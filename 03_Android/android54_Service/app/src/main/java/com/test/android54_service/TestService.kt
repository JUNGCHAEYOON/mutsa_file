package com.test.android54_service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlin.concurrent.thread

class TestService : Service() {

    val NOTIFICATION_CHANNEL1_ID = "Service"
    val NOTIFICATION_CHANNEL1_NAME = "Service"

    var isRunning = false
    var value = 0

    // Activity가 서비스에 접속하면 전달될 바인더 객체
    val binder = LocalBinder()

    // 외부에서 서비스에 접속하면 자동으로 호출되는 메서드
    // 여기에서는 바인더 객체를 반환한다ㅏ.
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    // 서비가 가동되면 자동으로 호출되는 메서드
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        addNotificationChannel(NOTIFICATION_CHANNEL1_ID, NOTIFICATION_CHANNEL1_NAME)

        // 안드로이드 8.0 이상이라면..
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
            builder.setSmallIcon(android.R.drawable.ic_menu_search)
            builder.setContentTitle("서비스 가동")
            builder.setContentText("서비스가 가동 중입니다")
            val notification = builder.build()
            startForeground(10, notification)
        }

        isRunning = true
        thread {
            while(isRunning == true){
                SystemClock.sleep(500)
                val now = System.currentTimeMillis()
                Log.d("now", "now : $now")
                value++
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    // 서비스가 중지되면 호출되는 메서드
    override fun onDestroy() {
        super.onDestroy()
        // 쓰래드의 while문 중단을 위해..
        isRunning = false
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
    fun getNotificationBuilder(id:String) : NotificationCompat.Builder{
        // 안드로이드 8.0 이상이라면
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val builder = NotificationCompat.Builder(this, id)
            return builder
        } else {
            val builder = NotificationCompat.Builder(this)
            return builder
        }
    }

    // 접속하는 Activity에서 서비스를 추출히기 위해 사용하는 객체
    inner class LocalBinder : Binder(){
        public fun getService() : TestService{
            return this@TestService
        }
    }

    // 변수의 값을 반환하는 메서드
    public fun getNumber():Int{
        return value
    }
}







