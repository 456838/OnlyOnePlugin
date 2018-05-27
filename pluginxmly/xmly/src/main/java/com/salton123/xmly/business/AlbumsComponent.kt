package com.salton123.xmly.business

import android.os.Bundle
import com.salton123.mvp.ui.BaseSupportPresenterFragment
import com.salton123.xmly.R
import com.ximalaya.ting.android.opensdk.model.metadata.MetaDataList

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/27 下午3:33
 * ModifyTime: 下午3:33
 * Description:
 */
class AlbumsComponent : BaseSupportPresenterFragment<RequestContract.IRequestPresenter>(), RequestContract.IRequestView {

    override fun getLayout(): Int {
        return R.layout.salton_fm_container
    }

    lateinit var albumId: String
    override fun initVariable(savedInstanceState: Bundle?) {
        mPresenter = RequestPresenter()
        albumId = arguments.getString("albumId", "")
    }

    override fun initViewAndData() {
        mPresenter.getMetadataList(albumId)
    }

    override fun onError(code: Int, msg: String) {
        shortToast("from AlbumsComponent errorCode: $code")
    }

    override fun <T> onSucceed(data: T?) {
        if (data is MetaDataList) {
            data.metaDatas
        }
    }

}