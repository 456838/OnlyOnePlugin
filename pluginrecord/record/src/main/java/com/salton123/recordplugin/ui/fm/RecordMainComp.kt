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
import com.andview.refreshview.XRefreshView
import com.hazz.kotlinmvp.view.recyclerview.adapter.OnItemClickListener
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
import com.salton123.core.CoreManager
import com.salton123.log.XLog
import com.salton123.recordplugin.R
import com.salton123.recordplugin.core.IRecorderCore
import com.salton123.recordplugin.livedata.RecordMainLiveData
import com.salton123.recordplugin.model.bean.VideoBean
import com.salton123.recordplugin.view.adapter.RecordMainRecAdapter
import com.salton123.util.ScreenUtils
import kotlinx.android.synthetic.main.record_main_comp.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/15 下午8:25
 * ModifyTime: 下午8:25
 * Description:
 */
class RecordMainComp : BaseSupportFragment(), XRefreshView.XRefreshViewListener {
    override fun onHeaderMove(headerMovePercent: Double, offsetY: Int) {

    }

    override fun onRelease(direction: Float) {
        getData()
    }

    override fun onLoadMore(isSilence: Boolean) {
        getData()
    }

    override fun onRefresh() {

    }

    override fun onRefresh(isPullDown: Boolean) {
        refreshLayout.setLoadComplete(false)
        getData()
    }

    private fun getData() {
        recordMainLiveData.onActive()
        refreshLayout.stopRefresh()
        refreshLayout.setLoadComplete(true)
    }

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
                val position = parent.getChildAdapterPosition(view)
                val offset = ScreenUtils.dp2px(2f)
                outRect.set(if (position % 2 == 0) 0 else offset, offset,
                        if (position % 2 == 0) offset else 0, offset)
            }
        })
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        Log.i("RecordMainComp", "cacheFolder:" + recorder.getCacheFolder())
//        RecordPropertyCompat.load()
        recorder.setVideoQuality(Recorder.LevelVideoQuality.LEVEL_HIGH)
        recorder.setMaxFrameSize(Recorder.LevelMaxFrameSize.LEVEL_1280_720)
//        recorder.disableMic()
        recorder.setRecordAudio(false)
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(obj: Any?, position: Int) {
                if (obj != null && obj is MP4) {
                    FragmentDelegate.newInstance(MediaPopupComp::class.java).apply {
                        var bundle = Bundle()
                        bundle.putParcelable(FragmentDelegate.ARG_ITEM, VideoBean(0, obj.localPath))
                        arguments = bundle
                        show(_mActivity.supportFragmentManager, "MediaPopupComp")
                    }
                }
            }
        })
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
        XLog.i("fillInData")
        refreshLayout.visibility = View.VISIBLE
        emptyView.visibility = View.GONE
        mAdapter.clear()
        mAdapter.addAll(list.asList() as MutableList<MP4>)
    }
}