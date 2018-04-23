package com.salton123.titeduplugin.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.salton123.base.BaseRecycerViewAdapter;
import com.salton123.titeduplugin.R;
import com.salton123.util.ViewUtils;
import com.salton123.util.log.MLog;
import com.shuyu.gsyimageloader.GSYImageLoader;
import com.shuyu.gsyimageloader.GSYImageLoaderManager;

import cn.edu.tit.module.model.bean.NewsTagInfo;

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/20 下午3:37
 * ModifyTime: 下午3:37
 * Description:
 */
public class TitEduMainPageAdapter extends BaseRecycerViewAdapter<NewsTagInfo, RecyclerView.ViewHolder> {

    private static final String TAG = "VideoPageAdapter";
    Typeface typeface;

    public TitEduMainPageAdapter(Context context) {
        super(context);
        typeface = Typeface.createFromAsset(context.getAssets(),
                "font/MgOpenCosmeticaBold.ttf");
    }

    @Override
    public RecyclerView.ViewHolder getCreateViewHolder(ViewGroup parent, int viewType) {
        MLog.info(TAG, "[getCreateViewHolder]");
        return new VideoItemHodler(inflater.inflate(R.layout.tit_item_video_page, parent, false));
    }


    @Override
    public void getBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VideoItemHodler) {
            MLog.info(TAG, "[getBindViewHolder]");
            VideoItemHodler itemHodler = (VideoItemHodler) holder;
            NewsTagInfo item = list.get(position);
            itemHodler.news_title.setText(item.getTitle() + "");
            itemHodler.news_short_content.setText(item.getTime() + "");
            itemHodler.item_textView3.setText((position + 1) + "");
            itemHodler.item_textView3.setTypeface(typeface);
            // GSYImageLoaderManager.Companion.getSInstance().loadImage();
        }
    }

    class VideoItemHodler extends RecyclerView.ViewHolder {
        TextView news_title;
        TextView news_short_content;
        TextView item_textView3;
        ImageView img_thu;

        public VideoItemHodler(View itemView) {
            super(itemView);
            news_title = ViewUtils.f(itemView, R.id.news_title);
            news_short_content = ViewUtils.f(itemView, R.id.news_short_content);
            item_textView3 = ViewUtils.f(itemView, R.id.item_textView3);
            img_thu = ViewUtils.f(itemView, R.id.img_thu);

        }
    }
}
