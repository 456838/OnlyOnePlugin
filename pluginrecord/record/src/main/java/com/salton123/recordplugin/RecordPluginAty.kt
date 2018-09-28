package com.salton123.recordplugin

import android.os.Bundle
import com.salton123.base.BaseSupportActivity

/**
 * User: newSalton@outlook.com
 * Date: 2018/4/15 下午8:09
 * ModifyTime: 下午8:09
 * Description:
 */
class RecordPluginAty : BaseSupportActivity() {
    override fun getLayout(): Int {
        return R.layout.record_plugin_aty
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
    }
}