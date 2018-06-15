package com.salton123.xmly.fm

import android.annotation.SuppressLint
import android.os.Bundle
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
import com.salton123.event.StartBrotherEvent
import com.salton123.util.EventUtil
import com.salton123.xmly.R
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午4:17
 * ModifyTime: 下午4:17
 * Description:
 */
class MainComponent : BaseSupportFragment() {
    override fun getLayout(): Int {
        return R.layout.salton_fm_container
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        EventUtil.register(this)
    }

    override fun initViewAndData() {
        if (findChildFragment(XmlyComponent::class.java) == null) {
            loadMultipleRootFragment(R.id.fl_container, 0, FragmentDelegate.newInstance(XmlyComponent::class.java))
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        super.onDestroy()
        EventUtil.unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onBrotherEvent(args: StartBrotherEvent) {
        start(args.targetFragment)
    }

}