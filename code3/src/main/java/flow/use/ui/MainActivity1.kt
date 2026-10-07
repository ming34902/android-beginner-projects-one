package flow.use.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.code3.R
import flow.use.api.APIClient
import flow.use.entity.LoginRegisterResponse
import flow.use.entity.LoginRegisterResponseWrapper
import flow.use.api.WanAndroidAPI

class MainActivity1 : AppCompatActivity() {

    private val TAG = "Derry"
    var mProgressDialog: ProgressDialog? = null
    private lateinit var textView: TextView

    // 主线程 Handler：接收子线程结果，切回主线程更新 UI
    private val mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val result = msg.obj as? LoginRegisterResponseWrapper<LoginRegisterResponse>
            textView.text = result?.data?.nickname ?: result?.errorMsg ?: "加载失败"
            mProgressDialog?.dismiss()
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

        // 开启异步线程
        // 第一大步骤，异步线程请求服务器 & 把服务器响应结果转发给Handler
        object : Thread() {
            override fun run() {
                super.run()
                Thread.sleep(2000)
                val loginRequest =
                    APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java).loginAction("Derry-vip", "123456")

                val result: LoginRegisterResponseWrapper<LoginRegisterResponse>? = loginRequest.execute().body()

                // 发生handle 切换Android主线程
                val msg = mHandler.obtainMessage()
                msg.obj = result
                mHandler.sendMessage(msg)
            }
        }.start()
    }
}
