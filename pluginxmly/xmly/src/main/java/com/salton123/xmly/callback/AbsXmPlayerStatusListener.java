package com.salton123.xmly.callback;

import com.salton123.util.LogUtils;
import com.salton123.util.MLog;
import com.ximalaya.ting.android.opensdk.model.PlayableModel;
import com.ximalaya.ting.android.opensdk.player.service.IXmPlayerStatusListener;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerException;

/**
 * User: newSalton@outlook.com
 * Date: 2018/6/13 下午2:38
 * ModifyTime: 下午2:38
 * Description:
 */
public abstract class AbsXmPlayerStatusListener implements IXmPlayerStatusListener {
    private String TAG;

    public AbsXmPlayerStatusListener(String from) {
        TAG = from;
    }

    @Override
    public void onPlayStart() {
        LogUtils.d("[onPlayStart]");
    }

    @Override
    public void onPlayPause() {
        LogUtils.d("[onPlayPause]");
    }

    @Override
    public void onPlayStop() {
        LogUtils.d("[onPlayStop]");
    }

    @Override
    public void onSoundPlayComplete() {
        LogUtils.d(TAG, "[onSoundPlayComplete]");
    }

    @Override
    public void onSoundPrepared() {
        LogUtils.d(TAG, "[onSoundPrepared]");
    }

    @Override
    public void onSoundSwitch(PlayableModel playableModel, PlayableModel playableModel1) {
        LogUtils.d(TAG, "[onSoundSwitch]");
    }

    @Override
    public void onBufferingStart() {
        LogUtils.d(TAG, "[onBufferingStart]");
    }

    @Override
    public void onBufferingStop() {
        LogUtils.d(TAG, "[onBufferingStop]");
    }

    @Override
    public void onBufferProgress(int i) {
        LogUtils.d(TAG, "[onBufferProgress] i=" + i);
    }

    @Override
    public void onPlayProgress(int i, int i1) {
        LogUtils.d(TAG, "[onPlayProgress] i=" + i + ",i1= " + i1);
    }

    @Override
    public boolean onError(XmPlayerException e) {
        MLog.error(TAG, "[onError] ex=" + e.getMessage());
        return false;
    }
}
