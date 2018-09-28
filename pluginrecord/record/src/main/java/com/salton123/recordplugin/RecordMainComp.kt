package com.salton123.recordplugin

import android.os.Bundle
import android.util.Log
import android.view.View
import com.salton123.base.BaseSupportFragment
import com.salton123.recordplugin.core.RecordCoreImpl
import com.salton123.util.MLog
import kotlinx.android.synthetic.main.record_main_comp.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/15 下午8:25
 * ModifyTime: 下午8:25
 * Description:
 */
class RecordMainComp : BaseSupportFragment() {
    override fun getLayout(): Int {
        return R.layout.record_main_comp
    }

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        setListener(btnStartRecord, btnFinishRecord)
        Log.i("RecordMainComp", "cacheFolder:" + RecordCoreImpl.getCacheFolder())
    }

    override fun onClick(v: View?) {
        when (v) {
            btnStartRecord -> {
                if (RecordCoreImpl.isAvailable()) {
                    RecordCoreImpl.start()
                } else {
                    longToast("系统版本低于5.0无法录制")
                }
            }
            btnFinishRecord -> {
                RecordCoreImpl.stop()
            }
        }
    }
}