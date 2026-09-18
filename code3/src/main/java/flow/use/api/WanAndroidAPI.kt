package flow.use.api

// Android 上不存在 jdk.vm.ci.*，改为注释保留（原为误导入）
//import jdk.vm.ci.code.site.Call

// 客户端API 可访问 服务器API
//interface WanAndroidAPI {
//    /*
//    * https://www.wanandroid.com/blog/show/2
//    * 登录API
//    * username=Derry-vip&password=123456
//    * */
//    @POST("/user/login")
//    @FormUrlEncoded
//    fun loginAction(@Field("username") username: String, @Field("password") password: String)
//    : Call<LoginRegisterResponseWrapper<LoginRegisterResponse>>
//
//
//
//    @POST("/user/register")
//    @FormUrlEncoded
//    fun registerAction(@Field("username") username: String, @Field("password") password: String, @Field("repassword") repassword: String)
//            : Call<LoginRegisterResponseWrapper<LoginRegisterResponse>>
//}