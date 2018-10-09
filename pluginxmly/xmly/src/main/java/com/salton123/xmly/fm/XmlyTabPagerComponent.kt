package com.salton123.xmly.fm

import android.os.Bundle
import android.support.v4.app.Fragment
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
import com.salton123.xmly.R
import com.salton123.xmly.util.StatusBarUtil
import com.salton123.xmly.view.TabLayoutHelper
import com.salton123.xmly.view.adapter.BaseFragmentAdapter
import kotlinx.android.synthetic.main.xmly_cp_tab_pager.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/28 下午4:27
 * ModifyTime: 下午4:27
 * Description:
 */
class XmlyTabPagerComponent : BaseSupportFragment() {
    private val mRecommendComponent by lazy { FragmentDelegate.newInstance(RecommendComponent::class.java) }
    private val mAboutComponent by lazy { FragmentDelegate.newInstance(AboutComponent::class.java) }
    private val tabList = ArrayList<String>()
    private val fragments = ArrayList<Fragment>()

    override fun getLayout(): Int = R.layout.xmly_cp_tab_pager

    override fun initVariable(savedInstanceState: Bundle?) {
        tabList.add("关注")
        tabList.add("关于")
        fragments.add(mRecommendComponent)
        fragments.add(mAboutComponent)
    }

    override fun initViewAndData() {
        mViewPager.adapter = BaseFragmentAdapter(childFragmentManager, fragments, tabList)
        mTabLayout.setupWithViewPager(mViewPager)
        TabLayoutHelper.setUpIndicatorWidth(mTabLayout)
//        StatusBarUtil.darkMode(activity, Color.BLACK, 0.1f)
        StatusBarUtil.setPaddingSmart(_mActivity, mTabLayout)
    }
}