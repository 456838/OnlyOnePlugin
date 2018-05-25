package com.salton123.titeduplugin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.Toast
import cn.edu.tit.module.api.TitNewsCategory
import com.hazz.kotlinmvp.view.recyclerview.adapter.OnItemClickListener
import com.salton123.titeduplugin.model.NewsCategory
import com.salton123.titeduplugin.ui.aty.NewsListAty
import com.salton123.titeduplugin.view.adapter.MainRecyclerPageAdapter
import kotlinx.android.synthetic.main.tit_view_news_list_page.view.*
import java.util.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/24 下午3:28
 * ModifyTime: 下午3:28
 * Description:
 */
class MainRecyclerPage : LinearLayout {
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
        LayoutInflater.from(context).inflate(R.layout.tit_view_news_list_page, this, true)
        initData()
    }

    private lateinit var mAdapter: MainRecyclerPageAdapter

    private val names = arrayOf(
            "学院要闻", "学院通知", "学院公告", "系部动态", "处室动态", "校园文化")
    private val icons = intArrayOf(R.drawable.icon_shortcut_01, R.drawable.icon_shortcut_02, R.drawable.icon_shortcut_03,
            R.drawable.icon_shortcut_04, R.drawable.icon_shortcut_05, R.drawable.icon_shortcut_06)

    private lateinit var menus: ArrayList<NewsCategory>

    private fun initData() {
        mAdapter = MainRecyclerPageAdapter(context, R.layout.tit_item_main_recycler)
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        menus = ArrayList()
        menus.add(NewsCategory(R.drawable.icon_shortcut_01, "学院要闻", "", TitNewsCategory.CATEGORY_IMPORTANCE_NEWS))
        menus.add(NewsCategory(R.drawable.icon_shortcut_02, "学院通知", "", TitNewsCategory.CATEGORY_NOTIFICATION))
        menus.add(NewsCategory(R.drawable.icon_shortcut_03, "学院公告", "", TitNewsCategory.CATEGORY_NOTICE))
        menus.add(NewsCategory(R.drawable.icon_shortcut_04, "系部动态", "", TitNewsCategory.CATEGORY_DEPARTMENT_NEWS))
        menus.add(NewsCategory(R.drawable.icon_shortcut_05, "处室动态", "", TitNewsCategory.CATEGORY_OFFICE_NEWS))
        menus.add(NewsCategory(R.drawable.icon_shortcut_06, "校园文化", "", TitNewsCategory.CATEGORY_CAMPUS_CULTURE))
        mAdapter.addAll(menus)
        mAdapter.notifyDataSetChanged()
        mAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(obj: Any?, position: Int) {
                val intent = Intent()
                intent.setClass(context, NewsListAty::class.java)
                val bundle = Bundle()
                val newsCategory = obj as NewsCategory
                bundle.putInt("newsType", newsCategory.type)
                intent.putExtras(bundle)
                context.startActivity(intent)
                Toast.makeText(context, "click position=$position", Toast.LENGTH_LONG).show()
            }

        })
    }
}