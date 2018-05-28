package com.salton123.xmly.fm

import android.os.Bundle
import com.salton123.mvp.ui.BaseSupportPresenterFragment
import com.salton123.xmly.R
import com.salton123.xmly.business.RequestContract
import com.salton123.xmly.business.RequestPresenter

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/28 下午4:30
 * ModifyTime: 下午4:30
 * Description:
 */

class AboutComponent : BaseSupportPresenterFragment<RequestContract.IRequestPresenter>(), RequestContract.IRequestView {
    override fun getLayout(): Int = R.layout.xmly_cp_about

    override fun initVariable(savedInstanceState: Bundle?) {
        mPresenter = RequestPresenter()
    }

    override fun initViewAndData() {
    }

    override fun onError(code: Int, msg: String) {
    }

    override fun <T> onSucceed(data: T?) {
    }

}