package com.salton123.xmly.fm

import android.os.Bundle
import com.salton123.base.BaseSupportFragment
import com.salton123.base.FragmentDelegate
import com.salton123.xmly.R


/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午4:19
 * ModifyTime: 下午4:19
 * Description:
 */
class XmlyComponent : BaseSupportFragment() {

    override fun getLayout(): Int {
        return R.layout.xmly_cp_main
    }

    override fun initVariable(savedInstanceState: Bundle?) {

    }

    override fun initViewAndData() {
        loadRootFragment(R.id.flBanner, FragmentDelegate.newInstance(BannerComponent::class.java))
        loadRootFragment(R.id.flRecommend, FragmentDelegate.newInstance(RecommendComponent::class.java))
    }

}