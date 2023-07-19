package kr.co.softcampus.mini01_lbs02

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kr.co.softcampus.mini01_lbs02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 승인받을 권한 목록
    val permissionList = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    // 위치 측정 리스너
    var myLocationListener:LocationListener? = null

    // 구글 지도 객체를 담을 변수
    lateinit var mainGoogleMap:GoogleMap

    // 현재 사용자 위치에 표시되는 마커
    var myMarker:Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // SplashScreen
        installSplashScreen()

        // 구글 지도 셋팅
        MapsInitializer.initialize(this, MapsInitializer.Renderer.LATEST, null);

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            toolbarMain.run{
                title = "LBSProject"
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {

                    when(it?.itemId){
                        // 현재 위치 메뉴
                        R.id.main_menu_location ->{
                            // 현재 위치를 측정하고 지도를 갱신한다.
                            getMyLocation()
                        }
                    }

                    false
                }
            }
        }

        // 권한을 확인한다.
        requestPermissions(permissionList, 0)

        // 구글 지도를 보여주는 MapFragment 객체를 추출한다.
        val supportMapFragment = supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        // 구글 지도 사용 준비가 완료되면 반응하는 리스너를 등록한다.
        supportMapFragment. getMapAsync{
            // Toast.makeText(this, "구글 지도가 준비되었습니다", Toast.LENGTH_SHORT).show()

            // 구글맵 객체를 변수에 담아준다.
            mainGoogleMap = it

            // 지도의 옵션을 설정한다.
            it.uiSettings.isZoomControlsEnabled = true

            // 현재 위치를 표시한다.
            it.isMyLocationEnabled = true

            // 현재 위치를 표시하는 버튼을 없앤다.
            it.uiSettings.isMyLocationButtonEnabled = false

            // 맵타입
            // it.mapType = GoogleMap.MAP_TYPE_NONE
            // it.mapType = GoogleMap.MAP_TYPE_NORMAL
            // it.mapType = GoogleMap.MAP_TYPE_TERRAIN
            // it.mapType = GoogleMap.MAP_TYPE_SATELLITE
            // it.mapType = GoogleMap.MAP_TYPE_HYBRID

            // 위치 정보를 관리하는 객체를 가지고 온다.
            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

            // 권한 확인
            val a1 = ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
            val a2 = ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
            if(a1 == PackageManager.PERMISSION_GRANTED && a2 == PackageManager.PERMISSION_GRANTED){
                // 현재 저장되어 있는 위치 정보값을 가지고온다.
                val location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                val location2 = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

                // 현재 위치를 표시한다.
                if(location1 != null){
                    setMyLocation(location1)
                } else if(location2 != null){
                    setMyLocation(location2)
                }

                // 현재 위치를 측정하여 지도를 갱신한다
                getMyLocation()
            }
        }
    }

    // 매개변수로 들어오는 위도 경도값을 통해 구글 지도를 해당 위치로 이동시킨다.
    fun setMyLocation(location: Location){
        // 위치 측정을 중단한다.
        if(myLocationListener != null) {
            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            locationManager.removeUpdates(myLocationListener!!)
            myLocationListener = null
        }

//        val str1 = "위도 : ${location.latitude}, 경도 : ${location.longitude}"
//        Toast.makeText(this@MainActivity, str1, Toast.LENGTH_SHORT).show()

        // 위도와 경도를 관리하는 객체를 생성한다.
        val latLng = LatLng(location.latitude, location.longitude)

        // 지도를 이용시키기 위한 객체를 생성한다.
        //val cameraUpdate = CameraUpdateFactory.newLatLng(latLng)
        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15f)
        // 지도를 이동한다.
        // mainGoogleMap.moveCamera(cameraUpdate)
        mainGoogleMap.animateCamera(cameraUpdate)

        // 현재 위치에 마커를 표시한다.
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)

        // 마커 이미지를 변경한다.
//        val markerBitmap = BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mylocation)
//        markerOptions.icon(markerBitmap)

        // 기존에 표시한 마커를 제거한다.
        if(myMarker != null){
            myMarker?.remove()
            myMarker = null
        }
        // myMarker = mainGoogleMap.addMarker(markerOptions)
    }

    fun getMyLocation(){
        val a1 = ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_FINE_LOCATION)
        val a2 = ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
        if(a1 == PackageManager.PERMISSION_GRANTED && a2 == PackageManager.PERMISSION_GRANTED){

            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager

            // 위치 측정 리스너
            myLocationListener = object : LocationListener {
                override fun onLocationChanged(p0: Location) {
                    setMyLocation(p0)
                }
            }

            // 위치 측정 요청
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    0, 0f, myLocationListener!!)
            }

//            if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) == true){
//                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
//                    0, 0f, myLocationListener!!)
//            }
        }
    }
}


















