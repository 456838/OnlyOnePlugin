package com.salton123.xmly.fm

import android.os.Bundle
import com.salton123.mvp.ui.BaseSupportPresenterFragment
import com.salton123.xmly.R
import com.salton123.xmly.business.RequestContract
import com.salton123.xmly.business.IRequestPresenter
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/24 下午3:57
 * ModifyTime: 下午3:57
 * Description:
 */
class RecomemdComponent : BaseSupportPresenterFragment<RequestContract.IRequestPresenter>(), RequestContract.IRequestView {

    override fun getLayout(): Int {
        return R.layout.xmly_cp_recommend
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        mPresenter = IRequestPresenter()
    }

    override fun initViewAndData() {
        mPresenter.getGuessLikeAlbum("10");
    }

    override fun onError(code: Int, msg: String?) {
    }

    override fun <T : Any?> onSucceed(data: T) {
        if (data is GussLikeAlbumList){
            data.albumList
        }
    }
}