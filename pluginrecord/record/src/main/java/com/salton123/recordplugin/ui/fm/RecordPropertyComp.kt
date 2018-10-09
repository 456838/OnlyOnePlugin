package com.salton123.recordplugin.ui.fm

import android.os.Bundle
import android.widget.Toast
import cn.sharerec.recorder.Recorder
import com.qmuiteam.qmui.widget.dialog.QMUIBottomSheet
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView
import com.qmuiteam.qmui.widget.grouplist.QMUIGroupListView
import com.salton123.base.BaseSupportFragment
import com.salton123.recordplugin.R
import com.salton123.recordplugin.compat.RecordPropertyCompat
import kotlinx.android.synthetic.main.record_comp_property.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/6 下午2:39
 * ModifyTime: 下午2:39
 * Description:
 */
class RecordPropertyComp : BaseSupportFragment() {
    override fun getLayout(): Int = R.layout.record_comp_property

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        val itemMaxFrameSize = groupListView.createItemView("分辨率")
        itemMaxFrameSize.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
        itemMaxFrameSize.orientation = QMUICommonListItemView.VERTICAL
        itemMaxFrameSize.detailText = "视频的宽、高像素数，越高视频越清晰"

        val itemVideoQuality = groupListView.createItemView("视频画质")
        itemVideoQuality.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON
        itemVideoQuality.orientation = QMUICommonListItemView.VERTICAL
        itemVideoQuality.detailText = "越高画质越好，生成的文件也会越大"
        QMUIGroupListView.newSection(context)
            .setTitle("录制参数")
            .addItemView(itemMaxFrameSize) { showMaxFrameSizeBottomDialog() }
            .addItemView(itemVideoQuality) { showVideoQualityBottomDialog() }
            .addTo(groupListView)
    }

    private fun showVideoQualityBottomDialog() {
        QMUIBottomSheet.BottomListSheetBuilder(activity)
            .setTitle("视频画质")
            .addItem("普通")
            .addItem("一般")
            .addItem("高清")
            .addItem("超清")
            .setOnSheetItemClickListener { dialog, _, position, tag ->
                dialog.dismiss()
                when (position) {
                    0 -> {
                        RecordPropertyCompat.videoQuality(Recorder.LevelVideoQuality.LEVEL_LOW)
                    }
                    1 -> {
                        RecordPropertyCompat.videoQuality(Recorder.LevelVideoQuality.LEVEL_MEDIUN)
                    }
                    2 -> {
                        RecordPropertyCompat.videoQuality(Recorder.LevelVideoQuality.LEVEL_HIGH)
                    }
                    3 -> {
                        RecordPropertyCompat.videoQuality(Recorder.LevelVideoQuality.LEVEL_VERY_HIGH)
                    }
                }
                Toast.makeText(activity, "已选择${tag}清晰度", Toast.LENGTH_SHORT).show()
            }
            .build()
            .show()
    }

    private fun showMaxFrameSizeBottomDialog() {
        QMUIBottomSheet.BottomListSheetBuilder(activity)
            .setTitle("分辨率")
            .addItem("800*480")
            .addItem("1280*720")
            .addItem("1920*1080")
            .setOnSheetItemClickListener { dialog, _, position, tag ->
                dialog.dismiss()
                when (position) {
                    0 -> {
                        RecordPropertyCompat.setMaxFrameSize(Recorder.LevelMaxFrameSize.LEVEL_800_480)
                    }
                    1 -> {
                        RecordPropertyCompat.setMaxFrameSize(Recorder.LevelMaxFrameSize.LEVEL_1280_720)
                    }
                    2 -> {
                        RecordPropertyCompat.setMaxFrameSize(Recorder.LevelMaxFrameSize.LEVEL_1920_1080)
                    }
                }
                Toast.makeText(activity, "已选择${tag}分辨率", Toast.LENGTH_SHORT).show()
            }
            .build()
            .show()
    }
}