package com.salton123.recordplugin.view.adapter

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import cn.sharerec.recorder.media.MP4
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.salton123.base.recyclerview.adapter.CommonAdapter
import com.salton123.recordplugin.R
import com.salton123.util.DateUtils
import com.salton123.util.FileUtils
import com.salton123.util.SizeUtils
import java.util.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/2 上午11:06
 * ModifyTime: 上午11:06
 * Description:
 */

class RecordMainRecAdapter(context: Context) : CommonAdapter<MP4>(context, R.layout.record_adapter_item_record_main) {
    override fun bindData(holder: ViewHolder, data: MP4, position: Int) {

        holder.setText(R.id.tvDurationAndLength,
            "${stringForTime(data.duration.toInt())} | ${SizeUtils.FormetFileSize(FileUtils.getFileSize(data.localPath))}")
            .setText(R.id.tvCreateTime, DateUtils.getDateTime(data.createTime))
        holder.getView<ImageView>(R.id.ivThumbnail).setImageBitmap(data.getThumb(0.5f))
    }

    private fun stringForTime(timeMs: Int): String {
        var mFormatBuilder = StringBuilder()
        var mFormatter = Formatter(mFormatBuilder, Locale.getDefault())
        val totalSeconds = timeMs / 1000
        val seconds = totalSeconds % 60
        val minutes = totalSeconds / 60 % 60
        val hours = totalSeconds / 3600
        mFormatBuilder.setLength(0)
        return if (hours > 0) {
            mFormatter.format("%d:%02d:%02d", hours, minutes, seconds).toString()
        } else {
            mFormatter.format("%02d:%02d", minutes, seconds).toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater?.inflate(layoutId, parent,false)
        return ViewHolder(view!!)
    }
}