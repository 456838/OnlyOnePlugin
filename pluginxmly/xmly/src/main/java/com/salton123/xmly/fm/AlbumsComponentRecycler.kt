package com.salton123.xmly.fm

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.andview.refreshview.XRefreshView
import com.andview.refreshview.XRefreshViewFooter
import com.salton123.mvp.ui.BaseSupportPresenterFragment
import com.salton123.xmly.R
import com.salton123.xmly.XmlyParams
import com.salton123.xmly.business.RequestContract
import com.salton123.xmly.business.RequestPresenter
import com.salton123.xmly.model.MultiTypeItem
import com.salton123.xmly.util.StatusBarUtil
import com.salton123.xmly.view.adapter.AlbumsDetailRecyclerAdapter
import com.salton123.xmly.view.widget.AlbumHeader
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.track.TrackList
import kotlinx.android.synthetic.main.xmly_cp_albums_new.*


/**
 * User: newSalton@outlook.com
 * Date: 2018/5/27 下午3:33
 * ModifyTime: 下午3:33
 * Description:
 */
class AlbumsComponentRecycler : BaseSupportPresenterFragment<RequestContract.IRequestPresenter>()
    , RequestContract.IRequestView, XRefreshView.XRefreshViewListener {

    override fun onHeaderMove(headerMovePercent: Double, offsetY: Int) {
    }

    override fun onRelease(direction: Float) {
    }

    override fun onLoadMore(isSilence: Boolean) {
        loadMore()
    }

    override fun onRefresh() {
    }

    override fun onRefresh(isPullDown: Boolean) {
        mAdapter.clear()
        mAdapter.add(MultiTypeItem(XmlyParams.TYPE_HEADER, mAlbum))
        page = 1
        loadMore()
    }

    private val mAdapter by lazy { AlbumsDetailRecyclerAdapter(_mActivity) }
    override fun getLayout(): Int = R.layout.xmly_cp_albums_new
    private val mHeader by lazy { AlbumHeader(_mActivity) }
    private var mAlbum: Album = Album()
    var page: Int = 1
    override fun initVariable(savedInstanceState: Bundle?) {
        mPresenter = RequestPresenter()
        mAlbum = arguments!!.getParcelable("album")
    }

    override fun initViewAndData() {
        titleBar.title("${mAlbum.albumTitle}")
            .leftIcon(R.drawable.xmly_ic_arrow_back_black_24dp)
            .leftIconListener(View.OnClickListener { pop() })
        StatusBarUtil.setPaddingSmart(_mActivity, titleBar)
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.setHasFixedSize(true)
        mHeader.setData(mAlbum)
//        mAdapter.setHeaderView(mHeader, mRecyclerView)
        loadMore()
        refreshLayout.setPinnedTime(1000)
        refreshLayout.setMoveForHorizontal(true)
        refreshLayout.pullLoadEnable = true
        refreshLayout.setAutoLoadMore(false)
        mAdapter.customLoadMoreView = XRefreshViewFooter(context)
        refreshLayout.enableReleaseToLoadMore(true)
        refreshLayout.enableRecyclerViewPullUp(true)
        refreshLayout.enablePullUpWhenLoadCompleted(true)
        refreshLayout.setXRefreshViewListener(this)
        mAdapter.add(MultiTypeItem(XmlyParams.TYPE_HEADER, mAlbum))
    }

    private fun loadMore() {
        mPresenter.getTracks(mAlbum.id.toString(), "time_desc", "${page++}")
    }

    override fun onError(code: Int, msg: String) {
        shortToast("from AlbumsComponent errorCode: $code")
    }

    override fun <T> onSucceed(data: T?) {
        if (data is TrackList) {
            mAdapter.setContentData(data.tracks)
            refreshLayout.setLoadComplete(false)
        }
        refreshLayout.stopRefresh()
    }
}