package com.salton123.xmly;

import android.os.Environment;

import com.salton123.base.GlobalParams;

import java.io.File;

/**
 * User: 巫金生(newSalton@outlook.com)
 * Date: 2017/7/19 14:37
 * Time: 14:37
 * Description:
 */
public abstract class XmlyParams extends GlobalParams {

    public static final String XM_CACHE_FILE_PATH = Environment.getDataDirectory() + File.separator + BASE_FOLDER + File.separator + "xmly" + File.separator;

    public static final String APP_SECRET = "4d8e605fa7ed546c4bcb33dee1381179";
    // public static final String APP_SECRET = "b617866c20482d133d5de66fceb37da3";

    public static final String CLOSE_ACTION = "com.app.test.android.Action_Close";

    public static final String PAUSE_START_ACTION = "com.app.test.android.Action_PAUSE_START";

    // 首页
    public static final int TYPE_BANNER = 0; //banner推荐
    public static final int TYPE_GUESS_LIKE = 1;     //猜你喜欢
    public static final int TYPE_RECOMMEND_ALBUMS = 2;     //推荐相册

    //相册页
    public static final int TYPE_HEADER = 0; //头部
    public static final int TYPE_CONTENT = 1;     //内容
    public static final int TYPE_FOOTER = 2;     //底部

    public static final String FLAG_PLAY_LIST = "flag_play_list";
    public static final String FLAG_PLAY_INDEX = "flag_play_index";

}
