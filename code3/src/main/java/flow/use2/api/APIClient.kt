package flow.use2.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIClient {
    // 单例
    private object Holder {
        val INSTANT = APIClient()
    }

    // 派生
    companion object {
        val instance = Holder.INSTANT
    }

    // WanAndroidAPI 实例化这个， XXXAPI 实例化
    fun <T> instanceRetrofit(apiInterface: Class<T>): T {
        // OkHttpClient 请求服务器
        val mOkHttpClient = OkHttpClient().newBuilder().myApply {
            readTimeout(10000, TimeUnit.SECONDS) // 读取超时时间
            connectTimeout(10000, TimeUnit.SECONDS) // 连接超时时间
            writeTimeout(10000, TimeUnit.SECONDS) // 写出超时时间
        }.build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .client(mOkHttpClient)
            // response 的事件响应，rxJava 处理，Gson 来解析（JavaBean）
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(apiInterface)
    }
}

fun <T> T.myApply(mm: T.() -> Unit): T {
    // T == this
    mm()
    return this
}
