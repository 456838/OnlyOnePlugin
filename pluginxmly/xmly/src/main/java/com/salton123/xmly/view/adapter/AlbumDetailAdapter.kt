package com.salton123.xmly.view.adapter

import android.content.Context
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.salton123.base.recyclerview.adapter.CommonAdapter
import com.salton123.xmly.R
import com.salton123.xmly.util.SizeUtils
import com.salton123.xmly.util.TrackUtil
import com.ximalaya.ting.android.opensdk.model.track.Track

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/29 下午3:25
 * ModifyTime: 下午3:25
 * Description:
 */
class AlbumDetailAdapter(context: Context) : CommonAdapter<Track>(context, R.layout.xmly_view_type_track_detail) {
    override fun bindData(holder: ViewHolder, data: Track, position: Int) {
        holder.setText(R.id.text_view_name, TrackUtil.getTrackTitle(data))
        holder.setText(R.id.text_view_artist, TrackUtil.getTrackIntro(data))
        holder.setText(R.id.text_view_duration, SizeUtils.FormetFileSize(data.downloadSize))
        holder.itemView.setOnClickListener {

        }
    }
}
