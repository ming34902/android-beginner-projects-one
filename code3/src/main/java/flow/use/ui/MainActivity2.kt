package flow.use.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.code3.R
import flow.use.api.APIClient
import flow.use.api.WanAndroidAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {

    private val TAG = "Derry"
    var mProgressDialog: ProgressDialog? = null
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textView)
    }

    fun startRequest(view: View) {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog?.setTitle("请求服务器中...")
        mProgressDialog?.show()

        // Deery1
        GlobalScope.launch(Dispatchers.Main) {
            val loginRequest = APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                .loginActionCoroutine("Derry-vip", "123456")

            // 主线程 更新UI
            Log.d(TAG, "errorMsg:${loginRequest.data}")
            textView.text = loginRequest.data.toString()
            mProgressDialog?.dismiss()
        }
    }
}
