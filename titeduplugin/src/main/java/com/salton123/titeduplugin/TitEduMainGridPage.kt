package com.salton123.titeduplugin

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.AttributeSet
import android.util.Pair
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.salton123.base.FragmentDelegate
import com.salton123.titeduplugin.ui.fm.FirstPage
import com.salton123.titeduplugin.view.adapter.MainPagerAdapter
import kotlinx.android.synthetic.main.tit_view_edu_main_grid.view.*
import java.util.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/23 下午3:12
 * ModifyTime: 下午3:12
 * Description:
 */
class TitEduMainGridPage : LinearLayout {
    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initView()
    }

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.tit_view_edu_main_grid, this, true)
        initData()
    }

    private lateinit var mAdapter: MainPagerAdapter
    private fun initData() {
        var fragmentActivity = context as FragmentActivity
        mAdapter = MainPagerAdapter(fragmentActivity.supportFragmentManager)
        viewPager.adapter = mAdapter
        viewPager.offscreenPageLimit = 5
        tabLayout.setupWithViewPager(viewPager)
        val mData = ArrayList<Pair<Fragment, String>>()
        mData.add(Pair(FragmentDelegate.newInstance(FirstPage::class.java, "郭德纲"), "郭德纲"))
        mData.add(Pair(FragmentDelegate.newInstance(FirstPage::class.java, "岳云鹏"), "岳云鹏"))
        mData.add(Pair(FragmentDelegate.newInstance(FirstPage::class.java, "高晓松"), "高晓松"))
        mData.add(Pair(FragmentDelegate.newInstance(FirstPage::class.java, "吴晓波"), "吴晓波"))
        mData.add(Pair(FragmentDelegate.newInstance(FirstPage::class.java, "采采"), "段子来了"))
        mAdapter.setData(mData)
    }
}