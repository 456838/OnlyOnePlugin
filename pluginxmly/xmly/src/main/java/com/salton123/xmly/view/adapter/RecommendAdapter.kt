package com.salton123.xmly.view.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.Toast
import com.salton123.base.BaseRecycerViewAdapter
import com.salton123.xmly.R
import com.salton123.xmly.model.type.GuessLikePlayType
import com.salton123.xmly.model.type.GuessLikeVH
import com.salton123.xmly.model.type.MultiTypeItem
import com.salton123.xmly.model.type.MultiTypeItem.TYPE_GUESSLIKE
import com.salton123.xmly.model.type.RecommendAlbumsPlayType
import com.salton123.xmly.model.type.RecommendAlbumsVH

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/25 下午6:16
 * ModifyTime: 下午6:16
 * Description:
 */
class RecommendAdapter(context: Context) : BaseRecycerViewAdapter<MultiTypeItem, RecyclerView.ViewHolder>(context) {


    init {

    }

    override fun getItemViewType(position: Int): Int {
        return data[position].viewType
    }

    override fun getBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder?.itemViewType) {
            TYPE_GUESSLIKE -> {
                if (holder is GuessLikeVH) {
                    if (data[position].item is GuessLikePlayType) {
                        val item = data[position].item as GuessLikePlayType
                        holder.tv_title.text = item.title
                        holder.tv_more.setOnClickListener { Toast.makeText(context, "更多", Toast.LENGTH_LONG).show() }
                        holder.fl_recyclerView.layoutManager = LinearLayoutManager(context as Activity, LinearLayoutManager.HORIZONTAL, false)
                        holder.fl_recyclerView.adapter = GuessLikeTypeHorizontalAdapter(context, item.data.albumList, R.layout.xmly_item_follow_horizontal)
                    }
                }
            }
            MultiTypeItem.TYPE_COM or MultiTypeItem.TYPE_GONGKAIKE or MultiTypeItem.TYPE_TELE or MultiTypeItem.TYPE_TALK_SHOW or MultiTypeItem.TYPE_XIANGSHENG or MultiTypeItem.TYPE_MUSIC -> {
                if (holder is RecommendAlbumsVH) {
                    val item = data[position].item as RecommendAlbumsPlayType
                    holder.tv_title.text = item.title
                    holder.tv_more.setOnClickListener { Toast.makeText(context, "更多", Toast.LENGTH_LONG).show() }
                    holder.fl_recyclerView.layoutManager = LinearLayoutManager(context as Activity, LinearLayoutManager.HORIZONTAL, false)
                    holder.fl_recyclerView.adapter = RecommendAlbumsTypeHorizontalAdapter(context, item.data.categoryRecommendAlbumses, R.layout.xmly_item_follow_horizontal)
                }
            }
        }
    }

    override fun getCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MultiTypeItem.TYPE_GUESSLIKE -> GuessLikeVH(inflater.inflate(R.layout.xmly_item_play_type_guess_like, parent, false))
            MultiTypeItem.TYPE_COM or MultiTypeItem.TYPE_GONGKAIKE or MultiTypeItem.TYPE_TELE or MultiTypeItem.TYPE_TALK_SHOW or MultiTypeItem.TYPE_XIANGSHENG or MultiTypeItem.TYPE_MUSIC ->
                RecommendAlbumsVH(inflater.inflate(R.layout.xmly_item_play_type_guess_like, parent, false))
            else -> GuessLikeVH(inflater.inflate(R.layout.xmly_item_play_type_guess_like, parent, false))
        }
    }

    fun add(item: MultiTypeItem) {
        var findRet = data.find { it.viewType == item.viewType }
        if (findRet != null) {
            data.remove(findRet)
        }
        data.add(item)
//        data.sort()
        notifyDataSetChanged()
    }

}