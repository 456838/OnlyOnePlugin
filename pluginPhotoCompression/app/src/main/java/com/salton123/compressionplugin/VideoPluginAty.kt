package com.salton123.compressionplugin

import android.Manifest
import android.content.pm.PackageInfo
import android.os.Bundle
import android.widget.Toast
import com.salton123.base.BaseSupportActivity
import com.salton123.util.SizeUtils
import me.weyye.hipermission.HiPermission
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem
import java.io.File
import java.util.*
import android.content.pm.ApplicationInfo
import android.media.Image
import com.gyf.barlibrary.ImmersionBar
import com.salton123.base.FragmentDelegate
import com.salton123.compressionplugin.model.AppInfoCore


/**
 * User: newSalton@outlook.com
 * Date: 2018/4/15 下午8:09
 * ModifyTime: 下午8:09
 * Description:
 */
class VideoPluginAty : BaseSupportActivity() {
    internal var mImmersionBar: ImmersionBar? = null
    override fun getLayout(): Int {
        return R.layout.salton_fm_container
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        mImmersionBar = ImmersionBar.with(this)
            .statusBarDarkFont(true)
        mImmersionBar!!.init()
    }

    override fun initViewAndData() {
        val permissionItems = ArrayList<PermissionItem>()
//        permissionItems.add(PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读内存卡权限", R.drawable.permission_ic_storage))
        permissionItems.add(PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写内存卡权限", R.drawable.permission_ic_storage))
        permissionItems.add(PermissionItem(Manifest.permission.READ_PHONE_STATE, "使用网络权限", R.drawable.permission_ic_phone))
        HiPermission.create(this)
            .permissions(permissionItems)
            .checkMutiPermission(object : PermissionCallback {
                override fun onFinish() {
                    checkPermission()
                }

                override fun onDeny(permission: String?, position: Int) {
                    Toast.makeText(applicationContext, "请授予应用必要的权限以满足您的使用需求", Toast.LENGTH_LONG).show()
                    finish()
                }

                override fun onGuarantee(permission: String?, position: Int) {
                }

                override fun onClose() {
                    checkPermission()
                }
            })
    }

    private fun checkPermission() {
        val hasReadStorgePermission = HiPermission.checkPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE)
        val hasWriteStorgePermission = HiPermission.checkPermission(applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE)
        val hasInternetPermission = HiPermission.checkPermission(applicationContext, Manifest.permission.INTERNET)
        if (hasReadStorgePermission && hasWriteStorgePermission && hasInternetPermission) {
            loadData()
        } else {
            Toast.makeText(applicationContext, "请授予应用必要的权限以满足您的使用需求", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun loadData() {
        loadRootFragment(R.id.fl_container, FragmentDelegate.newInstance(ImageCompressionComp::class.java))
    }


    override fun onDestroy() {
        mImmersionBar?.destroy()
        super.onDestroy()
    }
}