package com.salton123.recordplugin.livedata

import android.arch.lifecycle.LiveData
import cn.sharerec.recorder.media.MP4
import com.salton123.core.CoreManager
import com.salton123.recordplugin.core.IRecorderCore

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/2 上午10:47
 * ModifyTime: 上午10:47
 * Description:
 */
class RecordMainLiveData : LiveData<Array<MP4>>() {

    public override fun onActive() {
        super.onActive()
        value = CoreManager.getCore(IRecorderCore::class.java).listVideos()
    }
}