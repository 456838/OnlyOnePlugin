package com.salton123.compressionplugin

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import com.hazz.kotlinmvp.view.recyclerview.adapter.OnItemClickListener
import com.salton123.base.BaseSupportFragment
import com.salton123.compressionplugin.R.id.recyclerView
import com.salton123.compressionplugin.model.AppInfoCore
import kotlinx.android.synthetic.main.comp_image_compression.*


/**
 * User: newSalton@outlook.com
 * Date: 2018/4/15 下午8:25
 * ModifyTime: 下午8:25
 * Description:
 */
class ImageCompressionComp : BaseSupportFragment() {

    private val mAdapter by lazy { AppInfoAdapter(_mActivity) }
    override fun getLayout(): Int {
        return R.layout.comp_image_compression
    }

    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun initViewAndData() {
        recyclerView.layoutManager = LinearLayoutManager(_mActivity)
        recyclerView.adapter = mAdapter
        mAdapter.addAll(AppInfoCore.getInstalledList())
        mAdapter.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(obj: Any?, position: Int) {
                val builder = AlertDialog.Builder(_mActivity).setPositiveButton("哈哈"
                ) { _, _ ->
//                    longToast("haha")
                }.create();
                builder.findViewById<Button>(android.R.id.button1)?.setOnClickListener {
                    longToast("you are my ")
                }
                builder.show()
            }
        })
    }
}