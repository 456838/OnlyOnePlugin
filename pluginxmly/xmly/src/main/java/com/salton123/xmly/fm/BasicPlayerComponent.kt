package com.salton123.xmly.fm

import android.os.Bundle
import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.salton123.GlideApp
import com.salton123.base.BaseSupportFragment
import com.salton123.callback.SingleClickListener
import com.salton123.util.EventUtil
import com.salton123.xmly.R
import com.salton123.xmly.business.XmlyInitializer
import com.salton123.xmly.callback.AbsXmPlayerStatusListener
import com.salton123.xmly.util.TrackUtil
import com.ximalaya.ting.android.opensdk.model.track.Track
import com.ximalaya.ting.android.opensdk.player.service.XmPlayerException
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * User: newSalton@outlook.com
 * Date: 2018/6/15 下午3:39
 * ModifyTime: 下午3:39
 * Description:
 */
abstract class BasicPlayerComponent : BaseSupportFragment() {
    private lateinit var ivPlayPauseButton: AppCompatImageView
    private lateinit var ivCover: ImageView
    private lateinit var tvTitle: TextView
    private lateinit var tvDesc: TextView
    private var mTracks: MutableList<Track>? = null
    override fun initVariable(savedInstanceState: Bundle?) {
        EventUtil.register(this)
    }

    override fun initViewAndData() {
        ivPlayPauseButton = f(R.id.ivPlayPauseButton)
        ivCover = f(R.id.ivCover)
        tvTitle = f(R.id.tvTitle)
        tvDesc = f(R.id.tvDesc)
        mTracks = XmlyInitializer.xmPlayerManager.playList
    }

    override fun initListener() {
        ivPlayPauseButton.setOnClickListener(object : SingleClickListener(1000) {
            override fun onSingleClick(v: View) {
                if (XmlyInitializer.xmPlayerManager.isPlaying) {
                    XmlyInitializer.xmPlayerManager.pause()
                } else {
                    XmlyInitializer.xmPlayerManager.play()
                }
            }
        })
        XmlyInitializer.xmPlayerManager.addPlayerStatusListener(mPlayStateListener)
    }

    private fun loadHistory() {
        try {

            var index = TrackUtil.getTrackIndex()
            mTracks = TrackUtil.loadTrackList()
            XmlyInitializer.xmPlayerManager.setPlayList(mTracks, index)
            if (mTracks != null) {
                rePlayInfo(mTracks?.let { it[index] }!!)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        loadHistory()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventUtil.unregister(this)
        XmlyInitializer.xmPlayerManager.removePlayerStatusListener(mPlayStateListener)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun rePlayInfo(track: Track) {
        GlideApp.with(ivCover).load(track.coverUrlSmall).centerInside().circleCrop().into(ivCover)
        tvTitle.text = track.trackTitle
        tvDesc.text = track.trackIntro
    }

    private val mPlayStateListener = object : AbsXmPlayerStatusListener("BasicPlayerComponent") {
        override fun onPlayStart() {
            super.onPlayStart()
            ivPlayPauseButton.setImageResource(R.drawable.xmly_ic_pause)
        }

        override fun onPlayPause() {
            super.onPlayPause()
            ivPlayPauseButton.setImageResource(R.drawable.xmly_ic_play)
        }

        override fun onPlayStop() {
            super.onPlayStop()
            ivPlayPauseButton.setImageResource(R.drawable.xmly_ic_play)
        }

        override fun onError(e: XmPlayerException?): Boolean {
            ivPlayPauseButton.setImageResource(R.drawable.xmly_ic_play)
            return super.onError(e)
        }
    }
}
