package com.salton123.recordplugin.ui.fm

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import cn.sharerec.recorder.OnRecorderStateListener
import cn.sharerec.recorder.Recorder
import cn.sharerec.recorder.media.MP4
import com.andview.refreshview.XRefreshView
import com.hazz.kotlinmvp.view.recyclerview.adapter.OnItemClickListener
import com.hazz.kotlinmvp.view.recyclerview.adapter.OnItemLongClickListener
import com.salton123.base.FragmentDelegate
import com.salton123.core.CoreManager
import com.salton123.mvp.ui.BaseSupportPresenterFragment
import com.salton123.recordplugin.R
import com.salton123.recordplugin.core.IRecorderCore
import com.salton123.recordplugin.model.bean.RecordMp4List
import com.salton123.recordplugin.model.bean.VideoBean
import com.salton123.recordplugin.model.business.RecordContract
import com.salton123.recordplugin.model.business.RecordPresenter
import com.salton123.recordplugin.view.adapter.RecordMainRecAdapter
import com.salton123.util.FileUtils
import com.salton123.util.ScreenUtils
import kotlinx.android.synthetic.main.record_comp_exhibition.*

/**
 * Date: 2018/10/16 5:45 PM
 * ModifyTime: 5:45 PM
 * Description:
 */
class RecordExhibitionComp : BaseSupportPresenterFragment<RecordContract.IRecordPresenter>(),
    RecordContract.IRecordView, XRefreshView.XRefreshViewListener {
    private var recorder = CoreManager.getCore(IRecorderCore::class.java)
    override fun onHeaderMove(headerMovePercent: Double, offsetY: Int) {
    }

    override fun onRelease(direction: Float) {
    }

    override fun onLoadMore(isSilence: Boolean) {
    }

    override fun onRefresh() {
    }

    override fun onRefresh(isPullDown: Boolean) {
        refreshLayout.setLoadComplete(false)
        getData()
    }

    override fun onError(code: Int, msg: String) {
    }

    override fun <T> onSucceed(data: T?) {
        if (data is RecordMp4List) {
            if (data.item.size == 0) {
                statusView.showEmpty()
            } else {
                statusView.showContent()
                mAdapter.clear()
                mAdapter.addAll(data.item)
            }
        }
    }

    private val mAdapter: RecordMainRecAdapter by lazy { RecordMainRecAdapter(_mActivity) }

    override fun getLayout(): Int = R.layout.record_comp_exhibition

    override fun initVariable(savedInstanceState: Bundle?) {
        mPresenter = RecordPresenter()
    }

    override fun initViewAndData() {
        statusView.showEmpty()
        recyclerView.adapter = mAdapter
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
                val position = parent.getChildAdapterPosition(view)
                val offset = ScreenUtils.dp2px(2f)
                outRect.set(if (position % 2 == 0) 0 else offset, offset,
                    if (position % 2 == 0) offset else 0, offset)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(_mActivity)
        refreshLayout.setXRefreshViewListener(this)
        getData()
    }

    override fun initListener() {
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
        mAdapter.setOnItemLongClickListener(object : OnItemLongClickListener {
            override fun onItemLongClick(obj: Any?, position: Int): Boolean {
                showDleteDialog(obj, position)
                return true
            }
        })
        ivStartRecordNew.setOnClickListener {
            if (recorder.getState() == Recorder.STATE_IDLE) {
                recorder.start()
            } else if (recorder.getState() > Recorder.STATE_STARTING) {
                recorder.stop()
            }
        }
        recorder.setOnRecorderStateListener(OnRecorderStateListener { _, p1 ->
            if (p1 == 1) {
                val small = Intent(Intent.ACTION_MAIN)
                small.addCategory(Intent.CATEGORY_HOME)
                small.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(small)
            }
        })
        f<ViewGroup>(R.id.emptyViewHolder).setOnClickListener {
            if (recorder.isAvailable()) {
                recorder.start()
            } else {
                longToast("系统版本低于5.0无法录制")
            }
        }
    }

    private var deleteDialog: AlertDialog? = null
    private fun showDleteDialog(obj: Any?, position: Int) {
        deleteDialog = AlertDialog.Builder(_mActivity)
            .setMessage("确认删除该视频？")
            .setNegativeButton("取消") { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton("确认") { dialog, _ ->
                if (obj != null && obj is MP4) {
                    try {
                        mAdapter.getData().remove(obj)
                        mAdapter.notifyItemRemoved(position)
                        FileUtils.deleteFile(obj.localPath)
                    } catch (ex: Exception) {
                        log(ex.localizedMessage)
                    }
                }
                dialog.dismiss()
            }
            .create()
        deleteDialog?.show()
    }

    private fun getData() {
        mPresenter.listVideos()
        refreshLayout.stopRefresh()
        refreshLayout.setLoadComplete(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        deleteDialog?.dismiss()
    }
}