package com.salton123.baselib

import com.salton123.base.ApplicationBase
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/25 下午4:39
 * ModifyTime: 下午4:39
 * Description:
 */
abstract class GlossyApplication : ApplicationBase() {


    fun cusFont() {
        // Custom fonts
        CalligraphyConfig.initDefault(
                CalligraphyConfig.Builder()
                        .setDefaultFontPath("fonts/Roboto-Monospace-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        )
    }
}