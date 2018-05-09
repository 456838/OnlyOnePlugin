package com.salton123.titeduplugin.ui.aty

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import cn.edu.tit.module.api.TitNewsCategory
import cn.edu.tit.module.config.DataOpService
import cn.edu.tit.module.config.RetrofitHelper
import com.andview.refreshview.XRefreshView
import com.andview.refreshview.XRefreshViewFooter
import com.andview.refreshview.XRefreshViewHeader
import com.salton123.base.BaseSupportActivity
import com.salton123.base.FragmentDelegate
import com.salton123.titeduplugin.R
import com.salton123.titeduplugin.ui.fm.NewsDetailComponent
import com.salton123.titeduplugin.view.adapter.TitEduMainPageAdapter
import com.salton123.util.RxUtils
import com.salton123.util.log.MLog
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.tit_aty_new_list.*
import java.util.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/26 下午5:08
 * ModifyTime: 下午5:08
 * Description:
 */
class NewsListAty : BaseSupportActivity() {
    lateinit var mAdapter: TitEduMainPageAdapter

    @TitNewsCategory
    private var category = TitNewsCategory.CATEGORY_IMPORTANCE_NEWS
    private var page = 0

    override fun getLayout(): Int {
        return R.layout.tit_aty_new_list
    }

    override fun initVariable(savedInstanceState: Bundle?) {

    }

    override fun initViewAndData() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        xRefreshView.pullLoadEnable = true
        xRefreshView.setCustomFooterView(XRefreshViewFooter(this))
        xRefreshView.setCustomHeaderView(XRefreshViewHeader(this))
        xRefreshView.setXRefreshViewListener(object : XRefreshView.SimpleXRefreshListener() {

            override fun onRefresh(isPullDown: Boolean) {
                shortToast("isPullDown=$isPullDown")
            }

            override fun onLoadMore(isSilence: Boolean) {
                shortToast("isSilence=$isSilence")
            }
        })

        mAdapter = TitEduMainPageAdapter(this)
        recyclerView.adapter = mAdapter
        mAdapter.setOnItemClickListener { position, view, vh ->
            log(mAdapter.data[position].toString())
            var bundle = Bundle()
            bundle.putParcelable("data", mAdapter.data[position])
            FragmentDelegate.newInstance(NewsDetailComponent::class.java, bundle).show(supportFragmentManager, "NewsDetailComponent")

        }
        val map = HashMap<String, Any>()
        RetrofitHelper.getTitNewsApi()
                .getNewsCategory(category, page++, "NextPage")
                .compose(RxUtils.schedulersTransformer())
                .subscribe(Consumer { s ->
                    val newsTagInfos = DataOpService.parserNewsTagInfo(s)
                    mAdapter.addAll(newsTagInfos)
                }, RxUtils.errorConsumer())
    }


}