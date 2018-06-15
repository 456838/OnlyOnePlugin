package com.salton123.xmly.view.widget

import android.content.Context
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.salton123.xmly.R
import kotlinx.android.synthetic.main.xmly_view_title_bar.view.*

/**
 * User: newSalton@outlook.com
 * Date: 2018/6/6 下午2:18
 * ModifyTime: 下午2:18
 * Description:
 */
class XmlyTitleBar(context: Context, attributes: AttributeSet?, defStyleAttr: Int) : FrameLayout(context, attributes, defStyleAttr) {
    constructor(context: Context, attributes: AttributeSet?) : this(context, attributes, 0)

    constructor(context: Context) : this(context, null)

    init {
        LayoutInflater.from(getContext()).inflate(R.layout.xmly_view_title_bar, this)
    }

    fun title(title: String = ""): XmlyTitleBar {
        tvTitle.text = title
        return this
    }

    fun leftIcon(@DrawableRes resId: Int = R.drawable.xmly_ic_arrow_back_black_24dp): XmlyTitleBar {
        tvTitleBack.setImageResource(resId)
        return this
    }

    fun leftIconListener(listener: OnClickListener?): XmlyTitleBar {
        listener?.let {
            tvTitleBack.setOnClickListener(it)
        }
        return this
    }
}