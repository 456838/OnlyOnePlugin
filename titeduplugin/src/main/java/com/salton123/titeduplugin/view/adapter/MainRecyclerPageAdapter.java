package com.salton123.titeduplugin.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hazz.kotlinmvp.view.recyclerview.MultipleType;
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder;
import com.salton123.GlideApp;
import com.salton123.base.BaseRecycerViewAdapter;
import com.salton123.base.recyclerview.adapter.CommonAdapter;
import com.salton123.titeduplugin.R;
import com.salton123.titeduplugin.model.NewsCategory;
import com.salton123.util.ViewUtils;
import com.salton123.util.log.MLog;

import cn.edu.tit.module.model.bean.NewsTagInfo;

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/20 下午3:37
 * ModifyTime: 下午3:37
 * Description: R.layout.tit_item_main_recycler
 */
public class MainRecyclerPageAdapter extends CommonAdapter<NewsCategory> {

    private static final String TAG = "VideoPageAdapter";
    Typeface typeface;

    public MainRecyclerPageAdapter(Context context, int layoutId) {
        super(context, layoutId);
        typeface = Typeface.createFromAsset(context.getAssets(),
                "font/MgOpenCosmeticaBold.ttf");
    }

    public MainRecyclerPageAdapter(Context context, MultipleType<? super NewsCategory> typeSupport) {
        super(context, typeSupport);
        typeface = Typeface.createFromAsset(context.getAssets(),
                "font/MgOpenCosmeticaBold.ttf");
    }

    @Override
    protected void bindData(ViewHolder viewHolder, NewsCategory newsCategory, int i) {
        viewHolder.setText(R.id.tv_menu_title,newsCategory.getMenuTitle())
        .setText(R.id.tv_menu_msg,newsCategory.getMenuMsg() + "")
        .setImagePath(R.id.iv_menu_icon, new ViewHolder.HolderImageLoader(newsCategory.getMenuIconRes()+"") {
            @Override
            public void loadImage(ImageView imageView, String s) {
                GlideApp.with(imageView).load(Integer.parseInt(s)).into(imageView);
            }
        });
    }

}
