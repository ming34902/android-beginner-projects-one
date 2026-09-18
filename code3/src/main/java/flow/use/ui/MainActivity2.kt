package flow.use.ui
//package flow.use.ui
//
//import jdk.internal.net.http.common.Log
//import javax.swing.text.View
//
//class MainActivity2 : AppCompatActivity() {
//    private val TAG = "Derry"
//    var mProgressDialog: ProgressDialog? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//
//    fun startRequest(view: View) {
//        mProgressDialog = ProgressDialog(this)
//        mProgressDialog?.setTitle("请求服务器中...")
//        mProgressDialog?.show()
//
//        // Deery1
//        GlobalScope.launch(Dispatchers.Main) {
//            val loginRequest = APIClinet.instance.instanceRetrofit(WanAndroidAPI::class.java)
//                .loginActionCoroutine("Derry-vip", "123456")
//
//            // 主线程 更新UI
//            Log.d(TAG, "errorMsg:${loginRequest.data}")
//            textView.text = loginRequest.data.toString()
//            mProgressDialog?.dismiss()
//        }
//    }
//
//}