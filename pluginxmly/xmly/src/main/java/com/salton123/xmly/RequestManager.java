package com.salton123.xmly;

import android.support.annotation.Nullable;

import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.banner.BannerV2List;

import java.util.HashMap;
import java.util.Map;

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午6:24
 * ModifyTime: 下午6:24
 * Description:
 */
public class RequestManager {
    public static void getCategoryBannersV2() {
        Map<String, String> map = new HashMap<>();
        CommonRequest.getCategoryBannersV2(map, new IDataCallBack<BannerV2List>() {
            @Override
            public void onSuccess(@Nullable BannerV2List bannerV2List) {

            }

            @Override
            public void onError(int i, String s) {

            }
        });
        // val map = HashMap<String, String>()
        // map[DTransferConstants.CHANNEL] = ""
        // map[DTransferConstants.APP_VERSION] = ""
        // map[DTransferConstants.IMAGE_SCALE] = "2"
        // CommonRequest.getCategoryBannersV2(map, object :IDataCallBack<BannerV2List> {
        //     override fun onSuccess(p0: BannerV2List?) {
        //     }
        //
        //     override fun onError(p0: Int, p1: String?) {
        //     }
        // })
    }
}
