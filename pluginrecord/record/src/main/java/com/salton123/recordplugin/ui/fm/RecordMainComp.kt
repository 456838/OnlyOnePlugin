package com.salton123.recordplugin.ui.fm

import android.arch.lifecycle.Observer
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import cn.sharerec.recorder.Recorder
import cn.sharerec.recorder.media.MP4
import com.salton123.base.BaseSupportFragment
import com.salton123.core.CoreManager
import com.salton123.recordplugin.R
import com.salton123.recordplugin.core.IRecorderCore
import com.salton123.recordplugin.livedata.RecordMainLiveData
import com.salton123.recordplugin.view.adapter.RecordMainRecAdapter
import com.salton123.util.ScreenUtils
import kotlinx.android.synthetic.main.record_main_comp.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/15 下午8:25
 * ModifyTime: 下午8:25
 * Description:
 */
class RecordMainComp : BaseSupportFragment() {
    private var recorder = CoreManager.getCore(IRecorderCore::class.java)
    private lateinit var recordMainLiveData: RecordMainLiveData
    private val mAdapter: RecordMainRecAdapter by lazy { RecordMainRecAdapter(_mActivity) }
    override fun getLayout(): Int {
        return R.layout.record_main_comp
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        recordMainLiveData = RecordMainLiveData()
        recordMainLiveData.observe(this, Observer<Array<MP4>> { t -> fillInData(t!!) })
    }

    override fun initViewAndData() {
        setListener(emptyView, btnRecordAct)
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                val position = parent.getChildPosition(view)
                val offset = ScreenUtils.dp2px(2f)
                outRect.set(if (position % 2 == 0) 0 else offset, offset,
                    if (position % 2 == 0) offset else 0, offset)
            }

        })
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        Log.i("RecordMainComp", "cacheFolder:" + recorder.getCacheFolder())
    }

    override fun onClick(v: View?) {
        when (v) {
            btnRecordAct -> {
                if (recorder.getState() == Recorder.STATE_IDLE) {
                    recorder.start()
                    btnRecordAct.text = "结束录制"
                    recordMainLiveData.onActive()
                } else if (recorder.getState() > Recorder.STATE_STARTING) {
                    recorder.stop()
                    recordMainLiveData.onActive()
                    btnRecordAct.text = "开始录制"
                }
            }
            emptyView -> {
                if (recorder.isAvailable()) {
                    recorder.start()
                } else {
                    longToast("系统版本低于5.0无法录制")
                }
            }
        }
    }

    private fun fillInData(list: Array<MP4>) {
        flContent.visibility = View.VISIBLE
        emptyView.visibility = View.GONE
        mAdapter.clear()
        mAdapter.addAll(list.asList() as MutableList<MP4>)
    }


}