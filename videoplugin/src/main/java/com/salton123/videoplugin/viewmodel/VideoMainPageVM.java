package com.salton123.videoplugin.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.salton123.videoplugin.model.bean.VideoItem;

import java.util.List;

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/21 上午10:58
 * ModifyTime: 上午10:58
 * Description:
 */
public class VideoMainPageVM extends ViewModel {

    public List<LiveData<VideoItem>> mVideoItemData;


    public List<LiveData<VideoItem>> getVideoMainPageData(int page) {
        return null;
    }
}
