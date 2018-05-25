package com.salton123.titeduplugin

import android.os.Bundle
import com.salton123.base.BaseSupportActivity
import com.salton123.base.FragmentDelegate
import com.salton123.titeduplugin.ui.fm.TitEduComponent

class TitEduAty : BaseSupportActivity() {


    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initViewAndData() {
        loadRootFragment(R.id.fl_container, FragmentDelegate.newInstance(TitEduComponent::class.java)!!)
    }

    override fun initListener() {
    }

    override fun initVariable(savedInstanceState: Bundle?) {
    }

}
