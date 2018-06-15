package com.salton123.xmly

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.gyf.barlibrary.ImmersionBar
import com.salton123.base.BaseSupportActivity
import com.salton123.base.FragmentDelegate
import com.salton123.xmly.business.XmlyInitializer
import com.salton123.xmly.fm.MainComponent
import me.weyye.hipermission.HiPermission
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/23 下午2:51
 * ModifyTime: 下午2:51
 * Description:
 */
class XmlyPluginAty : BaseSupportActivity() {
    override fun getLayout(): Int {
        return R.layout.salton_fm_container
    }

    internal var isSetup = false

    lateinit var mImmersionBar: ImmersionBar
    override fun initVariable(savedInstanceState: Bundle?) {
        mImmersionBar = ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .transparentBar().transparentNavigationBar()
        mImmersionBar.init()

    }

    override fun initViewAndData() {
        checkPermission()
        fragmentAnimator = DefaultHorizontalAnimator()
    }


    /**
     * 6.0以下版本(系统自动申请) 不会弹框
     * 有些厂商修改了6.0系统申请机制，他们修改成系统自动申请权限了
     */
    private fun checkPermission() {
        val permissionItems = mutableListOf<PermissionItem>()
        permissionItems.add(PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取存储空间", R.drawable.permission_ic_storage))
        permissionItems.add(PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入存储空间", R.drawable.permission_ic_storage))
        HiPermission.create(this)
            .title("亲爱的上帝")
            .msg("为了能够正常使用，请开启这些权限吧！")
            .permissions(permissionItems)
            .style(R.style.PermissionDefaultBlueStyle)
            .animStyle(R.style.PermissionAnimScale)
            .checkMutiPermission(object : PermissionCallback {
                override fun onClose() {
                    Log.i("aa", "permission_onClose")
                    Toast.makeText(applicationContext, "用户关闭了权限", Toast.LENGTH_LONG).show()
                }

                override fun onFinish() {
                    Toast.makeText(applicationContext, "初始化完毕", Toast.LENGTH_LONG).show()
                    if (!isSetup) {
                        setup()
                    }
                }

                override fun onDeny(s: String, i: Int) {
                    Log.i("aa", "permission_onDeny")
                }

                override fun onGuarantee(s: String, i: Int) {
                    Log.i("aa", "permission_onGuarantee")
                    if (!isSetup) {
                        setup()
                    }
                }
            })
    }

    private fun setup() {
        isSetup = true
        if (findFragment(MainComponent::class.java) == null) {
            loadRootFragment(R.id.fl_container, FragmentDelegate.newInstance(MainComponent::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mImmersionBar.destroy()
        if (XmlyInitializer.xmPlayerManager.playList != null) {

        }
        XmlyInitializer.deInit()
    }
}