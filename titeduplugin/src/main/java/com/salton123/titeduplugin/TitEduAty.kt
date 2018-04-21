package com.salton123.titeduplugin

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import com.salton123.base.BaseSupportActivity

class TitEduAty : BaseSupportActivity(), SensorEventListener {


    override fun getLayout(): Int {
//        return R.layout.activity_main
        return 0
    }

    override fun initViewAndData() {
        var map = HashMap<String, Any>()
//        map["a42787CountNo"] = 10
//        map["a42787CURURI"] = 10
//        map["a42787NOWPAGE"] = 10
//        map["a42787ORDER"] = 10
//        map["a42787ORDERKEY"] = 10
//        map["a42787PAGE"] = 0
//        map["a42787rowCount"] = 10
//        map["actiontype"] = "NextPage".
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
        // 注册listener，第三个参数是检测的精确度
        //SENSOR_DELAY_FASTEST 最灵敏 因为太快了没必要使用
        //SENSOR_DELAY_GAME    游戏开发中使用
        //SENSOR_DELAY_NORMAL  正常速度
        //SENSOR_DELAY_UI          最慢的速度
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent) {
        var mGX = event.values[SensorManager.DATA_X]
        var mGY = event.values[SensorManager.DATA_Y]
        var mGZ = event.values[SensorManager.DATA_Z]
//        content.setText("x=$mGX,y=$mGY,z=$mGZ")
    }

//    fun getPostParams(postDao: HttpPostDao): List<NameValuePair> {
//        val postparams = ArrayList<NameValuePair>()
//        postparams.add(BasicNameValuePair("a42787CountNo", postDao
//                .getCountNo()))
//        postparams.add(BasicNameValuePair("a42787CURURI", postDao
//                .getCurUri()))
//        postparams.add(BasicNameValuePair("a42787NOWPAGE", postDao
//                .getNowPage()))
//        postparams
//                .add(BasicNameValuePair("a42787ORDER", postDao.getOrder()))
//
//        postparams.add(BasicNameValuePair("a42787ORDERKEY", postDao
//                .getOrderkey()))
//
//        postparams.add(BasicNameValuePair("a42787PAGE", postDao.getPage()))
//
//        postparams.add(BasicNameValuePair("a42787rowCount", postDao
//                .getRowCount()))
//
//        postparams.add(BasicNameValuePair("actiontype", postDao
//                .getActionType()))
//
//        return postparams
//
//    }

    override fun initListener() {
    }

    override fun initVariable(savedInstanceState: Bundle?) {
    }

}
