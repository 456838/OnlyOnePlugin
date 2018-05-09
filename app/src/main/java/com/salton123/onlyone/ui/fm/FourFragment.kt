package com.salton123.onlyone.ui.fm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.qihoo360.replugin.RePlugin
import com.salton123.onlyone.R
import me.yokeyword.fragmentation.SupportFragment

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/20 下午5:55
 * ModifyTime: 下午5:55
 * Description:
 */
class FourFragment : SupportFragment() {
    lateinit var contentView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        contentView = inflater?.inflate(R.layout.salton_fm_container, null, false)!!
        return contentView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (RePlugin.preload("musicplugin")) {
            var videoLayout = RePlugin.fetchViewByLayoutName<FrameLayout>("musicplugin", "plugin_music_main", null)
            (contentView as ViewGroup).addView(videoLayout)
        }
    }

}