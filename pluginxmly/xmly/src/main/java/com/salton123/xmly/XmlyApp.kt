package com.salton123.xmly

import android.app.PendingIntent
import android.content.Intent
import com.salton123.baselib.GlossyApplication
import com.salton123.util.MLog
import com.salton123.xmly.business.XmlyInitializer
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest
import com.ximalaya.ting.android.opensdk.player.appnotification.XmNotificationCreater
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerConfig
import com.ximalaya.ting.android.opensdk.util.BaseUtil
import com.ximalaya.ting.android.sdkdownloader.XmDownloadManager
import io.paperdb.Paper
import me.yokeyword.fragmentation.Fragmentation


/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午4:33
 * ModifyTime: 下午4:33
 * Description:
 */
class XmlyApp : GlossyApplication() {

    override fun onCreate() {
        super.onCreate()
        MLog.info("aa", "" + System.currentTimeMillis())
        initXmly()
        cusFont()
        initLog()
        Fragmentation.FragmentationBuilder().debug(true).stackViewMode(Fragmentation.BUBBLE).install()
    }

    fun initXmly() {
        MLog.info("aa", "" + System.currentTimeMillis())
//        CommonRequest.getInstanse().setPackid("com.app.test.android")
        CommonRequest.getInstanse()
            .init(this, XmlyParams.APP_SECRET)
        if (BaseUtil.isMainProcess(this)) {
            XmDownloadManager.Builder(this)
                .maxDownloadThread(1)            // 最大的下载个数 默认为1 最大为3
                .maxSpaceSize(java.lang.Long.MAX_VALUE)    // 设置下载文件占用磁盘空间最大值，单位字节。不设置没有限制
                .connectionTimeOut(15000)        // 下载时连接超时的时间 ,单位毫秒 默认 30000
                .readTimeOut(15000)                // 下载时读取的超时时间 ,单位毫秒 默认 30000
                .fifo(false)                    // 等待队列的是否优先执行先加入的任务. false表示后添加的先执行(不会改变当前正在下载的音频的状态) 默认为true
                .maxRetryCount(3)                // 出错时重试的次数 默认2次
                .progressCallBackMaxTimeSpan(1000)//  进度条progress 更新的频率 默认是800
                .requestTracker(XmlyRequestTracker())    // 日志 可以打印下载信息
//                    .savePath(XmlyParams.XM_CACHE_FILE_PATH)    // 保存的地址 会检查这个地址是否有效
                .create()
        }

        if (BaseUtil.getCurProcessName(this).contains(":player")) {
            val instanse = XmNotificationCreater.getInstanse(this)
            instanse.setNextPendingIntent(null as PendingIntent?)
            instanse.setPrePendingIntent(null as PendingIntent?)

            val actionName = XmlyParams.CLOSE_ACTION
            val intent = Intent(actionName)
            intent.setClass(this, MyPlayerReceiver::class.java)
            val broadcast = PendingIntent.getBroadcast(this, 0, intent, 0)
            instanse.setClosePendingIntent(broadcast)

            val pauseActionName = XmlyParams.PAUSE_START_ACTION
            val intent1 = Intent(pauseActionName)
            intent1.setClass(this, MyPlayerReceiver::class.java)
            val broadcast1 = PendingIntent.getBroadcast(this, 0, intent1, 0)
            instanse.setStartOrPausePendingIntent(broadcast1)
        }
        // 注意此方法需在CommonRequest XmPlayerManager 初始化之前设置
        XmPlayerConfig.getInstance(this).isUseSystemLockScreen = true
        XmlyInitializer.initForApp(this)
        Paper.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        MLog.error("aa", "[onTerminate]")

    }

}
