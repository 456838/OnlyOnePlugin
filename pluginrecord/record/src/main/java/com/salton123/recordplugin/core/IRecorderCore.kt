package com.salton123.recordplugin.core

import android.graphics.Bitmap
import cn.sharerec.core.biz.CustomPlatform
import cn.sharerec.recorder.FrameWatcher
import cn.sharerec.recorder.MediaOutput
import cn.sharerec.recorder.OnRecorderStateListener
import cn.sharerec.recorder.Recorder
import cn.sharerec.recorder.a
import cn.sharerec.recorder.impl.SystemRecorder
import cn.sharerec.recorder.media.MP4
import com.mob.tools.utils.ReflectHelper
import com.salton123.core.IBaseCore
import java.nio.ByteBuffer

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/28 下午8:35
 * ModifyTime: 下午8:35
 * Description:
 */
interface IRecorderCore : IBaseCore {
    fun setMaxFrameSize(var1: Recorder.LevelMaxFrameSize)
    fun setVideoQuality(var1: Recorder.LevelVideoQuality)
    fun setMinDuration(var1: Long)
    fun setCacheFolder(var1: String)
    fun setForceLandscape()
    fun setRotatable(var1: Boolean)
    fun isAvailable(): Boolean
    fun start()
    fun checkPermisson(var1: ReflectHelper.ReflectRunnable<SystemRecorder.CheckResult, Void>?)
    fun clearCache()
    fun setMediaOutput(var1: MediaOutput)
    fun setFrameSize(var1: Int, var2: Int)
    fun getMaxWidth(): Int
    fun getMaxHeight(): Int
    fun prepareSoundCopying(var1: Int, var2: Int, var3: Int): ByteBuffer
    fun getCacheFolder(): String
    fun getTmpPath(): String
    fun getMinDuration(): Long
    fun getIsRecordAudio(): Boolean
    fun setRecordAudio(var1: Boolean)
    fun setUseES3(var1: Boolean)
    fun setOnRecorderStateListener(var1: OnRecorderStateListener)
    fun getState(): Int
    fun disableMic()
    fun showLastVideoOffLine()
    fun showLastVideoOffLine(var1: Runnable)
    fun pause()
    fun resume()
    fun stop()
    fun getInputFrameBuffer(): ByteBuffer?
    fun offerFrame(var1: a)
    fun offerFrame(var1: ByteBuffer)
    fun offerSample(var1: ByteArray, var2: Int, var3: Int)
    fun setForceSoftwareEncoding(var1: Boolean, var2: Boolean)
    @Throws(Throwable::class)
    fun listVideos(): Array<MP4>

    @Throws(Throwable::class)
    fun lastVideo(): MP4?

    fun setText(var1: String)
    fun addCustomAttr(var1: String, var2: String)
    fun calculateVideoSize(var1: Int, var2: Int): IntArray
    fun showLocalVideos(var1: Bitmap)
    fun showLocalVideos(var1: Bitmap, var2: Runnable)
    fun addFrameWatcher(var1: FrameWatcher)
    fun setErrorListener(var1: ReflectHelper.ReflectRunnable<Throwable, Void>)
    fun onError(var1: Throwable)
}
