package com.salton123.recordplugin

import android.os.Bundle
import android.util.Log
import android.view.View
import cn.sharerec.recorder.Recorder
import cn.sharerec.recorder.media.MP4
import com.salton123.base.BaseSupportFragment
import com.salton123.core.CoreManager
import com.salton123.recordplugin.core.IRecorderCore
import com.salton123.util.RxUtils
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.record_main_comp.*
import java.util.concurrent.TimeUnit

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/15 下午8:25
 * ModifyTime: 下午8:25
 * Description:
 */
class RecordMainComp : BaseSupportFragment() {
    private var recorder = CoreManager.getCore(IRecorderCore::class.java)
    override fun getLayout(): Int {
        return R.layout.record_main_comp
    }

    private var mDisp: Disposable? = null
    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {

        setListener(emptyView, btnRecordAct)
        Log.i("RecordMainComp", "cacheFolder:" + CoreManager.getCore(IRecorderCore::class.java).getCacheFolder())
    }

    override fun onClick(v: View?) {
        when (v) {
            btnRecordAct -> {
                if (recorder.getState() == Recorder.STATE_IDLE) {
                    recorder.start()
                    btnRecordAct.setText("结束录制")
                } else if (recorder.getState() > Recorder.STATE_STARTING) {
                    recorder.stop()
                    btnRecordAct.text = "开始录制"
                }
            }
            emptyView -> {
                if (CoreManager.getCore(IRecorderCore::class.java).isAvailable()) {
                    CoreManager.getCore(IRecorderCore::class.java).start()
                } else {
                    longToast("系统版本低于5.0无法录制")
                }
            }
        }
        mDisp = Observable.interval(3000, TimeUnit.MILLISECONDS).subscribe {
            asyncCheckTask()
        }
    }

    override fun onPause() {
        super.onPause()
        mDisp?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mDisp = Observable.interval(3000, TimeUnit.MILLISECONDS).subscribe {
            asyncCheckTask()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisp?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    /**
     * async check videos
     */
    private fun asyncCheckTask() {
        Flowable.create(FlowableOnSubscribe<Array<MP4>> { emitter ->
            emitter.onNext(CoreManager.getCore(IRecorderCore::class.java)
                .listVideos())
        }, BackpressureStrategy.LATEST)
            .compose(RxUtils.schedulersTransformerForFlowable())
            .subscribe { list ->
                if (list != null && list.isNotEmpty()) {
                    fillInData(list)
                }
            }
    }

    private fun fillInData(list: Array<MP4>) {
        flContent.visibility = View.VISIBLE
        emptyView.visibility = View.GONE
        tvData.setText(list.asList().toString())
    }


}