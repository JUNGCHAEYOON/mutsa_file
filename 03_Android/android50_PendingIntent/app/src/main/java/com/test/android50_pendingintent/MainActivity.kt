package com.test.android50_pendingintent

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.test.android50_pendingintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding:ActivityMainBinding

    val permissionList = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS
    )

    val NOTIFICATION_CHANNEL1_ID = "Pending"
    val NOTIFICATION_CHANNEL1_NAME = "Pending Intent"

    lateinit var notificationManager: NotificationManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        requestPermissions(permissionList, 0)

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        addNotificationChannel(NOTIFICATION_CHANNEL1_ID, NOTIFICATION_CHANNEL1_NAME)

        activityMainBinding.run{
            button.setOnClickListener {
                val builder = getNoficationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("Notification 1")
                builder.setContentText("알림 메시지1 입니다")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)

                // 메시지를 터치하면 자동으로 메시지를 제거한다.
                builder.setAutoCancel(true)

                // 메시지를 터치하면 Activity가 실행되도록 한다.
                val newIntent = Intent(this@MainActivity, Notification1Activity::class.java)
                
                newIntent.putExtra("data1", 100)
                newIntent.putExtra("data2", "안녕하세요")
                
                val pendingIntent1 = PendingIntent.getActivity(this@MainActivity, 10,
                                    newIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                builder.setContentIntent(pendingIntent1)

                // Action 설정
                val newIntent2 = Intent(this@MainActivity, Notification2Activity::class.java)
                newIntent2.putExtra("data3", 200)
                newIntent2.putExtra("data4", "반갑습니다")

                val pendingIntent2 = PendingIntent.getActivity(this@MainActivity, 100,
                    newIntent2, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

                // Action을 생성한다.
                // 첫 번째 : 아이콘 설정
                // 두 번째 : 표시할 문자열
                // 세 번째 : PendingIntent
                val builder2 = NotificationCompat.Action.Builder(
                    android.R.drawable.ic_dialog_alert, "Action", pendingIntent2)
                val action2 = builder2.build()
                builder.addAction(action2)

                val notification = builder.build()
                notificationManager.notify(10, notification)
            }
        }
    }


    // Notification Channel을 등록하는 메서드
    // 첫 번째 : 코드에서 채널을 관리하기 위한 이름
    // 두 번째 : 사용자에게 노출 시킬 이름
    fun addNotificationChannel(id:String, name:String){
        // 안드로이드 8.0 이상일 때만 동작하게 한다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 알림 메시지를 관리하는 객체를 추출한다.
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            // id를 통해 NotificationChannel 객체를 추출한다.
            // 채널이 등록된 적이 없다면 null을 반환한다.
            val channel = notificationManager.getNotificationChannel(id)
            // 채널이 등록된 적이 없다면...
            if(channel == null){
                // 채널 객체를 생성한다.
                val newChannel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
                // 단말기에 LED 램프가 있다면 램프를 사용하도록 설정한다.
                newChannel.enableLights(true)
                // LED 램프의 색상을 설정한다.
                newChannel.lightColor = Color.RED
                // 진동을 사용할 것인가?
                newChannel.enableVibration(true)
                // 채널을 등록한다.
                notificationManager.createNotificationChannel(newChannel)
            }

        }
    }

    // Notification 메시지 관리 객체를 생성하는 메서드
    // Notification 채널 id를 받는다.
    fun getNoficationBuilder(id:String) : NotificationCompat.Builder{
        // 안드로이드 8.0 이상이라면
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val builder = NotificationCompat.Builder(this, id)
            return builder
        } else {
            val builder = NotificationCompat.Builder(this)
            return builder
        }
    }
}