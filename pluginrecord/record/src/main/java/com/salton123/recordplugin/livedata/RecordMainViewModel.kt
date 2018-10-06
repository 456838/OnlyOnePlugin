package com.salton123.recordplugin.livedata

import android.arch.lifecycle.ViewModel

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/2 上午12:04
 * ModifyTime: 上午12:04
 * Description:
 */
class RecordMainViewModel : ViewModel() {

    private var mRecordList: RecordMainLiveData? = null

    fun getRecordList(): RecordMainLiveData {
        return mRecordList ?: RecordMainLiveData()
    }

}