package com.salton123.onlyone.ui

import android.content.Intent
import android.os.Bundle
import com.brioal.bottomtab.entity.TabEntity
import com.qihoo360.replugin.RePlugin
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
import com.salton123.onlyone.R
import com.salton123.onlyone.ui.fm.FirstFragment
import com.salton123.onlyone.ui.fm.FourFragment
import com.salton123.onlyone.ui.fm.SecondFragment
import kotlinx.android.synthetic.main.fm_main.*
import me.yokeyword.fragmentation.SupportFragment


/**
 * User: 巫金生(newSalton@163.com)
 * Date: 2017/6/15 22:30
 * Description:
 * Updated:
 */
class MainFragment : BaseSupportFragment() {
    private val mList: MutableList<TabEntity> = ArrayList()
    private val mFragments = arrayOfNulls<SupportFragment>(4)
    private val zero = 0
    private val first = 1
    private val second = 2
    private val third = 3

    override fun getLayout(): Int {
        return R.layout.fm_main
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            mFragments[zero] = FragmentDelegate.newInstance(FirstFragment::class.java)
            mFragments[first] = FragmentDelegate.newInstance(SecondFragment::class.java)
            mFragments[second] = FragmentDelegate.newInstance(SecondFragment::class.java)
            mFragments[third] = FragmentDelegate.newInstance(FourFragment::class.java)
        } else {
            mFragments[zero] = findChildFragment(FirstFragment::class.java)
            mFragments[first] = findChildFragment(SecondFragment::class.java)
            mFragments[second] = findChildFragment(SecondFragment::class.java)
            mFragments[third] = findChildFragment(FourFragment::class.java)
        }
        mList.add(TabEntity(R.drawable.book_icon, "推荐"))
        mList.add(TabEntity(R.drawable.movie_icon, "游戏"))
        mList.add(TabEntity(R.drawable.music_icon, "软件"))
        mList.add(TabEntity(R.drawable.newspaper_icon, "音乐"))
//        mList.add(TabEntity(R.drawable.icon_5, "管理"))

    }

    override fun initViewAndData() {
        bottoNavigationBar.setList(mList) //设置数据源
        bottoNavigationBar.setNews(0, 1)
        loadMultipleRootFragment(R.id.fl_container, zero, mFragments[zero], mFragments[first], mFragments[second], mFragments[third])
    }

    override fun initListener() {
        bottoNavigationBar.setSelectedListener { position ->
//            if (position == 3) {
//                if (RePlugin.preload("musicplugin")) {
//                    RePlugin.startActivity(context, Intent(), "musicplugin", "io.github.ryanhoo.music.ui.main.MainActivity")
//                }
//            } else {
//
//            }
            showHideFragment(mFragments[position])
        }

    }
}
