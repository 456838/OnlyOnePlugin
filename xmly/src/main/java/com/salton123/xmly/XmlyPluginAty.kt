package com.salton123.xmly

import android.os.Bundle
import com.salton123.base.BaseSupportActivity
import com.salton123.base.FragmentDelegate
import com.salton123.xmly.fm.MainComponent

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午2:51
 * ModifyTime: 下午2:51
 * Description:
 */
class XmlyPluginAty : BaseSupportActivity() {
    override fun getLayout(): Int {
        return R.layout.salton_fm_container
    }

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        loadRootFragment(R.id.fl_container, FragmentDelegate.newInstance(MainComponent::class.java))
    }
}