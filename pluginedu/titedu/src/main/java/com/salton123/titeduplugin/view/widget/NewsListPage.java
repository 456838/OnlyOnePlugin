package com.salton123.titeduplugin.view.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.andview.refreshview.XRefreshViewHeader;
import com.salton123.titeduplugin.R;
import com.salton123.titeduplugin.view.adapter.TitEduMainPageAdapter;
import com.salton123.util.RxUtils;
import com.salton123.util.ViewUtils;
import com.yy.mobile.memoryrecycle.views.YYFrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.edu.tit.module.api.TitNewsCategory;
import cn.edu.tit.module.config.DataOpService;
import cn.edu.tit.module.config.RetrofitHelper;
import cn.edu.tit.module.model.bean.NewsTagInfo;
import io.reactivex.functions.Consumer;

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/21 下午9:08
 * ModifyTime: 下午9:08
 * Description:
 */
public class NewsListPage extends YYFrameLayout {
    private RecyclerView recyclerView;
    private TitEduMainPageAdapter mAdapter;
    private XRefreshView xRefreshView;

    public NewsListPage(@NonNull Context context) {
        super(context);
        initView();
    }

    public NewsListPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public NewsListPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.tit_page_edu_plugin, this, true);
        recyclerView = ViewUtils.f(this, R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        xRefreshView = ViewUtils.f(this, R.id.xRefreshView);
        xRefreshView.setPullLoadEnable(true);
        xRefreshView.setCustomFooterView(new XRefreshViewFooter(getContext()));
        xRefreshView.setCustomHeaderView(new XRefreshViewHeader(getContext()));
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                Toast.makeText(getContext(), "isPullDown=" + isPullDown, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                Toast.makeText(getContext(), "isSilence=" + isSilence, Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void initData(@TitNewsCategory int category) {
        this.category = category;
        mAdapter = new TitEduMainPageAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
        HashMap<String, Object> map = new HashMap<>();
        RetrofitHelper.INSTANCE.getTitNewsApi()
                .getNewsCategory(category, page++, "NextPage")
                .compose(RxUtils.<String>schedulersTransformer())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        List<NewsTagInfo> newsTagInfos = DataOpService.INSTANCE.parserNewsTagInfo(s);
                        mAdapter.addAll(newsTagInfos);
                    }
                }, RxUtils.errorConsumer());
    }

    private @TitNewsCategory
    int category = TitNewsCategory.CATEGORY_IMPORTANCE_NEWS;
    int page = 0;

}
