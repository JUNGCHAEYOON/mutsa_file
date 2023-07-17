package com.test.android86_sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android86_sensor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 센서로 부터 측정된 값을 받아오면 동작하는 리스너
    var sensorListener : SensorEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            // 중지 버튼
            button.run{
                setOnClickListener {
                    if(sensorListener != null) {
                        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                        // 센서가 측정한 값을 받아올 수 있는 리스너 연결을 해제한다.
                        sensorManager.unregisterListener(sensorListener)
                        sensorListener = null
                    }
                }
            }

            // 조도센서
            // 주변밝기를 측정하는센서
            // lux 단위의 주변 밝기 값을 가져온다.
            button2.run{
                setOnClickListener {
                    // 리스너 객체를 생성한다.
                    sensorListener = object : SensorEventListener{
                        // 센서로 부터 새로운 값이 측정될때 호출되는 메서드
                        override fun onSensorChanged(p0: SensorEvent?) {
                            // 매개변수로 들어오는 센서 객체로 부터 측정된 값을 가져온다.
                            textView.text = "주변 밝기 : ${p0?.values?.get(0)} + lux"
                        }

                        // 센서의 정확도 혹은 감도 등의 성능의 변화가 있을 때 호출되는 메서드
                        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
                            TODO("Not yet implemented")
                        }
                    }

                    // 리스너를 등록한다.
                    val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                    // 조도 센서 객체를 가져온다.
                    val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
                    // 센서로 부터 측정된 값을 가져올 리스너를 등록한다.
                    // 첫 번째 : 리스너
                    // 두 번째 : 연결할 센서 객체
                    // 세 번째 : 측정 주기
                    // SensorManager.SENSOR_DELAY_FASTEST : 가장 짧은 주기(단말기 하드웨어 성능에 따라 다르다)
                    // SensorManager.SENSOR_DELAY_UI : 화면 주사율 주기
                    // SensorManager.SENSOR_DELAY_GAME : 게임에 최적화된 주기
                    // SensorManager.SENSOR_DELAY_NORMAL : 보통 주기
                    // 조도 센서와 리스너를 연결한다.
                    // 반환값(Boolean) : 센서가 지원하지 않거나 리스너 연결에 문제가 있다면 false를 반환한다.
                    val chk = sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_UI)
                    if(chk == false){
                        sensorListener = null
                        textView.text = "조도 센서를 지원하지 않습니다"
                    }
                }
            }
        }
    }
}