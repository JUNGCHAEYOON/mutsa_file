package kr.co.softcampus.android85_sensorlist

import android.hardware.Sensor
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.softcampus.android85_sensorlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        // 센서 관리자를 가져온다.
        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // 단말기에 있는 센서 목록을 가져온다.
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        activityMainBinding.textView.run{
            text = ""

            // 센서의 수 만큼 반복한다.
            for(sensor in sensorList){
                append("센서 이름 : ${sensor.name}\n")
                append("센서 종류 : ${sensor.type}\n\n")
            }
        }

        setContentView(activityMainBinding.root)
    }
}










