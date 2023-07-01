package com.test.android49_notification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.test.android49_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 확인할 권한
    // 안드로이드 13버전 부터는 Notification 사용을 위해
    // POST_NOTIFICATIONS 권한을 사용자로부터 확인 받아야 한다.
    val permissionList = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS
    )

    // Notification Channel을 코드에서 구분하기 위한 이름
    val NOTIFICATION_CHANNEL1_ID = "CHANNEL1"
    val NOTIFICATION_CHANNEL2_ID = "CHANNEL2"
    // 사용자게 노출 시킬 채널의 이름
    val NOTIFICATION_CHANNEL1_NAME = "첫 번째 채널"
    val NOTIFICATION_CHANNEL2_NAME = "두 번째 채널"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 권한 확인 요청
        requestPermissions(permissionList, 0)

        // Notification Channel을 등록한다.
        addNotificationChannel(NOTIFICATION_CHANNEL1_ID, NOTIFICATION_CHANNEL1_NAME)
        addNotificationChannel(NOTIFICATION_CHANNEL2_ID, NOTIFICATION_CHANNEL2_NAME)

        activityMainBinding.run{
            button.setOnClickListener {
                // NotificationBuilder를 가져온다.
                val builder = getNoficationBuilder(NOTIFICATION_CHANNEL1_ID)
                // 작은 아이콘
                builder.setSmallIcon(android.R.drawable.ic_menu_search)
                // 큰 아이콘
                val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
                builder.setLargeIcon(bitmap)
                // 숫자 설정
                builder.setNumber(100)
                // 타이틀 설정
                builder.setContentTitle("Content Title 1")
                // 메시지 설정
                builder.setContentText("Content Text 1")

                // 메시지 객체를 생성한다
                val notification = builder.build()
                // 알림 메시지르 ㄹ관리하는 객체를 추출한다.
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                // 메시지를 표시한다.
                // 첫번째 매개변수에 넣어주는 정수는 단말기 전체에서 메시지를 구분하기 위한 값
                // 값은 값으로 메시지를 계속 보여주면 메시지가 갱신 것이고
                // 다른 값으로 메시즈를 계속 보여주면 메시지가 각각 따로 나타난다.
                notificationManager.notify(10, notification)
            }

            button2.setOnClickListener {
                // NotificationBuilder를 가져온다.
                val builder = getNoficationBuilder(NOTIFICATION_CHANNEL1_ID)
                // 작은 아이콘
                builder.setSmallIcon(android.R.drawable.ic_menu_search)
                // 타이틀 설정
                builder.setContentTitle("Content Title 2")
                // 메시지 설정
                builder.setContentText("Content Text 2")

                // 메시지 객체를 생성한다
                val notification = builder.build()
                // 알림 메시지르 ㄹ관리하는 객체를 추출한다.
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                // 메시지를 표시한다.
                notificationManager.notify(20, notification)
            }

            button3.setOnClickListener {
                // NotificationBuilder를 가져온다.
                val builder = getNoficationBuilder(NOTIFICATION_CHANNEL2_ID)
                // 작은 아이콘
                builder.setSmallIcon(android.R.drawable.ic_menu_search)
                // 타이틀 설정
                builder.setContentTitle("Content Title 3")
                // 메시지 설정
                builder.setContentText("Content Text 3")

                // 메시지 객체를 생성한다
                val notification = builder.build()
                // 알림 메시지르 ㄹ관리하는 객체를 추출한다.
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                // 메시지를 표시한다.
                notificationManager.notify(30, notification)
            }

            button4.setOnClickListener {
                // NotificationBuilder를 가져온다.
                val builder = getNoficationBuilder(NOTIFICATION_CHANNEL2_ID)
                // 작은 아이콘
                builder.setSmallIcon(android.R.drawable.ic_menu_search)
                // 타이틀 설정
                builder.setContentTitle("Content Title 4")
                // 메시지 설정
                builder.setContentText("Content Text 4")

                // 메시지 객체를 생성한다
                val notification = builder.build()
                // 알림 메시지르 ㄹ관리하는 객체를 추출한다.
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                // 메시지를 표시한다.
                notificationManager.notify(40, notification)
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









