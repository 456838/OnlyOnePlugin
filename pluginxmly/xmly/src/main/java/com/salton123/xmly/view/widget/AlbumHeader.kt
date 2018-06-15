package com.salton123.xmly.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.salton123.GlideApp
import com.salton123.xmly.R
import com.ximalaya.ting.android.opensdk.model.album.Album
import kotlinx.android.synthetic.main.xmly_view_album_header.view.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/6/5 下午9:53
 * ModifyTime: 下午9:53
 * Description:
 */
class AlbumHeader(context: Context, attributes: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attributes, defStyleAttr) {
    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0)

    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.xmly_view_album_header, this)
    }

    fun setData(album: Album) {
        GlideApp.with(this)
            .asBitmap()
            .load(album.coverUrlLarge)
            .thumbnail(0.5f)
            .into(blurImageView)
        tvTitle.text = album.albumTitle
        tvIntro.text = album.albumTags
        tvRichIntro.text = album.albumRichIntro
    }
}