package com.salton123.videoplugin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.andview.refreshview.XRefreshViewHeader;
import com.salton123.util.ViewUtils;
import com.salton123.videoplugin.view.adapter.VideoPageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/20 下午3:29
 * ModifyTime: 下午3:29
 * Description:
 */
public class VideoPluginMainPage extends FrameLayout {
    private RecyclerView recyclerView;
    private VideoPageAdapter mAdapter;
    private XRefreshView xRefreshView;

    public VideoPluginMainPage(@NonNull Context context) {
        super(context);
        initView();
        initData();
    }

    public VideoPluginMainPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        initData();
    }

    public VideoPluginMainPage(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initData();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.page_video_plugin, this, true);
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

    List<String> data = new ArrayList<>();

    private void initData() {
        for (int i = 0; i < 10; i++) {
            data.add("hello" + i);
        }
        mAdapter = new VideoPageAdapter(getContext(), data);
        recyclerView.setAdapter(mAdapter);
        // mAdapter.addAll(data);
    }

}
