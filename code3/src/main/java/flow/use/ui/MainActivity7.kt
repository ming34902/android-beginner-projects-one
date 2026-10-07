package flow.use.ui

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.code3.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.Duration.Companion.milliseconds


private suspend fun requestLoadUser(): String {
    val isLoadSuccess = true

    // 第一步：开启异步线程请求服务器，再把服务器响应结果回调出去
    withContext(Dispatchers.IO) {
        delay(3000L.milliseconds)
    }

    if (isLoadSuccess) {
        return "加载到用户数据"
    } else {
        return "加载失败"
    }
}
/**
 * 模拟：第一层请求 —— 请求用户信息
 */
private fun requestLoadUser(responseCallback: ResponseCallback) {
    val isLoadSuccess = true

    // 第一步：开启异步线程请求服务器，再把服务器响应结果回调出去
    object : Thread() {
        override fun run() {
            super.run()
            try {
                Thread.sleep(2000) // 模拟网络耗时

                if (isLoadSuccess) {
                    responseCallback.responseSuccess("加载到用户信息")
                } else {
                    responseCallback.responseError("用户信息加载失败")
                }
            } catch (e: InterruptedException) {
                responseCallback.responseError("用户信息加载异常：${e.message}")
            }
        }
    }.start()
}

/**
 * 模拟：第二层请求 —— 请求用户资产信息
 */
private fun requestLoadUseAssets(responseCallback: ResponseCallback) {
    val isLoadSuccess = true

    object : Thread() {
        override fun run() {
            super.run()
            try {
                Thread.sleep(2000)

                if (isLoadSuccess) {
                    responseCallback.responseSuccess("加载到用户资产信息")
                } else {
                    responseCallback.responseError("用户资产信息加载失败")
                }
            } catch (e: InterruptedException) {
                responseCallback.responseError("用户资产信息加载异常：${e.message}")
            }
        }
    }.start()
}

/**
 * java反编译
 * suspend 背后是 ResponseCallback
 * Continuation 保证 后面的代码恢复执行   (非阻塞)
 *
public static final object requestLoadUser(Continuation $ completion) {
    val isLoadSuccess = true
    // todo
    if (isLoadSuccess) {
        return "加载到用户数据"
    } else {
        return "加载失败"
    }
}
*/
/**
public interface Continuation<in T>{
    public val context: CoroutineContext
//    相当于 responseSuccess
public fun resumeWith(result: Result<T>)
}
*/

/**
interface ResponseCallback {
    // 请求服务器后 成功
    fun responseSuccess(serverResponseInfo: String)

    // 请求服务器后 失败
    fun responseError(serverResponseInfo: String)
}
 */

class MainActivity7: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
// suspend 修饰符
// suspend 起提醒作用，如果没有使用 withContext
suspend fun noSuspendFriendList(user: String): String {
    return "Derry"
}




class MainActivity6 : AppCompatActivity() {

    private val TAG = "Derry"
    private var mProgressDialog: ProgressDialog? = null
    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startRequest(view: View) {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog?.setTitle("请求服务器中...")
        mProgressDialog?.show()

        // GlobalScope 全局的作用域 协程，默认异步线程
        GlobalScope.launch(Dispatchers.Main) {
            // 异步请求1
            var serverResponseInfo = requestLoadUser()
            textView.text = serverResponseInfo // 更新UI
            textView.setTextColor(Color.GREEN) // 更新UI

            // 异步请求2
            serverResponseInfo = requestLoadUseAssets()
            textView.text = serverResponseInfo // 更新UI
            textView.setTextColor(Color.BLUE) // 更新UI

            // 异步请求3
            serverResponseInfo = requestLoadUserOrders()
            textView.text = serverResponseInfo // 更新UI
            mProgressDialog?.dismiss()
            textView.setTextColor(Color.RED) // 更新UI

            // ======== 5.3 协程背后状态机制原理 ===========
            /**
                // 伪代码理解
                requstLoadUser(object: Continuation<String> {
                    override val context: CoroutineContext
                        get() = EmptyCoroutineContext

                    override fun resumeWith(result: Result<String>) {
                        var serverResponseInfo = result.getOrNull()
                        textView.text = serverResponseInfo // 更新UI
                        textView.setTextColor(Color.GREEN) // 更新UI

                        // 异步请求2
                        serverResponseInfo = requestLoadUseAssets()
                        textView.text = serverResponseInfo // 更新UI
                        textView.setTextColor(Color.BLUE) // 更新UI

                        // 异步请求3
                        serverResponseInfo = requestLoadUserOrders()
                        textView.text = serverResponseInfo // 更新UI
                        mProgressDialog?.dismiss()
                        textView.setTextColor(Color.RED) // 更新UI
                    }
                })
             *
             */


        }
    }
    // ======== 5.3 协程背后状态机制原理 ===========
    suspend fun showCoroutine() {
        val user = requestLoadUser()
        Toast.makeText(this, "更新$user", Toast.LENGTH_SHORT).show()

        val userAssets = requestLoadUseAssets()
        Toast.makeText(this, "更新$userAssets", Toast.LENGTH_SHORT).show()

        val userOrders = requestLoadUserOrders()
        Toast.makeText(this, "更新$userOrders", Toast.LENGTH_SHORT).show()
    }
}