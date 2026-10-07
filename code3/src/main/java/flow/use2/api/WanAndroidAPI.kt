package flow.use2.api

import flow.use.entity.LoginRegisterResponse
import flow.use.entity.LoginRegisterResponseWrapper
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

// 客户端API 可访问 服务器API
interface WanAndroidAPI {
    /*
    * https://www.wanandroid.com/blog/show/2
    * 登录API
    * username=Derry-vip&password=123456
    * */
    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(@Field("username") username: String, @Field("password") password: String)
    : Call<LoginRegisterResponseWrapper<LoginRegisterResponse>>


    /*
    * 注册API
    * username=Derry-vip&password=123456&repassword=123456
    * */
    @POST("/user/register")
    @FormUrlEncoded
    fun registerAction(@Field("username") username: String, @Field("password") password: String, @Field("repassword") repassword: String)
            : Call<LoginRegisterResponseWrapper<LoginRegisterResponse>>
}
