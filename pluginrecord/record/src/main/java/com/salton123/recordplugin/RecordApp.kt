package com.salton123.recordplugin

import com.mob.MobSDK
import com.salton123.base.ApplicationBase

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/28 下午8:10
 * ModifyTime: 下午8:10
 * Description:
 */
class RecordApp :ApplicationBase(){
    override fun onCreate() {
        super.onCreate()
        MobSDK.init(this)

    }
}