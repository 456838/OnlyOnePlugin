package com.salton123.xmly.fm

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.andview.refreshview.XRefreshView
import com.andview.refreshview.XRefreshViewFooter
import com.salton123.mvp.ui.BaseSupportPresenterFragment
import com.salton123.util.EventUtil
import com.salton123.util.MLog
import com.salton123.util.NetUtil
import com.salton123.xmly.R
import com.salton123.xmly.XmlyParams
import com.salton123.xmly.business.RequestContract
import com.salton123.xmly.business.RequestPresenter
import com.salton123.xmly.model.MultiTypeItem
import com.salton123.xmly.view.adapter.XmlyAdapter
import com.ximalaya.ting.android.opensdk.model.album.DiscoveryRecommendAlbumsList
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList
import com.ximalaya.ting.android.opensdk.model.banner.BannerV2List
import com.ximalaya.ting.android.opensdk.model.track.BatchTrackList
import kotlinx.android.synthetic.main.xmly_cp_recommend.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/24 下午3:57
 * ModifyTime: 下午3:57
 * Description:
 */
class RecommendComponent : BaseSupportPresenterFragment<RequestContract.IRequestPresenter>()
    , RequestContract.IRequestView, XRefreshView.XRefreshViewListener {

    override fun onHeaderMove(headerMovePercent: Double, offsetY: Int) {
    }

    override fun onRelease(direction: Float) {
    }

    override fun onLoadMore(isSilence: Boolean) {
        getData()
    }

    override fun onRefresh() {
    }

    override fun onRefresh(isPullDown: Boolean) {
        refreshLayout.setLoadComplete(false)
        mXmlyAdapter.clear()
        getData()
    }

    private val mXmlyAdapter by lazy { XmlyAdapter(context) }
    override fun getLayout(): Int {
        return R.layout.xmly_cp_recommend
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        mPresenter = RequestPresenter()
    }

    override fun initViewAndData() {
        if (!NetUtil.isNetworkAvailable(context)) {
            multipleStatusView.showNoNetwork()
        }
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mXmlyAdapter
        getData()
        multipleStatusView.setOnClickListener { getData() }
        refreshLayout.setPinnedTime(1000)
        refreshLayout.setMoveForHorizontal(true)
        refreshLayout.pullLoadEnable = true
        refreshLayout.setAutoLoadMore(false)
        mXmlyAdapter.customLoadMoreView = XRefreshViewFooter(context)
        refreshLayout.enableReleaseToLoadMore(true)
        refreshLayout.enableRecyclerViewPullUp(true)
        refreshLayout.enablePullUpWhenLoadCompleted(true)
        refreshLayout.setXRefreshViewListener(this)
    }

    private fun getData() {
        mPresenter.getDiscoveryRecommendAlbums("10")
        mPresenter.getCategoryBannersV2("1")
        mPresenter.getGuessLikeAlbum("10")
    }

    val TAG = "RecommendComponent"

    override fun onError(code: Int, msg: String) {
        MLog.error(TAG, "code=$code,msg=$msg")
        refreshLayout.stopRefresh()
        showEmpty()
    }

    private fun showEmpty() {
        if (mXmlyAdapter.getData().isEmpty()) {
            multipleStatusView.showEmpty()
        }
    }

    override fun <T> onSucceed(data: T?) {
        if (multipleStatusView.viewStatus != 0) {
            multipleStatusView.showContent()
        }
        when (data) {
            is DiscoveryRecommendAlbumsList -> data.discoveryRecommendAlbumses.forEach {
                mXmlyAdapter.add(MultiTypeItem(XmlyParams.TYPE_RECOMMEND_ALBUMS, it))
                mXmlyAdapter.notifyItemInserted(mXmlyAdapter.getData().size)
            }
            is BannerV2List -> {
                mXmlyAdapter.add(MultiTypeItem(XmlyParams.TYPE_BANNER, data))
                mXmlyAdapter.notifyItemInserted(mXmlyAdapter.getData().size)
            }
            is GussLikeAlbumList -> {
                mXmlyAdapter.add(MultiTypeItem(XmlyParams.TYPE_GUESS_LIKE, data))
                mXmlyAdapter.notifyItemInserted(mXmlyAdapter.getData().size)
            }

            is BatchTrackList -> {
                EventUtil.sendEvent(data.tracks.first())
            }
        }
        refreshLayout.stopRefresh()
        refreshLayout.setLoadComplete(true)
        showEmpty()
    }

}