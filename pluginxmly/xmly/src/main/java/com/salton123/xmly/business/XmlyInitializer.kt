package com.salton123.xmly.business

import android.content.Context
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager

/**
 * User: newSalton@outlook.com
 * Date: 2018/6/11 上午10:02
 * ModifyTime: 上午10:02
 * Description:
 */
object XmlyInitializer {
    lateinit var xmPlayerManager: XmPlayerManager
    fun initForApp(context: Context) {
        xmPlayerManager = XmPlayerManager.getInstance(context)
        xmPlayerManager.init()
    }

    fun deInit() {
        XmPlayerManager.release()
    }

}