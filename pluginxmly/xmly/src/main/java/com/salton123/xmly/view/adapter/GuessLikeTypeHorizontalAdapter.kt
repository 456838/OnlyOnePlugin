package com.salton123.xmly.view.adapter

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.salton123.GlideApp
import com.salton123.base.FragmentDelegate
import com.salton123.base.recyclerview.adapter.CommonAdapter
import com.salton123.event.StartBrotherEvent
import com.salton123.util.EventUtil
import com.salton123.xmly.R
import com.salton123.xmly.fm.AlbumsComponent
import com.ximalaya.ting.android.opensdk.model.album.Album

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/26 上午11:52
 * ModifyTime: 上午11:52
 * Description:
 */
class GuessLikeTypeHorizontalAdapter(context: Context, layoutId: Int) : CommonAdapter<Album>(context, layoutId) {

    constructor(context: Context, list: MutableList<Album>, layoutId: Int) : this(context, layoutId) {
        getData().addAll(list)
    }

    override fun bindData(holder: ViewHolder, data: Album, position: Int) {
        holder.setImagePath(R.id.iv_cover_feed, object : ViewHolder.HolderImageLoader(data.coverUrlLarge) {
            override fun loadImage(iv: ImageView, path: String) {
                // 加载封页图
                GlideApp.with(context)
                        .load(path)
                        .placeholder(R.drawable.placeholder_banner)
                        .transition(DrawableTransitionOptions().crossFade())
                        .into(holder.getView(R.id.iv_cover_feed))
            }
        })
        holder.itemView.setOnClickListener { EventUtil.sendEvent(StartBrotherEvent(FragmentDelegate.newInstance(AlbumsComponent::class.java,
            Bundle().also { it.putParcelable("album", data) },null,null))) }
        //横向 RecyclerView 封页图下面标题
        holder.setText(R.id.tv_title, data.albumTitle)
        holder.setText(R.id.tv_tag, data.albumIntro)
    }
}

