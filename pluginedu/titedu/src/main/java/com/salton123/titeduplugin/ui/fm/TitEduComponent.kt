package com.salton123.titeduplugin.ui.fm

import android.os.Bundle
import cn.edu.tit.module.api.TitNewsCategory
import com.salton123.base.BaseSupportFragment
import com.salton123.titeduplugin.R

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/22 下午5:05
 * ModifyTime: 下午5:05
 * Description:
 */
class TitEduComponent : BaseSupportFragment() {
    override fun getLayout(): Int {
        return R.layout.tit_edu_cp_main
    }

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
    }

    fun say(@TitNewsCategory type: Int) {

    }
}