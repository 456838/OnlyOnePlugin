package com.salton123.xmly.model.type

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.salton123.util.ViewUtils
import com.salton123.xmly.R

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/25 下午8:10
 * ModifyTime: 下午8:10
 * Description:
 */
class RecommendAlbumsVH(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    var tv_title: TextView
    var tv_more: TextView
    var fl_recyclerView: RecyclerView

    init {
        tv_title = ViewUtils.f(itemView, R.id.tv_title)
        tv_more = ViewUtils.f(itemView, R.id.tv_more)
        fl_recyclerView = ViewUtils.f(itemView, R.id.fl_recyclerView)
    }

}