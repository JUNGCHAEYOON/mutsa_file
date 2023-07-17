package kr.co.softcampus.android87_compass

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.softcampus.android87_compass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 가속도 센서를 통해 측정된 값을 담을 배열
    val accValue = floatArrayOf(0.0f, 0.0f, 0.0f)
    // 자기장 센서로 측정한 값을 담을 배열
    val magValue = floatArrayOf(0.0f, 0.0f, 0.0f)

    // 센서로 부터 측정된 적이 있는지...
    var isGetAcc = false
    var isGetMag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        // 가속도 센서
        val accListener = object:SensorEventListener{
            override fun onSensorChanged(p0: SensorEvent?) {
                // 가속도 센서로 부터 측정된 값을 담아준다.
                accValue[0] = p0?.values?.get(0)!!
                accValue[1] = p0?.values?.get(1)!!
                accValue[2] = p0?.values?.get(2)!!

                isGetAcc = true

                getAzimuth()
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

            }
        }

        // 자기장 센서
        val magListener = object:SensorEventListener{
            override fun onSensorChanged(p0: SensorEvent?) {
                // 자기장 센서로 측정된 값을 담아준다.
                magValue[0] = p0?.values?.get(0)!!
                magValue[1] = p0?.values?.get(1)!!
                magValue[2] = p0?.values?.get(2)!!

                isGetMag = true;

                getAzimuth()
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

            }
        }

        // 가속도 센서와 자기장 센서에 리스너를 연결해준다.
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        val sensor1 = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensor2 = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        sensorManager.registerListener(accListener, sensor1, SensorManager.SENSOR_DELAY_UI)
        sensorManager.registerListener(magListener, sensor2, SensorManager.SENSOR_DELAY_UI)

        setContentView(activityMainBinding.root)
    }

    // 센서를 통해 측정된 값을 담을 리스너
    fun getAzimuth() {
        // 두 센서 모두 값이 측정된 적이 있다면..
        if(isGetAcc == true && isGetMag == true){
            // 방위값 등을 계산하기 위해 사용할 배열
            val R = FloatArray(9){
                0.0f
            }
            val I = FloatArray(9){
                0.0f
            }
            // 계산 행렬 값을 구한다.
            SensorManager.getRotationMatrix(R, I, accValue, magValue)
            // 계산 행렬을 이용하여 방위값을 추출한다.
            val values = FloatArray(3){
                0.0f
            }
            SensorManager.getOrientation(R, values)

            // 결과가 라디언 값으로 나오기 때문에 각도 값으로 변환한다.
            val azimuth = Math.toDegrees(values[0].toDouble())
            val pitch = Math.toDegrees(values[1].toDouble())
            val roll = Math.toDegrees(values[2].toDouble())

            activityMainBinding.run{
                textView.text = "방위값 : ${azimuth.toInt() + 180}"
                textView2.text = "좌위 기울기 값 : ${pitch.toInt()}"
                textView3.text = "앞뒤 기울기 값 : ${roll.toInt()}"
            }

            // 이미지 뷰 회전
            activityMainBinding.imageView.rotation = (180 - azimuth).toFloat()
        }
    }
}