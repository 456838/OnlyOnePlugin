package com.salton123.recordplugin.ui.fm

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cn.sharerec.recorder.Recorder
import com.qmuiteam.qmui.util.QMUIResHelper
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
    //    private lateinit var itemMaxFrameSize: QMUICommonListItemView
//    private lateinit var itemVideoQuality: QMUICommonListItemView
    private lateinit var maxFrameAccessoryView: View
    private lateinit var qualityAccessoryView: View
    override fun getLayout(): Int = R.layout.record_comp_property

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    private fun createAccessView(text: String): View {
        var accessView = View.inflate(_mActivity, R.layout.view_assessory, null)
        accessView.findViewById<TextView>(R.id.tvHint).text = text
        accessView.findViewById<ImageView>(R.id.ivAct)
            .setImageDrawable(QMUIResHelper
                .getAttrDrawable(context, R.attr.qmui_common_list_item_chevron)
            )
        return accessView
    }

    override fun initViewAndData() {
        maxFrameAccessoryView = createAccessView(getPixel(RecordPropertyCompat.mProperty.maxFrameSize))
        val itemMaxFrameSize = groupListView.createItemView("分辨率")
        itemMaxFrameSize.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM
        itemMaxFrameSize.addAccessoryCustomView(maxFrameAccessoryView)
        itemMaxFrameSize.orientation = QMUICommonListItemView.VERTICAL
        itemMaxFrameSize.detailText = "视频的宽、高像素数，越高视频越清晰"

        qualityAccessoryView = createAccessView(getQuality(RecordPropertyCompat.mProperty.videoQuality))
        val itemVideoQuality = groupListView.createItemView("视频画质")
        itemVideoQuality.accessoryType = QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM
        itemVideoQuality.orientation = QMUICommonListItemView.VERTICAL
        itemVideoQuality.addAccessoryCustomView(qualityAccessoryView)
        itemVideoQuality.detailText = "越高画质越好，生成的文件也会越大"
        QMUIGroupListView.newSection(context)
            .setTitle("录制参数")
            .addItemView(itemMaxFrameSize) { showMaxFrameSizeBottomDialog() }
            .addItemView(itemVideoQuality) { showVideoQualityBottomDialog() }
            .addTo(groupListView)
    }


    private fun getQuality(videoQuality: Int): String {
        return when (videoQuality) {
            Recorder.LevelVideoQuality.LEVEL_VERY_HIGH.ordinal -> {
                "超清"
            }
            Recorder.LevelVideoQuality.LEVEL_MEDIUN.ordinal -> {
                "一般"
            }
            Recorder.LevelVideoQuality.LEVEL_HIGH.ordinal -> {
                "高清"
            }
            else -> {
                "普通"
            }
        }
    }

    private fun getVideoQualityCheckIndex(): Int {
        return when (getQuality(RecordPropertyCompat.mProperty.videoQuality)) {
            "普通" -> return 0
            "一般" -> return 1
            "高清" -> return 2
            "超清" -> return 3
            else -> 0
        }
    }

    private fun showVideoQualityBottomDialog() {
        QMUIBottomSheet.BottomListSheetBuilder(activity, true)
            .setTitle("视频画质")
            .addItem("普通")
            .addItem("一般")
            .addItem("高清")
            .addItem("超清")
            .setCheckedIndex(getVideoQualityCheckIndex())
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
//                Toast.makeText(activity, "已选择${tag}清晰度", Toast.LENGTH_SHORT).show()
                qualityAccessoryView.findViewById<TextView>(R.id.tvHint).text = tag
            }
            .build()
            .show()
    }

    private fun getMaxFrameCheckIndex(): Int {
        return when (getPixel(RecordPropertyCompat.mProperty.maxFrameSize)) {
            "800*480" -> return 0
            "1280*720" -> return 1
            "1920*1080" -> return 2
            else -> 0
        }
    }

    private fun getPixel(pixel: Int): String {
        return when (pixel) {
            Recorder.LevelMaxFrameSize.LEVEL_800_480.pixels -> {
                "800*480"
            }
            Recorder.LevelMaxFrameSize.LEVEL_1280_720.pixels -> {
                "1280*720"
            }
            Recorder.LevelMaxFrameSize.LEVEL_1920_1080.pixels -> {
                "1920*1080"
            }
            else -> {
                "800*480"
            }
        }
    }


    private fun showMaxFrameSizeBottomDialog() {
        QMUIBottomSheet.BottomListSheetBuilder(activity, true)
            .setTitle("分辨率")
            .addItem("800*480")
            .addItem("1280*720")
            .addItem("1920*1080")
            .setCheckedIndex(getMaxFrameCheckIndex())
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
                maxFrameAccessoryView.findViewById<TextView>(R.id.tvHint).text = tag
//                Toast.makeText(activity, "已选择${tag}分辨率", Toast.LENGTH_SHORT).show()
            }
            .build()
            .show()
    }
}