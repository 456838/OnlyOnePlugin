package com.salton123.videoplugin.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.salton123.base.BaseRecycerViewAdapter;
import com.salton123.util.ViewUtils;
import com.salton123.util.log.MLog;
import com.salton123.videoplugin.R;

import java.util.List;

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/20 下午3:37
 * ModifyTime: 下午3:37
 * Description:
 */
public class VideoPageAdapter extends BaseRecycerViewAdapter<String, RecyclerView.ViewHolder> {

    private static final String TAG = "VideoPageAdapter";
    //
    // public VideoPageAdapter(Context context, List<String> list) {
    //     super(context, list);
    // }

    public VideoPageAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder getCreateViewHolder(ViewGroup parent, int viewType) {
        MLog.info(TAG, "[getCreateViewHolder]");
        return new VideoItemHodler(inflater.inflate(R.layout.item_video_page, parent, false));
    }


    @Override
    public void getBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof VideoItemHodler) {
            MLog.info(TAG, "[getBindViewHolder]");
            VideoItemHodler itemHodler = (VideoItemHodler) holder;
            itemHodler.tvHint.setText("" + getData().get(position));
        }
    }


    class VideoItemHodler extends RecyclerView.ViewHolder {
        TextView tvHint;

        public VideoItemHodler(View itemView) {
            super(itemView);
            tvHint = ViewUtils.f(itemView, R.id.tvHint);
        }
    }
}
