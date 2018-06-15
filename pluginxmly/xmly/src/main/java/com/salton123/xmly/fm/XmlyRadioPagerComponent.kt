package com.salton123.xmly.fm

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.widget.CompoundButton
import android.widget.RadioButton
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
import com.salton123.xmly.R
import com.salton123.xmly.view.adapter.BaseFragmentAdapter
import kotlinx.android.synthetic.main.xmly_layout_main_custom_toolbar.*
import kotlinx.android.synthetic.main.xmly_cp_radio_pager.*
import me.yokeyword.fragmentation.SupportFragment

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/28 下午9:36
 * ModifyTime: 下午9:36
 * Description:
 */
class XmlyRadioPagerComponent : BaseSupportFragment(), CompoundButton.OnCheckedChangeListener {
    internal val DEFAULT_PAGE_INDEX = 0
    private val mRecommendComponent by lazy { FragmentDelegate.newInstance(RecommendComponent::class.java) }
    private val mAboutComponent by lazy { FragmentDelegate.newInstance(AboutComponent::class.java) }
    private var radioButtons: MutableList<RadioButton> = ArrayList()
    private val mTabList: MutableList<Pair<SupportFragment, String>> = mutableListOf(
            Pair(mRecommendComponent, "推荐"),
            Pair(mAboutComponent, "关于")
    )

    override fun getLayout(): Int = R.layout.xmly_cp_radio_pager


    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        radioButtons.add(radio_button_play_list)
        radioButtons.add(radio_button_music)
//        radioButtons.add(radio_button_local_files)
//        radioButtons.add(radio_button_settings)
        radio_button_play_list.setOnCheckedChangeListener(this)
        radio_button_music.setOnCheckedChangeListener(this)
//        radio_button_local_files.setOnCheckedChangeListener(this)
//        radio_button_settings.setOnCheckedChangeListener(this)
        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, mTabList.map { it.first }, mTabList.map { it.second })
        mViewPager.offscreenPageLimit = mTabList.size - 1
        mViewPager.pageMargin = resources.getDimensionPixelSize(R.dimen.mp_margin_large)
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