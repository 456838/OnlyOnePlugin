package com.salton123.onlyone.ui

import android.os.Bundle
import com.salton123.base.BaseSupportFragment
import com.salton123.onlyone.R
import com.brioal.bottomtab.entity.TabEntity
import com.brioal.bottomtab.interfaces.OnTabSelectedListener
import com.salton123.base.FragmentDelegate
import com.salton123.onlyone.ui.fm.FirstFragment
import com.salton123.onlyone.ui.fm.SecondFragment
import kotlinx.android.synthetic.main.fm_main.*
import me.yokeyword.fragmentation.SupportFragment


/**
 * User: 巫金生(newSalton@163.com)
 * Date: 2017/6/15 22:30
 * Description:
 * Updated:
 */
@Deprecated
class MainFragment : BaseSupportFragment() {
    val mList: MutableList<TabEntity> = ArrayList()
    private val mFragments = arrayOfNulls<SupportFragment>(4)
    private val ZERO = 0
    private val FIRST = 1
    private val SECOND = 2
    private val THIRD = 3

    override fun getLayout(): Int {
        return R.layout.fm_main
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            mFragments[ZERO] = FragmentDelegate.newInstance(FirstFragment::class.java)
            mFragments[FIRST] = FragmentDelegate.newInstance(SecondFragment::class.java)
            mFragments[SECOND] = FragmentDelegate.newInstance(SecondFragment::class.java)
            mFragments[THIRD] = FragmentDelegate.newInstance(SecondFragment::class.java)
        } else {
            mFragments[ZERO] = findChildFragment(FirstFragment::class.java)
            mFragments[FIRST] = findChildFragment(SecondFragment::class.java)
            mFragments[SECOND] = findChildFragment(SecondFragment::class.java)
            mFragments[THIRD] = findChildFragment(SecondFragment::class.java)
        }
        mList.add(TabEntity(R.drawable.book_icon, "推荐"))
        mList.add(TabEntity(R.drawable.movie_icon, "游戏"))
        mList.add(TabEntity(R.drawable.music_icon, "软件"))
        mList.add(TabEntity(R.drawable.newspaper_icon, "应用圈"))
//        mList.add(TabEntity(R.drawable.icon_5, "管理"))

    }

    override fun initViewAndData() {
        bottoNavigationBar.setList(mList) //设置数据源
        bottoNavigationBar.setNews(0, 1)
        loadMultipleRootFragment(R.id.fl_container, ZERO, mFragments[ZERO], mFragments[FIRST], mFragments[SECOND], mFragments[THIRD])
    }

    override fun initListener() {
        bottoNavigationBar.setSelectedListener { position -> showHideFragment(mFragments[position]) }
    }

}
