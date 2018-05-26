package com.salton123.xmly.view.adapter

import android.content.Context
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.salton123.base.recyclerview.adapter.CommonAdapter
import com.salton123.xmly.R
import com.ximalaya.ting.android.opensdk.model.album.CategoryRecommendAlbums

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/26 上午11:52
 * ModifyTime: 上午11:52
 * Description:
 */
class RecommendAlbumsTypeHorizontalAdapter(context: Context, layoutId: Int) : CommonAdapter<CategoryRecommendAlbums>(context, layoutId) {

    constructor(context: Context, list: MutableList<CategoryRecommendAlbums>, layoutId: Int) : this(context, layoutId) {
        getData().addAll(list)
    }

    override fun bindData(holder: ViewHolder, data: CategoryRecommendAlbums, position: Int) {
//        holder.setImagePath(R.id.iv_cover_feed, object : ViewHolder.HolderImageLoader(data.) {
//            override fun loadImage(iv: ImageView, path: String) {
//                // 加载封页图
//                GlideApp.with(context)
//                        .load(path)
//                        .placeholder(R.drawable.placeholder_banner)
//                        .transition(DrawableTransitionOptions().crossFade())
//                        .into(holder.getView(R.id.iv_cover_feed))
//            }
//        })

        //横向 RecyclerView 封页图下面标题
        holder.setText(R.id.tv_title, data.disPlayTagName)
        holder.setText(R.id.tv_tag, data.tagName)
    }
}

