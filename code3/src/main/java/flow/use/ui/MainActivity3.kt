package flow.use.ui

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.code3.R

/**
 * 传统方式：多层（这里是三层）异步回调带来的痛点。
 *
 * 每一层请求都要重复：开启子线程 -> 子线程请求服务器 -> 通过 Handler 切回主线程 -> 更新 UI
 * -> 再发起下一层请求。层级越深，嵌套越深，代码越难阅读、越难维护，
 * 这就是所谓的"回调地狱 Callback Hell"。本文件后续会用 Flow 来重构它。
 *
 * 注意：本文件纯示意，不参与编译。
 */

interface ResponseCallback {
    // 请求服务器后 成功
    fun responseSuccess(serverResponseInfo: String)

    // 请求服务器后 失败
    fun responseError(serverResponseInfo: String)
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
 * 模拟：第三层请求 —— 请求用户订单信息
 */
private fun requestLoadUserOrders(responseCallback: ResponseCallback) {
    val isLoadSuccess = true

    object : Thread() {
        override fun run() {
            super.run()
            try {
                Thread.sleep(2000)

                if (isLoadSuccess) {
                    responseCallback.responseSuccess("加载到用户订单信息")
                } else {
                    responseCallback.responseError("用户订单信息加载失败")
                }
            } catch (e: InterruptedException) {
                responseCallback.responseError("用户订单信息加载异常：${e.message}")
            }
        }
    }.start()
}

// 传统方式：三层回调带来的痛点
class MainActivity3 : AppCompatActivity() {

    private val TAG = "Derry"
    private var mProgressDialog: ProgressDialog? = null
    private lateinit var textView: TextView

    // 主线程 Handler：只需创建一次，避免每层回调都 new 一个 Handler
    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            // msg.obj 携带子线程的响应结果，切回主线程后更新 UI
            val result = msg.obj as? String ?: return
            textView.text = result
            textView.setTextColor(Color.GREEN)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
    }

    fun startRequest(view: View) {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog?.setTitle("请求服务器中...")
        mProgressDialog?.show()

        // ===== 第一层：请求用户信息 =====
        requestLoadUser(object : ResponseCallback {
            override fun responseSuccess(serverResponseInfo: String) {
                // 子线程 -> 主线程更新 UI
                sendToMainThread(serverResponseInfo)

                // 第一层成功后，在回调里再发起第二层请求（嵌套开始）
                requestLoadUseAssets(object : ResponseCallback {
                    override fun responseSuccess(serverResponseInfo: String) {
                        sendToMainThread(serverResponseInfo)

                        // 第二层成功后，在回调里再发起第三层请求（嵌套继续加深）
                        requestLoadUserOrders(object : ResponseCallback {
                            override fun responseSuccess(serverResponseInfo: String) {
                                // 第三层也成功，累计耗时约 6 秒，全部完成后关闭进度框
                                sendToMainThread(serverResponseInfo)
                                mProgressDialog?.dismiss()
                            }

                            override fun responseError(serverResponseInfo: String) {
                                sendToMainThread(serverResponseInfo)
                                mProgressDialog?.dismiss()
                            }
                        })
                    }

                    override fun responseError(serverResponseInfo: String) {
                        sendToMainThread(serverResponseInfo)
                        mProgressDialog?.dismiss()
                    }
                })
            }

            override fun responseError(serverResponseInfo: String) {
                sendToMainThread(serverResponseInfo)
                mProgressDialog?.dismiss()
            }
        })
    }

    /**
     * 从异步线程切换回 Android 主线程，并携带响应结果更新 UI。
     */
    private fun sendToMainThread(serverResponseInfo: String) {
        val msg = mHandler.obtainMessage()
        msg.obj = serverResponseInfo
        mHandler.sendMessage(msg)
    }
}
