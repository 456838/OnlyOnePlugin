package cn.edu.tit.module.api

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/8 下午4:49
 * ModifyTime: 下午4:49
 * Description:
 */
interface TitNewsApi {

    @POST("list.jsp?urltype=tree.TreeTempUrl")
    @FormUrlEncoded
    fun getNewsCategory(@Query("wbtreeid") category: Int, @Field("a42787PAGE") page: Int, @Field("actiontype") actionType: String): Observable<String>

}