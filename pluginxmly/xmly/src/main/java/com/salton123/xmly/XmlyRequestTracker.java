package com.salton123.xmly;

import com.ximalaya.ting.android.player.Logger;
import com.ximalaya.ting.android.sdkdownloader.http.RequestParams;
import com.ximalaya.ting.android.sdkdownloader.http.app.RequestTracker;
import com.ximalaya.ting.android.sdkdownloader.http.request.UriRequest;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午5:21
 * ModifyTime: 下午5:21
 * Description:
 */
public class XmlyRequestTracker implements RequestTracker {
    @Override
    public void onWaiting(RequestParams params) {
        Logger.log("TingApplication : onWaiting " + params);
    }

    @Override
    public void onStart(RequestParams params) {
        Logger.log("TingApplication : onStart " + params);
    }

    @Override
    public void onRequestCreated(UriRequest request) {
        Logger.log("TingApplication : onRequestCreated " + request);
    }

    @Override
    public void onSuccess(UriRequest request, Object result) {
        Logger.log("TingApplication : onSuccess " + request + "   result = " + result);
    }

    @Override
    public void onRemoved(UriRequest request) {
        Logger.log("TingApplication : onRemoved " + request);
    }

    @Override
    public void onCancelled(UriRequest request) {
        Logger.log("TingApplication : onCanclelled " + request);
    }

    @Override
    public void onError(UriRequest request, Throwable ex, boolean isCallbackError) {
        Logger.log("TingApplication : onError " + request + "   ex = " + ex + "   isCallbackError = " + isCallbackError);
    }

    @Override
    public void onFinished(UriRequest request) {
        Logger.log("TingApplication : onFinished " + request);
    }
}
