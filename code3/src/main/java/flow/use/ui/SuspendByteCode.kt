package flow.use.ui

import android.content.Context
import android.widget.Toast
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * 演示：suspend 函数编译后的「CPS + 状态机」形态。
 *
 * 说明：Kotlin 生成的字节码里会用到 `kotlin.coroutines.jvm.internal.CoroutineSingletons`
 * 与 `ContinuationImpl`，二者均为 internal，外部代码无法直接引用。
 * 这里用同语义的自定义标记 / 直接实现 `Continuation` 接口来替代，仅用于演示。
 */
private val COROUTINE_SUSPENDED: Any = Any()

/**
 * 演示用全局 Context：真实工程请由调用方（如 Application/Activity）注入。
 */
var appContext: Context? = null

fun requestLoadUser(completion: Continuation<Any?>) : Any? {
    // 省略几百行代码
    // 调用 invokeSuspend
    return COROUTINE_SUSPENDED
}

fun requestLoadUseAssets(completion: Continuation<Any?>) : Any? {
    // 省略几百行代码
    // 调用 invokeSuspend
    return COROUTINE_SUSPENDED
}

fun requestLoadUserOrders(completion: Continuation<Any?>) : Any? {
    // 省略几百行代码
    // 调用 invokeSuspend
    return COROUTINE_SUSPENDED
}

fun showCoroutine(completion: Continuation<Any?>) : Any? {
    /**
     * 字节码里它是 `ContinuationImpl` 的子类（internal，无法直接继承）；
     * 这里直接实现公开的 `Continuation` 接口，语义一致。
     */
    class TestContinuation(private val completion: Continuation<Any?>?) : Continuation<Any?> {

        override val context: CoroutineContext =
            completion?.context ?: EmptyCoroutineContext

        // 表示 协程状态机当前的状态
        var label: Int = 0
        // 协程返回结果
        var result: Any? = null

        // 用来保存之前协程的计算结果
        var user: Any? = null
        var userAssets: Any? = null

        // 协程恢复执行入口（等价于 ContinuationImpl.resumeWith -> invokeSuspend）
        // 它最终会调用 showCoroutine(this) 开启协程状态机
        // 状态机相关代码就是后面的 when 语句
        // 协程的本质就是 CPS + 状态机
        override fun resumeWith(result: Result<Any?>) {
            invokeSuspend(result)
        }

        fun invokeSuspend(_result: Result<Any?>) :Any? {
            result = _result
            label = label or Int.MIN_VALUE
            return showCoroutine(this)
        }
    }

    // 如果是初次运行，会把 completion 包一层 TestContinuation；
    // 若是恢复执行，则直接复用已有的 continuation
    val continuation = completion as? TestContinuation ?: TestContinuation(completion)

    // 三个变量，由外部输入 原函数的三个变量
    lateinit var user: String
    lateinit var userAssets: String
    lateinit var userOrders: String

    // result 接收协程运行的结果
    var result = continuation.result

    // suspendReturn 接收挂起函数的返回值  Any? == java的Object
    var suspendReturn : Any? = null

    // COROUTINE_SUSPENDED 代表当前函数被挂起
    val sFlag = COROUTINE_SUSPENDED

    var loop = true

    while (loop) {
        when(continuation.label) {
            0 -> {
                // 检查异常
                throwOnFailure(result)
                // start
                // 将label内置为1 准备进入下一次状态
                continuation.label = 1

                // 执行 requestLoadUser
                // withContext(IO) 执行耗时任务
                suspendReturn = requestLoadUser(continuation)

                // 判断是否挂起
                if (suspendReturn == sFlag) {
                    // suspend 挂起了，执行耗时任务完成后，返回 suspendReturn == 加载到用户信息
                    return suspendReturn
                } else {
                    // 未挂起，result 接收协程运行结果
                    result = suspendReturn
                }
            }
            1 -> {
                // 检查异常
                throwOnFailure(result)
                // start
                // 获取user的值
                user = result as String
                toast("更新ui-user:$user")

                // 将协程结果存储到 continuation 里
                continuation.user = user

                // 将label内置为2 准备进入下一次状态
                continuation.label = 2

                // 执行 requestLoadUseAssets
                // withContext(IO) 执行耗时任务
                suspendReturn = requestLoadUseAssets(continuation)

                // 判断是否挂起
                if (suspendReturn == sFlag) {
                    // suspend 挂起了，执行耗时任务完成后，返回 suspendReturn == 加载到用户资产信息
                    return suspendReturn
                } else {
                    // 未挂起，result 接收协程运行结果
                    result = suspendReturn
                }
            }

            2-> {
                // 检查异常
                throwOnFailure(result)
                // start
                user = continuation.user as String
                // 获取 userAssets 的值
                userAssets = result as String
                toast("更新ui-userAssets:$userAssets")

                // 将协程结果存储到 continuation 里
                continuation.user = user
                continuation.userAssets = userAssets

                // 将label内置为3 准备进入下一次状态
                continuation.label = 3

                // 执行 requestLoadUserOrders
                // withContext(IO) 执行耗时任务
                suspendReturn = requestLoadUserOrders(continuation)

                // 判断是否挂起
                if (suspendReturn == sFlag) {
                    // suspend 挂起了，执行耗时任务完成后，返回 suspendReturn == 加载到用户订单信息
                    return suspendReturn
                } else {
                    // 未挂起，result 接收协程运行结果
                    result = suspendReturn
                }
            }
            3-> {
                throwOnFailure(result)

                userAssets = continuation.userAssets as String
                userOrders = result as String
                toast("更新UI-userOrders:$userOrders")
                loop = false
            }
        }
    }

    return Unit
}

private fun throwOnFailure(value: Any?) {
    if (value is Result<*>) {
        val e = value.exceptionOrNull()
        if (e != null) throw e
    }
}

fun toast(msg: Any) {
    val ctx = appContext ?: return
    Toast.makeText(ctx, "${Thread.currentThread().name},msg=$msg", Toast.LENGTH_SHORT).show()
}


