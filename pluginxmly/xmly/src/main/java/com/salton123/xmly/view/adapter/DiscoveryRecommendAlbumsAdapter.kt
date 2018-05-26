package com.salton123.xmly.view.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import android.widget.Toast
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.salton123.base.recyclerview.adapter.CommonAdapter
import com.salton123.xmly.R
import com.ximalaya.ting.android.opensdk.model.album.DiscoveryRecommendAlbums

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/26 下午6:26
 * ModifyTime: 下午6:26
 * Description:
 */
class DiscoveryRecommendAlbumsAdapter(context: Context, layoutId: Int) : CommonAdapter<DiscoveryRecommendAlbums>(context, layoutId) {

    override fun bindData(holder: ViewHolder, data: DiscoveryRecommendAlbums, position: Int) {
        holder.setText(R.id.tv_title, data.displayCategoryName)
        holder.getView<RecyclerView>(R.id.fl_recyclerView).let {
            it.layoutManager = LinearLayoutManager(context as Activity, LinearLayoutManager.HORIZONTAL, false)
            it.adapter = GuessLikeTypeHorizontalAdapter(context, data.albumList, R.layout.xmly_item_follow_horizontal)
        }
        holder.getView<TextView>(R.id.tv_more).let {
            it.setOnClickListener { Toast.makeText(context, "更多", Toast.LENGTH_LONG).show() }
        }
    }
}