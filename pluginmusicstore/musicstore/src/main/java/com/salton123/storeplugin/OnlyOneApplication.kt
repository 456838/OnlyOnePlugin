package com.salton123.storeplugin

import com.salton123.base.ApplicationBase
import com.salton123.util.LogUtils
import me.yokeyword.fragmentation.Fragmentation


/**
 * User: newSalton@outlook.com
 * Date: 2018/4/14 下午4:46
 * ModifyTime: 下午4:46
 * Description:
 */
class OnlyOneApplication : ApplicationBase() {


    override fun onCreate() {
        super.onCreate()
        initDependences()
    }

    private fun initDependences() {

    }
}