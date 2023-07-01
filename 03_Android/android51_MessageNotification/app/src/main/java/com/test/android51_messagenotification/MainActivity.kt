package com.test.android51_messagenotification

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.graphics.drawable.IconCompat
import com.test.android51_messagenotification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val permissionList = arrayOf(
        Manifest.permission.POST_NOTIFICATIONS
    )

    lateinit var activityMainBinding: ActivityMainBinding

    val NOTIFICATION_CHANNEL1_ID = "Style"
    val NOTIFICATION_CHANNEL1_NAME = "Style Notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        requestPermissions(permissionList, 0)

        addNotificationChannel(NOTIFICATION_CHANNEL1_ID, NOTIFICATION_CHANNEL1_NAME)

        activityMainBinding.run{
            button.setOnClickListener {
                // BigPicture
                // 메시지를 펼쳤을 때 이미지가 나타난다.
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("Big Picture")
                builder.setContentText("Big Picture Notification")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)

                // BigPicture 설정
                val big = NotificationCompat.BigPictureStyle(builder)
                // 보여줄 이미지를 설정한다.
                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_android)
                big.bigPicture(bitmap)
                big.setBigContentTitle("Big Content Title")
                big.setSummaryText("Summary Text")

                val notification = builder.build()
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(10, notification)
            }

            button2.setOnClickListener {
                // Big Text
                // 펼쳤을 때 장문의 문자열이 나온다.
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("Big Text")
                builder.setContentText("Big Text Notification")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)

                // Big Text 설정
                val big = NotificationCompat.BigTextStyle(builder)
                big.setBigContentTitle("Big Content Title")
                big.setSummaryText("Summary Text")
                big.bigText("""동해물과 백두산이
                    |마르고 달도록
                    |하느님이 보우하사
                    |우리나라 만세
                """.trimMargin())

                val notification = builder.build()
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(20, notification)
            }

            button3.setOnClickListener {
                // Inbox
                // 한 줄의 문자열을 다수 보여준다.
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("InBox")
                builder.setContentText("InBox Notification")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)

                // InBox 설정
                val inbox = NotificationCompat.InboxStyle(builder)
                inbox.setSummaryText("Summary Text")
                inbox.setBigContentTitle("InBox Style")

                inbox.addLine("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
                inbox.addLine("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
                inbox.addLine("ccccccccccccccccccccccccccccccccccccccc")
                inbox.addLine("ddddddddddddddddddddddddddddddddddddd")
                inbox.addLine("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee")
                inbox.addLine("ffffffffffffffffffffffffffffffffffffffff")
                inbox.addLine("gggggggggggggggggggggggggggggggggggggggggg")

                val notification = builder.build()
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(30, notification)
            }

            button4.setOnClickListener {
                // Message
                // 채팅 화면 처럼 구성할 수 있다.
                val builder = getNotificationBuilder(NOTIFICATION_CHANNEL1_ID)
                builder.setContentTitle("Message")
                builder.setContentText("Message Notification")
                builder.setSmallIcon(android.R.drawable.ic_menu_search)

                // 사람1
                val personBuilder1 = Person.Builder()
                val icon1 = IconCompat.createWithResource(this@MainActivity, android.R.drawable.ic_dialog_email)
                personBuilder1.setIcon(icon1)
                personBuilder1.setName("홍길동")
                val person1 = personBuilder1.build()

                // 사람2
                val personBuilder2 = Person.Builder()
                val icon2 = IconCompat.createWithResource(this@MainActivity, R.mipmap.ic_launcher)
                personBuilder2.setIcon(icon2)
                personBuilder2.setName("김길동")
                val person2 = personBuilder2.build()

                // Message Style 설정
                val msg = NotificationCompat.MessagingStyle(person1)

                // 대화 내용을 설정한다.
                msg.addMessage("첫 번째 메시지", System.currentTimeMillis(), person1)
                msg.addMessage("두 번째 메시지", System.currentTimeMillis(), person2)
                msg.addMessage("세 번째 메시지", System.currentTimeMillis(), person1)
                msg.addMessage("네 번째 메시지", System.currentTimeMillis(), person2)

                builder.setStyle(msg)

                val notification = builder.build()
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
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
}