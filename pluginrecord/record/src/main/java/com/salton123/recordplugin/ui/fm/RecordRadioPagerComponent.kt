package com.salton123.recordplugin.ui.fm

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.widget.CompoundButton
import android.widget.RadioButton
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
import com.salton123.recordplugin.R
import com.salton123.recordplugin.view.adapter.BaseFragmentAdapter
import com.salton123.util.ScreenUtils
import kotlinx.android.synthetic.main.record_cp_radio_pager.*
import kotlinx.android.synthetic.main.record_layout_main_custom_toolbar.*
import me.yokeyword.fragmentation.SupportFragment

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/28 下午9:36
 * ModifyTime: 下午9:36
 * Description:
 */
class RecordRadioPagerComponent : BaseSupportFragment(), CompoundButton.OnCheckedChangeListener {
    internal val DEFAULT_PAGE_INDEX = 0
    private val mRecommendComponent by lazy { FragmentDelegate.newInstance(RecordMainComp::class.java) }
    private val mAboutComponent by lazy { FragmentDelegate.newInstance(RecordPropertyComp::class.java) }
    private var radioButtons: MutableList<RadioButton> = ArrayList()
    private val mTabList: MutableList<Pair<SupportFragment, String>> = mutableListOf(
        Pair(mRecommendComponent, "推荐"),
        Pair(mAboutComponent, "关于")
    )

    override fun getLayout(): Int = R.layout.record_cp_radio_pager


    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        radioButtons.add(radio_button_play_list)
        radioButtons.add(radio_button_music)
        radio_button_play_list.setOnCheckedChangeListener(this)
        radio_button_music.setOnCheckedChangeListener(this)
        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, mTabList.map { it.first }, mTabList.map { it.second })
        mViewPager.offscreenPageLimit = mTabList.size - 1
        mViewPager.pageMargin = ScreenUtils.dp2px(16f)
        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Empty
            }

            override fun onPageScrollStateChanged(state: Int) {
                // Empty
            }

            override fun onPageSelected(position: Int) {
                radioButtons[position].isChecked = true
            }
        })

        radioButtons[DEFAULT_PAGE_INDEX].isChecked = true
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            onItemChecked(radioButtons.indexOf(buttonView))
        }
    }


    private fun onItemChecked(position: Int) {
        mViewPager.currentItem = position
        toolbar.title = mTabList.map { it.second }[position]
    }
}