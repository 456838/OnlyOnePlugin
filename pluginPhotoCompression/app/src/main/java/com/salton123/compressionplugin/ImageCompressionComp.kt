package com.salton123.compressionplugin

import android.os.Bundle
import com.salton123.base.BaseSupportFragment
import com.salton123.videoplugin.R
import com.zhihu.matisse.engine.impl.GlideEngine
import android.content.pm.ActivityInfo
import com.zhihu.matisse.filter.Filter.K
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.filter.Filter


/**
 * User: newSalton@outlook.com
 * Date: 2018/4/15 下午8:25
 * ModifyTime: 下午8:25
 * Description:
 */
class ImageCompressionComp : BaseSupportFragment() {
    override fun getLayout(): Int {
        return R.layout.comp_image_compression
    }

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {

    }
}