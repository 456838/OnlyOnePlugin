package com.salton123.xmly.model;


/**
 * User: newSalton@outlook.com
 * Date: 2018/6/11 下午2:38
 * ModifyTime: 下午2:38
 * Description:
 */
public class PlayHistory {

    public int break_second;

    public long trackId;

    public String mTracks;

    public PlayHistory(long trackId, int break_second, String tracks) {
        this.trackId = trackId;
        this.mTracks = tracks;
        this.break_second = break_second;
    }
}