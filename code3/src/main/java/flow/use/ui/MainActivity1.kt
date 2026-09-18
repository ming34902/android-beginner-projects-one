package flow.use.ui

// Android 上不存在 javax.swing，改为注释保留（原为误导入）
//import javax.swing.text.View

//import flow.use.api.WanAndroidAPI
//import flow.use.entity.LoginRegisterResponse
//import javax.swing.text.View
//
//class MainActivity1 : AppCompatActivity() {
//    private val TAG = "Derry"
//    var mProgressDialog: ProgressDialog? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//
//
//    fun startRequest(view: View) {
//        mProgressDialog = ProgressDialog(this)
//        mProgressDialog?.setTitle("请求服务器中...")
//        mProgressDialog?.show()
//
//        // 开启异步线程
//        // 第一大步骤，异步线程请求服务器 & 把服务器响应结果转发给Handler
//        object:Thread() {
//            override fun run() {
//                super.run()
//                Thread.sleep(2000)
//                val loginRequest =
//                    APIClinent.instance.instanceRetrofit(WanAndroidAPI::class.java).loginAction("Derry-vip", "123456")
//
//                val result: LoginRegisterResponseWrapper<LoginRegisterResponse> ?= loginRequest.execute().body()
//
//                // 发生handle 切换Android主线程
//                val msg = mHandler.obtainMessage()
//                msg.obj = result
//                mHandler.sendMessage(msg)
//            }
//        }.start()
//    }
//}




