package cn.edu.tit.module.config

import cn.edu.tit.module.api.TitConstants
import cn.edu.tit.module.api.TitNewsApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/8 下午8:43
 * ModifyTime: 下午8:43
 * Description:
 */
object RetrofitHelper {

    private fun <T> getApiService(baseUrl: String, cls: Class<T>): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(OkHttpClientManager.getClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(cls)
    }

    fun getTitNewsApi(): TitNewsApi {
        return getApiService(TitConstants.BASE_URL, TitNewsApi::class.java)
    }

}