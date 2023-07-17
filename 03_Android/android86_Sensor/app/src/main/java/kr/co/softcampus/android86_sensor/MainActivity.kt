package kr.co.softcampus.android86_sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.softcampus.android86_sensor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    // 센서로 부터 측정된 값을 받아오면 동작하는 리스너
    var sensorListener:SensorEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

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

            // 조도 센서
            // 주변 밝기를 측정하는 센서
            // lux 단위의 주변 밝기 값을 가져온다.
            button2.run{
                setOnClickListener {

                    if(sensorListener == null){
                        // 리스너 객체를 생성한다.
                        sensorListener = object : SensorEventListener{
                            // 센서로 부터 새로운 값이 측정될 때 호출되는 메서드
                            override fun onSensorChanged(p0: SensorEvent?) {
                                // 매개변수로 들어오는 센서 객체로 부터 측정된 값을 가져온다.
                                textView.text = "주변 밝기 : ${p0?.values?.get(0)} lux"
                            }
                            // 센서의 정확도 혹은 감도 등의 성능의 변화가 있을 때 호출되는 메서드
                            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

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

            // 기압 센서
            // 공기압을 측정하는 센서
            // 실제 공기압을 위해 사용하기도 하지만 실내에서 사용자가의 고도를 측정하기 위한 용도로도 사용한다.
            button3.run{
                setOnClickListener {
                    if(sensorListener == null){
                        // 리스너
                        sensorListener = object : SensorEventListener {
                            override fun onSensorChanged(p0: SensorEvent?) {
                                textView.text = "현재 기압 : ${p0?.values?.get(0)} millibar"
                            }

                            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

                            }
                        }
                        
                        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE)
                        val chk = sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_UI)
                        if(chk == false){
                            sensorListener = null
                            textView.text = "기압 센서를 지원하지 않습니다"
                        }
                    }
                }
            }

            // 근접 센서
            // 대부분의 스마트폰에서 단말기 좌측 상단에 배치되어 있다.
            // 단말기와 물간의 거리를 cm 단위로 측정한다.
            // 실제 단말기에서는 가까운가 가깝지 않은가의 값만 측정된다. 0 또는 1
            button4.run{
                setOnClickListener {
                    if(sensorListener == null){
                        // 리스너
                        sensorListener = object : SensorEventListener {
                            override fun onSensorChanged(p0: SensorEvent?) {
                                textView.text = "물체와의 거리 : ${p0?.values?.get(0)} cm"
                            }

                            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

                            }
                        }

                        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
                        val chk = sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_UI)
                        if(chk == false){
                            sensorListener = null
                            textView.text = "근접 센서를 지원하지 않습니다"
                        }
                    }
                }
            }

            // 자이로 스코프 센서
            // 처음 발명 되었을 때는 배 내의 시설들의 수평유지를 위해 사용되었습니다.
            // 각속도
            // 기울어짐의 대한 속도
            // 스마트폰의 가속도를 측정하기 위해 사용합니다(게임, 운동, 수평계 어플 등...)
            button5.run{
                setOnClickListener {
                    if(sensorListener == null){
                        // 리스너
                        sensorListener = object : SensorEventListener {
                            override fun onSensorChanged(p0: SensorEvent?) {
                                textView.text = "X 축의 각속도 : ${p0?.values?.get(0)}"
                                textView2.text = "Y 축의 각속도 : ${p0?.values?.get(1)}"
                                textView3.text = "Z 축의 각속도 : ${p0?.values?.get(2)}"
                            }

                            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

                            }
                        }

                        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
                        val chk = sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_UI)
                        if(chk == false){
                            sensorListener = null
                            textView.text = "자이로 스코프 센서를 지원하지 않습니다"
                        }
                    }
                }
            }

            // 가속도 센서
            // 기울어짐을 측정하는 센서
            button6.run{
                setOnClickListener {
                    if(sensorListener == null){
                        // 리스너
                        sensorListener = object : SensorEventListener {
                            override fun onSensorChanged(p0: SensorEvent?) {
                                textView.text = "X 축의 기울기 : ${p0?.values?.get(0)}"
                                textView2.text = "Y 축의 기울기 : ${p0?.values?.get(1)}"
                                textView3.text = "Z 축의 기울기 : ${p0?.values?.get(2)}"
                            }

                            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

                            }
                        }

                        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
                        val chk = sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_UI)
                        if(chk == false){
                            sensorListener = null
                            textView.text = "가속도 센서를 지원하지 않습니다"
                        }
                    }
                }
            }

            // 자기장 센서
            // 단말기 주변의 자기장을 측정하는 센서
            button7.run{
                setOnClickListener {
                    if(sensorListener == null){
                        // 리스너
                        sensorListener = object : SensorEventListener {
                            override fun onSensorChanged(p0: SensorEvent?) {
                                textView.text = "X 축 자기장 : ${p0?.values?.get(0)}"
                                textView2.text = "Y 축 자기장 : ${p0?.values?.get(1)}"
                                textView3.text = "Z 축 자기장 : ${p0?.values?.get(2)}"
                            }

                            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

                            }
                        }

                        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
                        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
                        val chk = sensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_UI)
                        if(chk == false){
                            sensorListener = null
                            textView.text = "자기장 센서를 지원하지 않습니다"
                        }
                    }
                }
            }


        }

        setContentView(activityMainBinding.root)
    }
}










