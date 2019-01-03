package com.salton123.xmly.view.adapter

import android.content.Context
import android.widget.ImageView
import com.hazz.kotlinmvp.view.recyclerview.MultipleType
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.salton123.GlideApp
import com.salton123.xmly.R
import com.salton123.xmly.XmlyParams
import com.salton123.xmly.model.MultiTypeItem
import com.salton123.xmly.util.SizeUtils
import com.salton123.xmly.util.TrackUtil
import com.ximalaya.ting.android.opensdk.model.album.Album
import com.ximalaya.ting.android.opensdk.model.track.Track

/**
 * User: newSalton@outlook.com
 * Date: 2018/6/7 下午10:31
 * ModifyTime: 下午10:31
 * Description:
 */
class AlbumsDetailRecyclerAdapter(context: Context) : XRefreshRecyclerAdapter<MultiTypeItem>(context, object : MultipleType<MultiTypeItem> {
    override fun getLayoutId(item: MultiTypeItem, position: Int): Int {
        return when (item.viewType) {
            XmlyParams.TYPE_HEADER -> R.layout.xmly_view_album_header
            XmlyParams.TYPE_CONTENT -> R.layout.xmly_view_type_track_detail
            else -> 0
        }
    }

}) {
    override fun bindData(holder: ViewHolder, data: MultiTypeItem, position: Int) {
        when (data.viewType) {
            XmlyParams.TYPE_HEADER -> {
                var album = data.item as Album
                holder
                    .setText(R.id.tvTitle, album.albumTitle)
                    .setText(R.id.tvIntro, album.albumTags)
                    .setText(R.id.tvRichIntro, album?.albumRichIntro ?: "")
                    .setImagePath(R.id.blurImageView, object : ViewHolder.HolderImageLoader(album.coverUrlLarge) {
                        override fun loadImage(iv: ImageView, path: String) {
                            GlideApp.with(iv)
                                .asBitmap()
                                .load(album.coverUrlLarge)
                                .thumbnail(0.5f)
                                .into(iv)
                        }
                    })
            }
            XmlyParams.TYPE_CONTENT -> {
                var track = data.item as Track
                holder.setText(R.id.text_view_name, TrackUtil.getTrackTitle(track))
                holder.setText(R.id.text_view_artist, TrackUtil.getTrackIntro(track))
                holder.setText(R.id.text_view_duration, SizeUtils.FormetFileSize(track.downloadSize))
            }
        }
    }

    fun setContentData(trackList: MutableList<Track>) {
        trackList.forEach {
            add(MultiTypeItem(XmlyParams.TYPE_CONTENT, it))
            notifyItemInserted(getData().size)
        }
    }

}