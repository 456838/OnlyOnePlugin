package com.salton123.xmly.fm

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
import com.salton123.callback.SingleClickListener
import com.salton123.xmly.R
import com.salton123.xmly.business.XmlyInitializer
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
    private val mXmlyRadioPagerComponent by lazy { FragmentDelegate.newInstance(XmlyRadioPagerComponent::class.java) }
    private val mMiniPlayerComponent by lazy { FragmentDelegate.newInstance(MiniPlayerComponent::class.java) }
    private val mMusicPlayerComponent by lazy { FragmentDelegate.newInstance(MusicPlayerComponent::class.java) }
    private var pagerType = 0
    override fun initViewAndData() {
        pagerType = (Math.random() * 10 % 2).toInt()
        if (pagerType == 0) {
            if (findFragment(MusicPlayerComponent::class.java) == null) {
                loadRootFragment(R.id.fl_main_content, mXmlyTabPagerComponent)
                loadRootFragment(R.id.miniPlayerContainer, mMiniPlayerComponent)
                loadRootFragment(R.id.musicPlayerContainer, mMusicPlayerComponent)
            }
        } else {
            if (findFragment(MusicPlayerComponent::class.java) == null) {
                loadRootFragment(R.id.fl_main_content, mXmlyRadioPagerComponent)
                loadRootFragment(R.id.miniPlayerContainer, mMiniPlayerComponent)
                loadRootFragment(R.id.musicPlayerContainer, mMusicPlayerComponent)
            }
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
        slideUpdatePannel.setFadeOnClickListener({ slideUpdatePannel.panelState = SlidingUpPanelLayout.PanelState.COLLAPSED })
        miniPlayerContainer.setOnClickListener(object : SingleClickListener(1000) {
            override fun onSingleClick(v: View) {
                switchPanel()
            }
        })
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
            builder
                .setTitle("Hi")
                .setMessage("要退出App吗？")
                .setPositiveButton("嗯") { _, _ ->
                    XmlyInitializer.deInit()
                    _mActivity.finish()
                    System.exit(0)
                }.setNegativeButton("再看看", null)
                .setNeutralButton("最小化") { _, _ ->
                    val small = Intent(Intent.ACTION_MAIN)
                    small.addCategory(Intent.CATEGORY_HOME)
                    small.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(small)
                }
                .show()
            true
        }
    }

}