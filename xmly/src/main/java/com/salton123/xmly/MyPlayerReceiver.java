package com.salton123.xmly;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;

/**
 * Created by le.xin on 2017/3/23.
 */

public class MyPlayerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("MyPlayerReceiver.onReceive " + intent);
        if (intent.getAction().equals(XmlyParams.CLOSE_ACTION)) {
            XmPlayerManager.release();
        } else if (intent.getAction().equals(XmlyParams.PAUSE_START_ACTION)) {
            if (XmPlayerManager.getInstance(context).isPlaying()) {
                XmPlayerManager.getInstance(context).pause();
            } else {
                XmPlayerManager.getInstance(context).play();
            }
        }
    }
}
