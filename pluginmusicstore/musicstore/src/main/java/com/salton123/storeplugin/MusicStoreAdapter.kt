package com.salton123.storeplugin

import android.content.Context
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.salton123.GlideApp
import com.salton123.base.recyclerview.adapter.CommonAdapter
import com.salton123.storeplugin.bean.Data

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/5 5:06 PM
 * ModifyTime: 5:06 PM
 * Description:
 */
class MusicStoreAdapter(context: Context) : CommonAdapter<Data>(context, R.layout.adapter_item_music_store) {

    override fun bindData(holder: ViewHolder, data: Data, position: Int) {
        GlideApp.with(context).load(data.pic).into(holder.getView(R.id.ivThumbnail))
        holder.setText(R.id.tvCreateTime, data.singer)
            .setText(R.id.tvDurationAndLength, data.name)
    }
}