package com.salton123.storeplugin

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import com.salton123.base.BaseSupportActivity
import com.salton123.base.FragmentDelegate
import me.weyye.hipermission.HiPermission
import android.Manifest.permission
import android.Manifest.permission.READ_PHONE_STATE
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem


/**
 * User: newSalton@outlook.com
 * Date: 2018/11/2 6:10 PM
 * ModifyTime: 6:10 PM
 * Description:
 */
class MusicStoreAty : BaseSupportActivity() {
    override fun initVariable(savedInstanceState: Bundle?) {
    }

    override fun getLayout(): Int = R.layout.salton_fm_container


    @SuppressLint("WrongConstant")
    override fun initViewAndData() {
        val permissionItems = ArrayList<PermissionItem>()
        permissionItems.add(PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "读写存储", R.drawable.permission_ic_storage))
        permissionItems.add(PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读写存储", R.drawable.permission_ic_storage))
        permissionItems.add(PermissionItem(Manifest.permission.INTERNET, "读写存储", R.drawable.permission_ic_storage))

        HiPermission.create(application)
            .permissions(permissionItems)
            .checkMutiPermission(object : PermissionCallback {
                override fun onFinish() {
                    loadRootFragment(R.id.fl_container, FragmentDelegate.newInstance(MusicStoreComp::class.java))
                }

                override fun onDeny(permission: String?, position: Int) {
                }

                override fun onGuarantee(permission: String?, position: Int) {
                }

                override fun onClose() {
                }
            })
    }
}
