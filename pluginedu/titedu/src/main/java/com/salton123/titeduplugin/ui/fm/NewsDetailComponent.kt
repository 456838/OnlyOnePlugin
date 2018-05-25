package com.salton123.titeduplugin.ui.fm

import android.os.Bundle
import cn.edu.tit.module.model.bean.NewsTagInfo
import com.salton123.base.BasePopupFragment
import com.salton123.titeduplugin.R
import kotlinx.android.synthetic.main.tit_cp_new_detail.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/5 上午11:56
 * ModifyTime: 上午11:56
 * Description:
 */

class NewsDetailComponent : BasePopupFragment() {

    private lateinit var newsTagInfo: NewsTagInfo
    override fun getLayout(): Int {
        return R.layout.tit_cp_new_detail
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        newsTagInfo = arguments.getParcelable<NewsTagInfo>("data")
    }

    override fun initViewAndData() {
        webView.loadUrl(newsTagInfo.url)
    }

}