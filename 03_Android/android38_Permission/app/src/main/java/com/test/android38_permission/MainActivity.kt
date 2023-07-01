package com.test.android38_permission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android38_permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 확인 받을 권한 목록
    // 이 권한 목록 중에 확인이 불 필요하거나 이미 허용되어 있는 권한은 제외한다
    val permissionList = arrayOf(
        Manifest.permission.INTERNET,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.WRITE_CONTACTS,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.run{
                setOnClickListener {
                    // 권한 확인을 요청한다.
                    requestPermissions(permissionList, 0)
                }
            }
        }
    }

    // requestPermission 메서드를 통해 권한을 요청하여 요청 작업이 끝나면 호출되는 메서드
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}