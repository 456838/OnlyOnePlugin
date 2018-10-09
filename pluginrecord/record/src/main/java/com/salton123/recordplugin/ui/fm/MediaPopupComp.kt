package com.salton123.recordplugin.ui.fm

import android.os.Bundle
import com.salton123.base.BaseSupportPopupFragment
import com.salton123.base.FragmentDelegate.Companion.ARG_ITEM
import com.salton123.recordplugin.R
import com.salton123.recordplugin.model.bean.VideoBean
import kotlinx.android.synthetic.main.record_comp_media_popup.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/8/22 7:03
 * ModifyTime: 7:03
 * Description:
 */
open class MediaPopupComp : BaseSupportPopupFragment() {
    override fun getLayout(): Int = R.layout.record_comp_media_popup
    lateinit var mVideoBean: VideoBean

    override fun initVariable(savedInstanceState: Bundle?) {
        mVideoBean = arguments!!.getParcelable(ARG_ITEM)
    }

    override fun initViewAndData() {
        videoPlayer?.let {
            it.updatePlayUrl(mVideoBean)
            it.start()
        }
        title_left_iv.setOnClickListener { dismissAllowingStateLoss() }
    }

    override fun onPause() {
        super.onPause()
        videoPlayer?.let {
            it.onPause()
        }
    }

    override fun onResume() {
        super.onResume()
        videoPlayer?.let {
            it.onResume()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoPlayer?.let { it.onDestroy() }
    }

}