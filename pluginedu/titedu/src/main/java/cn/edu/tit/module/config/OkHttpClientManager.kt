package cn.edu.tit.module.config

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/8 下午8:58
 * ModifyTime: 下午8:58
 * Description:
 */
object OkHttpClientManager {

    private lateinit var okHttpClient: OkHttpClient

    init {
        initOkHttpClient()
    }

    private fun initOkHttpClient() {
        okHttpClient = OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()
    }

    fun getClient(): OkHttpClient {
        if (okHttpClient == null) {
            initOkHttpClient()
        }
        return okHttpClient
    }
}