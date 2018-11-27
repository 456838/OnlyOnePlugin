package com.salton123.storeplugin

import com.salton123.config.RetrofitManager
import com.salton123.mvp.presenter.RxPresenter
import com.salton123.storeplugin.model.IMusicStore
import com.salton123.util.RxUtils

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/2 6:29 PM
 * ModifyTime: 6:29 PM
 * Description:
 */
class MusicStorePresenter : RxPresenter<IMusicStoreView>() {
    private val MUSIC_STORE_BASE_URL = "http://api.bzqll.com/music/"
    fun searchMusic(keyword: String, num: Int, page: Int) {
        RetrofitManager.getRetrofit(MUSIC_STORE_BASE_URL)
            .create(IMusicStore::class.java)
            .search(keyword, num, page)
            .compose(RxUtils.rxSchedulerHelper())
            .subscribe({
                mView.onSearchSuccess(it)
            }, {
                mView?.onError()
            })
    }
}