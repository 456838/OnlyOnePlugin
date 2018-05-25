package com.salton123.onlyone.ui.fm

import android.os.Bundle
import com.salton123.base.BaseSupportFragment
import com.salton123.onlyone.R

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/20 下午5:46
 * ModifyTime: 下午5:46
 * Description:
 */
class FirstFragment : BaseSupportFragment() {


    override fun initVariable(savedInstanceState: Bundle?) {

    }

    override fun getLayout(): Int {
        return R.layout.fm_test
    }


    override fun initViewAndData() {
//        if (RePlugin.preload("videoplugin")) {
//            var videoLayout = RePlugin.fetchViewByLayoutName<LinearLayout>("videoplugin", "fm_video", null)
//            (f<FrameLayout>(R.id.fl_container)).addView(videoLayout)
//        }
    }
}
