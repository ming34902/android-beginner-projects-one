package flow.use2.repository

import flow.use.api.APIClient
import flow.use.api.WanAndroidAPI
import flow.use.entity.LoginRegisterResponse
import flow.use.entity.LoginRegisterResponseWrapper

// 仓库层
class APIRepository {

    suspend fun requestLogin(username: String, userpwd: String): LoginRegisterResponseWrapper<LoginRegisterResponse> {
        return APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
            .loginActionCoroutine(username,userpwd) // 调用 Retrofit 的API
    }
}