package com.salton123.onlyone

import android.os.Bundle
import com.salton123.base.FragmentDelegate
import com.salton123.onlyone.ui.MainFragment
import me.yokeyword.fragmentation.SupportActivity

class MainActivity : SupportActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.salton_fm_container)
        loadRootFragment(R.id.fl_container, FragmentDelegate.newInstance(MainFragment::class.java))
    }

//    override fun getLayout(): Int {
//        return R.layout.salton_fm_container
//    }
//
//    override fun initVariable(savedInstanceState: Bundle?) {
//        if (savedInstanceState == null) {
//
//        }
//    }
//
//    override fun initViewAndData() {
//        loadRootFragment(R.id.fl_container, FragmentDelegate.newInstance(MainFragment::class.java)!!)
//    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.salton_fm_container)

//        var image = ImageView(this)
//        image.setImageResource(R.mipmap.ic_launcher)
//        findViewById<FrameLayout>(R.id.fl_container).addView(image)
//        if (RePlugin.preload("videoplugin")) {
//            var videoLayout = RePlugin.fetchViewByLayoutName<LinearLayout>("videoplugin", "fm_video", null)
//            findViewById<FrameLayout>(R.id.fl_container).addView(videoLayout)
//        }
//        if (RePlugin.preload("videoplugin")) {
//            RePlugin.startActivity(this@MainActivity, Intent(), "videoplugin", "com.salton123.videoplugin.VideoPluginAty")
//        }
//    }

}
