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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

/**
 * 传统方式：多层（这里是三层）异步回调带来的痛点。
 *
 * 每一层请求都要重复：开启子线程 -> 子线程请求服务器 -> 通过 Handler 切回主线程 -> 更新 UI
 * -> 再发起下一层请求。层级越深，嵌套越深，代码越难阅读、越难维护，
 * 这就是所谓的"回调地狱 Callback Hell"。本文件后续会用 Flow 来重构它。
 *
 * 注意：本文件纯示意，不参与编译。
 */

//interface ResponseCallback {
//    // 请求服务器后 成功
//    fun responseSuccess(serverResponseInfo: String)
//
//    // 请求服务器后 失败
//    fun responseError(serverResponseInfo: String)
//}

/**
 * 模拟：第一层请求 —— 请求用户信息
 * suspend 标记该函数 是可异步挂起
 */
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
 * 模拟：第二层请求 —— 请求用户资产信息
 * suspend 标记该函数 是可异步挂起
 */
private suspend fun requestLoadUseAssets(): String {
    val isLoadSuccess = true

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
 * 模拟：第三层请求 —— 请求用户订单信息
 */
private suspend fun requestLoadUserOrders() : String {
    val isLoadSuccess = true

    withContext(Dispatchers.IO) {
        delay(3000L.milliseconds)
    }

    if (isLoadSuccess) {
        return "加载到用户数据"
    } else {
        return "加载失败"
    }
}

// 传统方式：三层回调带来的痛点
class MainActivity4 : AppCompatActivity() {

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

        GlobalScope.launch(Dispatchers.Main) {
            var serverResponseInfo = requestLoadUser()
            textView.text = serverResponseInfo // 更新UI
            textView.setTextColor(Color.GREEN) // 更新UI
        }
    }
}
