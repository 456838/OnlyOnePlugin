package com.salton123.xmly.fm

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.andview.refreshview.XRefreshView
import com.andview.refreshview.XRefreshViewFooter
import com.hazz.kotlinmvp.view.recyclerview.adapter.OnItemClickListener
import com.salton123.GlideApp
import com.salton123.mvp.ui.BaseSupportPresenterFragment
import com.salton123.util.EventUtil
import com.salton123.util.ViewUtils
import com.salton123.xmly.R
import com.salton123.xmly.business.RequestContract
import com.salton123.xmly.business.RequestPresenter
import com.salton123.xmly.business.XmlyInitializer
import com.salton123.xmly.util.StatusBarUtil
import com.salton123.xmly.util.TrackUtil
import com.salton123.xmly.view.adapter.AlbumDetailAdapterNew
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.track.TrackList
import kotlinx.android.synthetic.main.xmly_cp_albums_new.*


/**
 * User: newSalton@outlook.com
 * Date: 2018/5/27 下午3:33
 * ModifyTime: 下午3:33
 * Description:
 */
class AlbumsComponent : BaseSupportPresenterFragment<RequestContract.IRequestPresenter>()
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
        page = 1
        refreshLayout.setLoadComplete(false)
        loadMore()
    }

    private val mAdapter by lazy { AlbumDetailAdapterNew(context) }
    override fun getLayout(): Int = R.layout.xmly_cp_albums_new
    private var mHeader: View? = null
    private var mAlbum: Album = Album()
    var page: Int = 1
    override fun initVariable(savedInstanceState: Bundle?) {
        mPresenter = RequestPresenter()
        mAlbum = arguments.getParcelable("album")
        setSwipeBackEnable(true)
    }

    override fun initViewAndData() {
        titleBar.title("${mAlbum.albumTitle}")
            .leftIcon(R.drawable.xmly_ic_arrow_back_black_24dp)
            .leftIconListener(View.OnClickListener { pop() })
        StatusBarUtil.setPaddingSmart(activity, titleBar)
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.setHasFixedSize(true)
        refreshLayout.setPinnedTime(1000)
        refreshLayout.setMoveForHorizontal(true)
        refreshLayout.pullLoadEnable = true
        refreshLayout.setAutoLoadMore(false)
        mAdapter.customLoadMoreView = XRefreshViewFooter(context)
        refreshLayout.enableReleaseToLoadMore(true)
        refreshLayout.enableRecyclerViewPullUp(true)
        refreshLayout.enablePullUpWhenLoadCompleted(true)
        refreshLayout.setXRefreshViewListener(this)
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(obj: Any?, position: Int) {
                XmlyInitializer.xmPlayerManager.playList(mAdapter.getData(), position)
                EventUtil.sendEvent(obj)
                TrackUtil.saveTrackList(mAdapter.getData())
                TrackUtil.saveTrackIndex(position)
            }
        })
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        initHeader()

        refreshLayout.postDelayed({
            loadMore()
        }, 500)
    }

    fun initHeader() {
        mHeader = mAdapter.setHeaderView(R.layout.xmly_view_album_header, mRecyclerView)
        mHeader?.let {
            GlideApp.with(this)
                .load(mAlbum.coverUrlLarge)
                .thumbnail(0.5f)
                .into(ViewUtils.f(it, R.id.blurImageView))

            ViewUtils.f<TextView>(it, R.id.tvTitle).text = mAlbum.albumTitle
            ViewUtils.f<TextView>(it, R.id.tvIntro).text = mAlbum.albumTags
            ViewUtils.f<TextView>(it, R.id.tvRichIntro).text = mAlbum.albumRichIntro
        }
    }

    private fun loadMore() {
        mPresenter.getTracks(mAlbum.id.toString(), "time_desc", "${page++}")
    }

    override fun onError(code: Int, msg: String) {
        shortToast("from AlbumsComponent errorCode: $code")
    }

    override fun <T> onSucceed(data: T?) {
        if (data is TrackList) {
            if (page >= data.totalPage) {
                refreshLayout.setLoadComplete(true)
            } else {
                if (mAdapter.itemCount == 2) {
                    mAdapter.addAllNotify(data.tracks)
                } else {
                    mAdapter.addAll(data.tracks)
                }
                refreshLayout.setLoadComplete(false)
            }
        }
        refreshLayout.stopRefresh()
    }

}