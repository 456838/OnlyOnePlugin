package com.salton123.storeplugin.model

import com.salton123.storeplugin.bean.SearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/2 6:33 PM
 * ModifyTime: 6:33 PM
 * Description:
 */

interface IMusicStore {
    //    http://api.bzqll.com/music/tencent/search?key=579621905&s=123&num=100&page=1
    @GET("tencent/search?key=579621905")
    fun search(@Query("s") keyword: String, @Query("num") num: Int, @Query("page") page: Int): Observable<SearchResult>
}