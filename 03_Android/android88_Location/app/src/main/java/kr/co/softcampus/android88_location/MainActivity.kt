package kr.co.softcampus.android88_location

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import kr.co.softcampus.android88_location.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 권한 목록
    val permissionList = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    // 위치 측정 리스너
    var locationListener:LocationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        // 권한 확인
        requestPermissions(permissionList, 0)

        activityMainBinding.run{
            button.setOnClickListener {
                // 사용자의 권한 허용 여부를 확인한다.
                val a1 = ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
                val a2 = ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_COARSE_LOCATION)

                if(a1 == PackageManager.PERMISSION_GRANTED && a2 == PackageManager.PERMISSION_GRANTED){
                    // 위치 정보를 관리하는 관리자를 추출한다.
                    val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

                    // 마지막으로 저장된 위치 값을 가져온다
                    val location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    val location2 = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                    // 기존에 추출된 정보가 있다면 일단 이것으로 먼저 사용한다.
                    if(location1 != null){
                        showInfo(location1)
                    } else if(location2 != null) {
                        showInfo(location2)
                    }

                    // 위치 측정 리스너
                    locationListener = object: LocationListener{
                        // 위치가 새롭게 측정되면 호출되는 메서드
                        override fun onLocationChanged(p0: Location) {
                            showInfo(p0)
                        }
                    }

                    // GPS Provider가 사용 가능하다면 측정을 요청한다.
                    if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true){
                        // 첫 번째 : 요청할 프로바이더
                        // 두 번째 : 측정할 시간 주기. 0을 넣어주면 가장 짧은 주기마다 측정을 한다. (단위 ms)
                        // 세 번째 : 측정할 거리 단위. 0을 넣어주면 가장 짧은 거리마다 측정을 한다. (단위 m)
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, locationListener!!)
                    }

                    // Network Provider가 사용 가능하다면 측정을 요청한다.
                    if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) == true){
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, locationListener!!)
                    }
                } else {
                    requestPermissions(permissionList, 0)
                }
            }

            button2.setOnClickListener {
                // 위치 측정을 중단한다.
                if(locationListener != null){
                    val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
                    locationManager.removeUpdates(locationListener!!)
                    locationListener = null
                }
            }
        }

        setContentView(activityMainBinding.root)
    }

    // 위도와 경도를 출력한다.
    fun showInfo(location:Location){
        if(location != null){
            activityMainBinding.run{
                textView.text = "Provider : ${location.provider}"
                textView2.text = "위도 : ${location.latitude}"
                textView3.text = "경도 : ${location.longitude}"
            }
        }
    }
}







