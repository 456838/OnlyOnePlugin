package com.salton123.recordplugin.model.business

import android.annotation.SuppressLint
import cn.sharerec.recorder.media.MP4
import com.salton123.core.CoreManager
import com.salton123.mvp.presenter.RxPresenter
import com.salton123.recordplugin.core.IRecorderCore
import com.salton123.recordplugin.model.bean.RecordMp4List
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/16 6:50 PM
 * ModifyTime: 6:50 PM
 * Description:
 */
class RecordPresenter : RxPresenter<RecordContract.IRecordView>(), RecordContract.IRecordPresenter {

    @SuppressLint("CheckResult")
    override fun listVideos() {
        Observable.create(ObservableOnSubscribe<RecordMp4List> { emitter ->
            var list = CoreManager.getCore(IRecorderCore::class.java).listVideos().asList()
            var ret = RecordMp4List(list as MutableList<MP4>)
            emitter.onNext(ret)
            emitter.onComplete()
        }).subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mView?.onSucceed(it)
            }, {
                mView?.onError(0, it.localizedMessage)
            })
    }
}