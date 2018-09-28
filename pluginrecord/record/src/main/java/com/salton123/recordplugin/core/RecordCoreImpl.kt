package com.salton123.recordplugin.core

import android.graphics.Bitmap
import cn.sharerec.recorder.FrameWatcher
import cn.sharerec.recorder.MediaOutput
import cn.sharerec.recorder.OnRecorderStateListener
import cn.sharerec.recorder.Recorder
import cn.sharerec.recorder.a
import cn.sharerec.recorder.impl.SystemRecorder
import cn.sharerec.recorder.media.MP4
import com.mob.tools.utils.ReflectHelper
import java.nio.ByteBuffer

/**
 * User: newSalton@outlook.com
 * Date: 2018/9/28 下午8:36
 * ModifyTime: 下午8:36
 * Description:
 */
object RecordCoreImpl : IRecorderCore {
    override fun setForceLandscape() {
        recoder.setForceLandscape()
    }

    override fun setRotatable(var1: Boolean) {
        recoder.setRotatable(var1)
    }

    override fun isAvailable(): Boolean {
        return recoder.isAvailable
    }

    override fun start() {
        recoder.start()
    }

    override fun checkPermisson(var1: ReflectHelper.ReflectRunnable<SystemRecorder.CheckResult, Void>?) {
        recoder.checkPermisson(var1)
    }


    override fun clearCache() {
        recoder.clearCache()
    }

    override fun setMediaOutput(var1: MediaOutput) {
        recoder.setMediaOutput(var1)
    }

    override fun setFrameSize(var1: Int, var2: Int) {
        recoder.setFrameSize(var1, var2)
    }

    override fun getMaxWidth(): Int {
        return recoder.maxWidth
    }

    override fun getMaxHeight(): Int {
        return recoder.maxHeight
    }

    override fun prepareSoundCopying(var1: Int, var2: Int, var3: Int): ByteBuffer {
        return recoder.prepareSoundCopying(var1, var2, var3)
    }

    override fun getCacheFolder(): String {
        return recoder.cacheFolder
    }

    override fun getTmpPath(): String {
        return recoder.tmpPath
    }

    override fun getMinDuration(): Long {
        return recoder.minDuration
    }

    override fun getIsRecordAudio(): Boolean {
        return recoder.isRecordAudio
    }

    override fun setRecordAudio(var1: Boolean) {
        recoder.setRecordAudio(var1)
    }

    override fun setUseES3(var1: Boolean) {
        recoder.setUseES3(var1)
    }

    override fun setOnRecorderStateListener(var1: OnRecorderStateListener) {
        recoder.setOnRecorderStateListener(var1)
    }

    override fun getState(): Int {
        return recoder.state
    }

    override fun disableMic() {
        recoder.disableMic()
    }

    override fun showLastVideoOffLine() {
        recoder.showLastVideoOffLine()
    }

    override fun showLastVideoOffLine(var1: Runnable) {
        recoder.showLastVideoOffLine(var1)
    }

    override fun pause() {
        recoder.pause()
    }

    override fun resume() {
        recoder.resume()
    }

    override fun stop() {
        recoder.stop()
    }

    override fun getInputFrameBuffer(): ByteBuffer? {
        return recoder.inputFrameBuffer
    }

    override fun offerFrame(var1: a) {
        recoder.offerFrame(var1)
    }

    override fun offerFrame(var1: ByteBuffer) {
        recoder.offerFrame(var1)
    }

    override fun offerSample(var1: ByteArray, var2: Int, var3: Int) {
        recoder.offerSample(var1, var2, var3)
    }

    override fun setForceSoftwareEncoding(var1: Boolean, var2: Boolean) {
        recoder.setForceSoftwareEncoding(var1, var2)
    }

    override fun listVideos(): Array<MP4> {
        return recoder.listVideos()
    }

    override fun lastVideo(): MP4? {
        return recoder.lastVideo()
    }

    override fun setText(var1: String) {
        return recoder.setText(var1)
    }

    override fun addCustomAttr(var1: String, var2: String) {
        return addCustomAttr(var1, var2)
    }


    override fun calculateVideoSize(var1: Int, var2: Int): IntArray {
        return recoder.calculateVideoSize(var1, var2)
    }

    override fun showLocalVideos(var1: Bitmap) {
        return recoder.showLocalVideos(var1)
    }

    override fun showLocalVideos(var1: Bitmap, var2: Runnable) {
        return recoder.showLocalVideos(var1, var2)
    }

    override fun addFrameWatcher(var1: FrameWatcher) {
        recoder.addFrameWatcher(var1)
    }

    override fun setErrorListener(var1: ReflectHelper.ReflectRunnable<Throwable, Void>) {
        recoder.setErrorListener(var1)
    }

    override fun onError(var1: Throwable) {
        recoder.onError(var1)
    }

    override fun setCacheFolder(var1: String) {
        recoder.cacheFolder = var1
    }

    override fun setMinDuration(var1: Long) {
        recoder.minDuration = var1

    }

    override fun setVideoQuality(var1: Recorder.LevelVideoQuality) {
        recoder.setVideoQuality(var1)

    }

    override fun setMaxFrameSize(var1: Recorder.LevelMaxFrameSize) {
        recoder.setMaxFrameSize(var1)
    }


    private var recoder: SystemRecorder = SystemRecorder()


}