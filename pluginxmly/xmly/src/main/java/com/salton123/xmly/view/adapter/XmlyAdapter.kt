package com.salton123.xmly.view.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import cn.bingoogolapple.bgabanner.BGABanner
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hazz.kotlinmvp.view.recyclerview.MultipleType
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.salton123.GlideApp
import com.salton123.xmly.R
import com.salton123.xmly.XmlyParams
import com.salton123.xmly.model.MultiTypeItem
import com.ximalaya.ting.android.opensdk.model.album.DiscoveryRecommendAlbums
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList
import com.ximalaya.ting.android.opensdk.model.banner.BannerV2
import com.ximalaya.ting.android.opensdk.model.banner.BannerV2List

class XmlyAdapter(context: Context)
    : XRefreshRecyclerAdapter<MultiTypeItem>(context, object : MultipleType<MultiTypeItem> {
    override fun getLayoutId(item: MultiTypeItem, position: Int): Int {
        return when (item.viewType) {
            XmlyParams.TYPE_BANNER -> R.layout.xmly_item_play_type_banner
            XmlyParams.TYPE_GUESS_LIKE -> R.layout.xmly_item_play_type_guess_like
            XmlyParams.TYPE_RECOMMEND_ALBUMS -> R.layout.xmly_item_play_type_recommend_albums
            else -> 0
        }
    }
}) {

    private val TAG = "XmlyAdapter"

    override fun bindData(holder: ViewHolder, data: MultiTypeItem, position: Int) {
        when (data.viewType) {
            XmlyParams.TYPE_BANNER -> {
                var banner = holder.getView<BGABanner>(R.id.banner)
                banner.setAdapter(object : BGABanner.Adapter<ImageView, BannerV2> {
                    override fun fillBannerItem(banner: BGABanner?, itemView: ImageView?, model: BannerV2?, position: Int) {
                        itemView?.let {
                            //                            itemView.scaleType = ImageView.ScaleType.CENTER_INSIDE
                            GlideApp.with(context)
                                .load(model?.bannerUrl)
                                .placeholder(R.drawable.placeholder_banner)
                                .thumbnail(0.5f)
                                .dontAnimate()
                                .transition(DrawableTransitionOptions().crossFade())
                                .centerInside()
                                .into(itemView)
                        }
                    }
                })
                banner.setDelegate(object : BGABanner.Delegate<ImageView, BannerV2> {
                    override fun onBannerItemClick(banner: BGABanner?, itemView: ImageView?, model: BannerV2?, position: Int) {
//                        model?.bannerUrl?.let { Toast.makeText(context, "${model.bannerUrl}", Toast.LENGTH_LONG).show() }
                    }
                })
                if (data.item is BannerV2List) {
                    val bannerV2 = data.item as BannerV2List
//                    banner.setData(bannerV2.bannerV2s, bannerV2.bannerV2s.map { it.kind })
                    banner.setData(bannerV2.bannerV2s, null)
                }
            }
            XmlyParams.TYPE_GUESS_LIKE -> {
                if (data.item is GussLikeAlbumList) {
                    val gussLikeAlbumList = data.item as GussLikeAlbumList
                    holder.setText(R.id.tv_title, "猜你喜欢")
                    holder.getView<RecyclerView>(R.id.fl_recyclerView).let {
                        it.layoutManager = LinearLayoutManager(context as Activity, LinearLayoutManager.HORIZONTAL, false)
                        it.adapter = GuessLikeTypeHorizontalAdapter(context, gussLikeAlbumList.albumList, R.layout.xmly_item_play_type_guess_like_stub)
                    }
                    holder.getView<TextView>(R.id.tv_more).let {
                        it.setOnClickListener { Toast.makeText(context, "换一批", Toast.LENGTH_LONG).show() }
                    }
                }
            }
            XmlyParams.TYPE_RECOMMEND_ALBUMS -> {
                if (data.item is DiscoveryRecommendAlbums) {
                    val discoveryRecommendAlbum = data.item as DiscoveryRecommendAlbums
                    holder.setText(R.id.tv_title, discoveryRecommendAlbum.displayCategoryName)
                    holder.getView<RecyclerView>(R.id.fl_recyclerView).let {
                        it.layoutManager = LinearLayoutManager(context as Activity, LinearLayoutManager.HORIZONTAL, false)
                        it.adapter = DiscoveryRecommendAlbumsAdapter(context, discoveryRecommendAlbum.albumList, R.layout.xmly_item_play_type_recommend_albums_stub)
                    }
                    holder.getView<TextView>(R.id.tv_more).let {
                        it.setOnClickListener { Toast.makeText(context, "更多", Toast.LENGTH_LONG).show() }
                    }
                }

            }
        }
    }
}