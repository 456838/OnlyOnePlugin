package com.salton123.compressionplugin

import android.Manifest
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import com.salton123.base.BaseSupportActivity
import com.salton123.videoplugin.R
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import com.zhihu.matisse.filter.Filter
import me.weyye.hipermission.HiPermission
import me.weyye.hipermission.PermissionCallback
import me.weyye.hipermission.PermissionItem
import java.util.ArrayList

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/15 下午8:09
 * ModifyTime: 下午8:09
 * Description:
 */
class VideoPluginAty : BaseSupportActivity() {
    override fun getLayout(): Int {
        return R.layout.view_video_component
    }

    override fun initVariable(savedInstanceState: Bundle?) {
        // 栈视图功能，大大降低Fragment的开发难度，建议在Application里初始化
//        Fragmentation.builder()
//                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
//                .stackViewMode(Fragmentation.BUBBLE)
//                // ture时，遇到异常："Can not perform this action after onSaveInstanceState!"时，会抛出
//                // false时，不会抛出，会捕获，可以在handleException()里监听到
//                .debug(BuildConfig.DEBUG)
//                // 线上环境时，可能会遇到上述异常，此时debug=false，不会抛出该异常（避免crash），会捕获
//                // 建议在回调处上传至我们的Crash检测服务器
//                .handleException {
//                    LogUtils.e(it)
//                    //                         以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
//                    //                         Bugtags.sendException(e);
//                }
//                .install()
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
        Matisse.from(this@VideoPluginAty)
            .choose(MimeType.ofAll())
            .countable(true)
            .maxSelectable(9)
            .addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
            .gridExpectedSize(resources.getDimensionPixelSize(R.dimen.grid_expected_size))
            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
            .thumbnailScale(0.85f)
            .imageEngine(GlideEngine())
            .forResult(REQUEST_CODE_CHOOSE)
    }

}