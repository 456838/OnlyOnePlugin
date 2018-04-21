package com.salton123.titeduplugin

import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import com.salton123.base.BaseSupportActivity

class TitEduAty : BaseSupportActivity(){


    override fun getLayout(): Int {
//        return R.layout.activity_main
        return 0
    }

    override fun initViewAndData() {
        var map = HashMap<String, Any>()
//        RetrofitHelper.getTitNewsApi()
//                .getNewsCategory(TitNewsCategory.CATEGORY_IMPORTANCE_NEWS, 1, "NextPage")
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    it?.let {
//                        var datas = DataOpService.parserNewsTagInfo(it)
//                        datas.forEachIndexed { index, newTagInfo ->
//                            content.append(newTagInfo.toString() + "\n")
//                        }
//                    }
//                }
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        val mSensor: Sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun initListener() {
    }

    override fun initVariable(savedInstanceState: Bundle?) {
    }

}
