package com.salton123.xmly.fm

import android.os.Bundle
import android.text.Html
import android.view.View
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
import com.salton123.xmly.R
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kotlinx.android.synthetic.main.xmly_cp_main.*


/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午4:19
 * ModifyTime: 下午4:19
 * Description:
 */
class XmlyComponent : BaseSupportFragment() {


    private val mXmlyTabPagerComponent by lazy { FragmentDelegate.newInstance(XmlyTabPagerComponent::class.java) }
    private val mMiniPlayerComponent by lazy { FragmentDelegate.newInstance(MiniPlayerComponent::class.java) }
    private val mMusicPlayerComponent by lazy { FragmentDelegate.newInstance(MusicPlayerComponent::class.java) }

    override fun initViewAndData() {
        if (findFragment(XmlyTabPagerComponent::class.java) == null) {
            loadRootFragment(R.id.fl_main_content, mXmlyTabPagerComponent)
            loadRootFragment(R.id.miniPlayerContainer, mMiniPlayerComponent)
            loadRootFragment(R.id.musicPlayerContainer, mMusicPlayerComponent)
        }
    }

    override fun getLayout(): Int = R.layout.xmly_cp_main


    override fun initVariable(savedInstanceState: Bundle?) {

    }

    override fun initListener() {
        slideUpdatePannel.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View?, slideOffset: Float) {
                miniPlayerContainer.alpha = 1 - slideOffset
            }

            override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {
            }
        })
        miniPlayerContainer.setOnClickListener { switchPanel() }
    }

    private fun switchPanel() {
        if (slideUpdatePannel.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            collapsePanel()
        } else {
            expandPanel()
        }
    }

    private fun collapsePanel() {
        slideUpdatePannel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED
    }

    private fun expandPanel() {
        slideUpdatePannel.panelState = SlidingUpPanelLayout.PanelState.EXPANDED
    }

    /**
     * 处理回退事件
     *
     * @return
     */
    override fun onBackPressedSupport(): Boolean {
        return if (slideUpdatePannel.panelState == SlidingUpPanelLayout.PanelState.EXPANDED) {
            collapsePanel()
            true
        } else {
            val builder = android.support.v7.app.AlertDialog.Builder(_mActivity)
            @Suppress("DEPRECATION")
            builder.setTitle("Hi").setMessage(Html.fromHtml("要退出App吗？")).setPositiveButton("嗯") { _, _ ->
                _mActivity.finish()
                System.exit(0)
            }.setNegativeButton("再看看", null)
                    .show()
            true
        }
    }
}