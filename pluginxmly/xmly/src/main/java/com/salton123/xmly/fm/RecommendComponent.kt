package com.salton123.xmly.fm

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.salton123.mvp.ui.BaseSupportPresenterFragment
import com.salton123.util.MLog
import com.salton123.xmly.R
import com.salton123.xmly.business.RequestContract
import com.salton123.xmly.business.RequestPresenter
import com.salton123.xmly.model.MultiTypeItem
import com.salton123.xmly.view.adapter.XmlyAdapter
import com.ximalaya.ting.android.opensdk.model.album.DiscoveryRecommendAlbumsList
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList
import com.ximalaya.ting.android.opensdk.model.banner.BannerV2List
import kotlinx.android.synthetic.main.xmly_cp_recommend.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/24 下午3:57
 * ModifyTime: 下午3:57
 * Description:
 */
class RecommendComponent : BaseSupportPresenterFragment<RequestContract.IRequestPresenter>(), RequestContract.IRequestView {

    private val mXmlyAdapter by lazy { XmlyAdapter(context) }
    override fun getLayout(): Int {
        return R.layout.xmly_cp_recommend
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        mPresenter = RequestPresenter()
    }

    override fun initViewAndData() {
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mXmlyAdapter
        mPresenter.getDiscoveryRecommendAlbums("10")
        mPresenter.getCategoryBannersV2("0")
        mPresenter.getGuessLikeAlbum("10")

    }

    val TAG = "RecommendComponent"

    override fun onError(code: Int, msg: String) {
        MLog.error(TAG, "code=$code,msg=$msg")
    }

    override fun <T> onSucceed(data: T?) {
        when (data) {
            is DiscoveryRecommendAlbumsList -> data.discoveryRecommendAlbumses.forEach { mXmlyAdapter.add(MultiTypeItem(MultiTypeItem.TYPE_RECOMMEND_ALBUMS, it)) }
            is BannerV2List -> mXmlyAdapter.add(MultiTypeItem(MultiTypeItem.TYPE_BANNER, data))
            is GussLikeAlbumList -> mXmlyAdapter.add(MultiTypeItem(MultiTypeItem.TYPE_GUESS_LIKE, data))
        }
    }
}