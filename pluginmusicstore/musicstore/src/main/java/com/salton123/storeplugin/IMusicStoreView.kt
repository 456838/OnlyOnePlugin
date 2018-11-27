package com.salton123.storeplugin

import com.salton123.mvp.view.BaseView
import com.salton123.storeplugin.bean.SearchResult

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/5 3:55 PM
 * ModifyTime: 3:55 PM
 * Description:
 */
interface IMusicStoreView : BaseView {
    fun onError()
    abstract fun onSearchSuccess(data: SearchResult)
}