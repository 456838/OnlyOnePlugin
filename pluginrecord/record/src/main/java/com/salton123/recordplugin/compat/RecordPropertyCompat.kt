package com.salton123.recordplugin.compat

import cn.sharerec.recorder.Recorder
import com.google.gson.Gson
import com.salton123.base.ApplicationBase
import com.salton123.core.CoreManager
import com.salton123.log.XLog
import com.salton123.recordplugin.core.IRecorderCore
import com.salton123.recordplugin.model.bean.RecordProperty
import com.salton123.util.PreferencesUtils

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/6 下午2:57
 * ModifyTime: 下午2:57
 * Description:
 */
object RecordPropertyCompat {
    private const val KEY_RECORD_PROPERTY = "key_record_property"
    private var mProperty = RecordProperty()
    private var recorder = CoreManager.getCore(IRecorderCore::class.java)
    fun setMaxFrameSize(frameSize: Recorder.LevelMaxFrameSize) {
        mProperty.maxFrameSize = frameSize.pixels
        recorder.setMaxFrameSize(frameSize)
        saveProperty()
    }

    init {
        load()
    }

    fun load() {
        mProperty = loadProperty()
        recorder.setMaxFrameSize(compatMaxFrameSize(mProperty.maxFrameSize))
        recorder.setVideoQuality(compatVideoQuality(mProperty.videoQuality))
        XLog.e("load")
    }
//    fun isLandscape(isLandscape: Boolean) {
//        mProperty.isLandscape = isLandscape
//        recorder.setForceLandscape()
//        saveProperty()
//    }

    fun isReecordAudio(isRecordAudio: Boolean) {
        mProperty.isRecordAudio = isRecordAudio
        recorder.setRecordAudio(isRecordAudio)
        saveProperty()
    }

    fun disableMic(disableMic: Boolean) {
        mProperty.disableMic = disableMic
        recorder.disableMic()
        saveProperty()
    }

    fun videoQuality(videoQuality: Recorder.LevelVideoQuality) {
        mProperty.videoQuality = videoQuality.ordinal
        recorder.setVideoQuality(videoQuality)
        saveProperty()
    }

    private fun loadProperty(): RecordProperty {
        return try {
            var property = PreferencesUtils.getString(
                    ApplicationBase.getInstance(),
                    KEY_RECORD_PROPERTY,""
            )
            XLog.e("property=$property")
            Gson().fromJson<RecordProperty>(property, RecordProperty::class.java)
        } catch (ex: Exception) {
            XLog.e(ex)
            RecordProperty()
        }
    }

    private fun saveProperty() {
        try {
            PreferencesUtils.putString(
                    ApplicationBase.getInstance(),
                    KEY_RECORD_PROPERTY,
                    Gson().toJson(mProperty)
            )
        } catch (ex: Exception) {
            XLog.e(ex)
        }
    }

    private fun compatVideoQuality(quality: Int): Recorder.LevelVideoQuality {
        when (quality) {
            Recorder.LevelVideoQuality.LEVEL_SUPER_LOW.ordinal -> {
                return Recorder.LevelVideoQuality.LEVEL_SUPER_LOW
            }
            Recorder.LevelVideoQuality.LEVEL_VERY_LOW.ordinal -> {
                return Recorder.LevelVideoQuality.LEVEL_VERY_LOW
            }
            Recorder.LevelVideoQuality.LEVEL_LOW.ordinal -> {
                return Recorder.LevelVideoQuality.LEVEL_LOW
            }
            Recorder.LevelVideoQuality.LEVEL_MEDIUN.ordinal -> {
                return Recorder.LevelVideoQuality.LEVEL_MEDIUN
            }

            Recorder.LevelVideoQuality.LEVEL_VERY_HIGH.ordinal -> {
                return Recorder.LevelVideoQuality.LEVEL_VERY_HIGH
            }
            Recorder.LevelVideoQuality.LEVEL_SUPER_HIGH.ordinal -> {
                return Recorder.LevelVideoQuality.LEVEL_SUPER_HIGH
            }
//            Recorder.LevelVideoQuality.LEVEL_HIGH.ordinal
            else -> {
                return Recorder.LevelVideoQuality.LEVEL_HIGH
            }
        }
    }

    private fun compatMaxFrameSize(size: Int): Recorder.LevelMaxFrameSize {
        when (size) {
            172800 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_480_360
            }
            921600 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_1280_720
            }
            2073600 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_1920_1080
            }
            76800 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_320_240
            }
            96000 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_400_240
            }
            103680 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_432_240
            }
            153600 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_480_320
            }
            480000 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_800_600
            }
            409920 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_854_480
            }
            983040 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_1280_768
            }
            2359296 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_2048_1152
            }
            3686400 -> {
                return Recorder.LevelMaxFrameSize.LEVEL_2560_1440
            }
            else -> { //384000
                return Recorder.LevelMaxFrameSize.LEVEL_800_480
            }
        }
    }
}