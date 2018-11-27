package com.salton123.storeplugin

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.andview.refreshview.XRefreshView
import com.hazz.kotlinmvp.view.recyclerview.adapter.OnItemClickListener
import com.liulishuo.okdownload.DownloadListener
import com.liulishuo.okdownload.DownloadTask
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo
import com.liulishuo.okdownload.core.cause.EndCause
import com.liulishuo.okdownload.core.cause.ResumeFailedCause
import com.salton123.mvp.ui.BaseSupportPresenterFragment
import com.salton123.storeplugin.bean.Data
import com.salton123.storeplugin.bean.SearchResult
import com.salton123.util.NetUtil
import kotlinx.android.synthetic.main.comp_music_store.*
import java.io.File
import java.lang.Exception

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/2 6:12 PM
 * ModifyTime: 6:12 PM
 * Description:
 */
class MusicStoreComp : BaseSupportPresenterFragment<MusicStorePresenter>()
    , IMusicStoreView
    , XRefreshView.XRefreshViewListener {
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
        refreshLayout.stopRefresh()
        refreshLayout.setLoadComplete(true)
    }

    override fun getLayout(): Int = R.layout.comp_music_store

    override fun initVariable(savedInstanceState: Bundle?) {
        mPresenter = MusicStorePresenter()
    }

    private val mAdapter by lazy { MusicStoreAdapter(_mActivity) }
    override fun initViewAndData() {
        if (!NetUtil.isNetworkAvailable(_mActivity)) {
            multipleStatusView.showNoNetwork()
        }
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mAdapter
        refreshLayout.setPinnedTime(1000)
        refreshLayout.setMoveForHorizontal(true)
        refreshLayout.pullLoadEnable = true
        refreshLayout.setAutoLoadMore(false)
        refreshLayout.enableReleaseToLoadMore(true)
        refreshLayout.enableRecyclerViewPullUp(true)
        refreshLayout.enablePullUpWhenLoadCompleted(true)
        refreshLayout.setXRefreshViewListener(this)
    }

    override fun initListener() {
        btnSearch.setOnClickListener {
            var keyword = etInput.text.toString().trim()
            mPresenter.searchMusic(keyword, 100, 1)
        }
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(obj: Any?, position: Int) {
                if (obj is Data) {
                    var task = DownloadTask.Builder(obj.url, File("/sdcard/")).build()
//                    OkDownload.with().downloadDispatcher().enqueue(task)
                    task.enqueue(object : DownloadListener {
                        override fun connectTrialEnd(task: DownloadTask, responseCode: Int, responseHeaderFields: MutableMap<String, MutableList<String>>) {
                        }

                        override fun fetchEnd(task: DownloadTask, blockIndex: Int, contentLength: Long) {
                        }

                        override fun downloadFromBeginning(task: DownloadTask, info: BreakpointInfo, cause: ResumeFailedCause) {
                        }

                        override fun taskStart(task: DownloadTask) {
                        }

                        override fun taskEnd(task: DownloadTask, cause: EndCause, realCause: Exception?) {
                        }

                        override fun connectTrialStart(task: DownloadTask, requestHeaderFields: MutableMap<String, MutableList<String>>) {
                        }

                        override fun downloadFromBreakpoint(task: DownloadTask, info: BreakpointInfo) {
                        }

                        override fun fetchStart(task: DownloadTask, blockIndex: Int, contentLength: Long) {
                        }

                        override fun fetchProgress(task: DownloadTask, blockIndex: Int, increaseBytes: Long) {
                        }

                        override fun connectEnd(task: DownloadTask, blockIndex: Int, responseCode: Int, responseHeaderFields: MutableMap<String, MutableList<String>>) {
                        }

                        override fun connectStart(task: DownloadTask, blockIndex: Int, requestHeaderFields: MutableMap<String, MutableList<String>>) {
                        }

                    })
                }
            }
        })
    }

    override fun onError() {
        refreshLayout.stopRefresh()
        refreshLayout.setLoadComplete(true)
    }

    override fun onSearchSuccess(data: SearchResult) {
        mAdapter.addAll(data.data.toMutableList())

        refreshLayout.stopRefresh()
        refreshLayout.setLoadComplete(true)
    }

}