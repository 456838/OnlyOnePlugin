package com.salton123.recordplugin

import android.os.Bundle
import com.gyf.barlibrary.ImmersionBar
import com.salton123.base.BaseSupportActivity

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/15 下午8:09
 * ModifyTime: 下午8:09
 * Description:
 */
class RecordPluginAty : BaseSupportActivity() {
    lateinit var mImmersionBar: ImmersionBar
    override fun getLayout(): Int {
        return R.layout.record_plugin_aty
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        mImmersionBar = ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .transparentBar().transparentNavigationBar()
        mImmersionBar.init()
    }

    override fun initViewAndData() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mImmersionBar.destroy()
    }
}