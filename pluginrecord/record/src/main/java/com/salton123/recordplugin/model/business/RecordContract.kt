package com.salton123.recordplugin.model.business

import com.salton123.mvp.presenter.BasePresenter
import com.salton123.mvp.view.BaseView

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/16 6:48 PM
 * ModifyTime: 6:48 PM
 * Description:
 */
interface RecordContract {
    interface IRecordPresenter : BasePresenter<IRecordView> {
        fun listVideos()
    }

    interface IRecordView : BaseView {
        fun onError(code: Int, msg: String)

        fun <T> onSucceed(data: T?)
    }
}