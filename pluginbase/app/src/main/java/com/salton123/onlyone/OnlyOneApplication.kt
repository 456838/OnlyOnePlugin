package com.salton123.onlyone

import android.content.Context
import android.content.res.Configuration
import com.qihoo360.replugin.RePlugin
import com.qihoo360.replugin.RePluginCallbacks
import com.qihoo360.replugin.RePluginConfig
import com.salton123.baselib.GlossyApplication
import com.salton123.log.XLog
import me.yokeyword.fragmentation.Fragmentation


/**
 * User: newSalton@outlook.com
 * Date: 2018/4/14 下午4:46
 * ModifyTime: 下午4:46
 * Description:
 */
class OnlyOneApplication : GlossyApplication() {


    /**
     * 子类可以复写此方法来自定义RePluginConfig。请参见 RePluginConfig 的说明
     *
     * @see RePluginConfig
     *
     * @return 新的RePluginConfig对象
     */
    protected fun createConfig(): RePluginConfig {
        return RePluginConfig()
    }

    /**
     * 子类可以复写此方法来自定义RePluginCallbacks。请参见 RePluginCallbacks 的说明
     *
     *
     * 注意：若在createConfig的RePluginConfig内同时也注册了Callbacks，则以这里创建出来的为准
     *
     * @see RePluginCallbacks
     *
     * @return 新的RePluginCallbacks对象，可以为空
     */
    protected fun createCallbacks(): RePluginCallbacks? {
        return null
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)

        var c: RePluginConfig? = createConfig()
        if (c == null) {
            c = RePluginConfig()
        }

        val cb = createCallbacks()
        if (cb != null) {
            c.callbacks = cb
        }

        RePlugin.App.attachBaseContext(this, c)
    }

    override fun onCreate() {
        super.onCreate()
        RePlugin.App.onCreate()
        initDependences()
    }

    private fun initDependences() {
        // 栈视图功能，大大降低Fragment的开发难度，建议在Application里初始化
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                // ture时，遇到异常："Can not perform this action after onSaveInstanceState!"时，会抛出
                // false时，不会抛出，会捕获，可以在handleException()里监听到
                .debug(BuildConfig.DEBUG)
                // 线上环境时，可能会遇到上述异常，此时debug=false，不会抛出该异常（避免crash），会捕获
                // 建议在回调处上传至我们的Crash检测服务器
                .handleException {
                   XLog.e(this,it.localizedMessage)
                    //                         以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
                    //                         Bugtags.sendException(e);
                }
                .install()
    }

    override fun onLowMemory() {
        super.onLowMemory()

        // 如果App的minSdkVersion >= 14，该方法可以不调用
        RePlugin.App.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)

        // 如果App的minSdkVersion >= 14，该方法可以不调用
        RePlugin.App.onTrimMemory(level)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // 如果App的minSdkVersion >= 14，该方法可以不调用
        RePlugin.App.onConfigurationChanged(newConfig)
    }

}