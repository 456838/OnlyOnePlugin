package com.salton123.recordplugin.model.bean

import cn.sharerec.recorder.Recorder

/**
 * User: newSalton@outlook.com
 * Date: 2018/10/6 下午2:59
 * ModifyTime: 下午2:59
 * Description:
 */
data class RecordProperty(
    var isLandscape: Boolean = false,
    var isRecordAudio: Boolean = true,
    var disableMic: Boolean = false,
    var maxFrameSize: Int = Recorder.LevelMaxFrameSize.LEVEL_800_480.pixels,
    var videoQuality: Int = Recorder.LevelVideoQuality.LEVEL_HIGH.ordinal
)