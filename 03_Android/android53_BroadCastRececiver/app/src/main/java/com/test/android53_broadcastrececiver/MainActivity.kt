package com.test.android53_broadcastrececiver

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android53_broadcastrececiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val permission = arrayOf(
        Manifest.permission.RECEIVE_BOOT_COMPLETED,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.RECEIVE_SMS
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        requestPermissions(permission, 0)

        activityMainBinding.run{
            button.setOnClickListener {
                // 클래스 이름을 지정하여 같은 애플리케이션에 있는 BR 를 동작시킨다.
                val brIntent = Intent(this@MainActivity, TestReceiver::class.java)
                sendBroadcast(brIntent)
            }

            button2.setOnClickListener {
                // 다른 애플리케이션이 가지고 있는 BR 중에 동작 시키고 싶은 BR의 이름을 지정하여준다.
                // 이름은 AndroidManifest.xml에서 확인한다.
                val brIntent = Intent("com.test.testbr")
                sendBroadcast(brIntent)
            }
        }
    }
}











