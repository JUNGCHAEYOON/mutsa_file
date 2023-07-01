package com.test.android50_pendingintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android50_pendingintent.databinding.ActivityNotification2Binding

class Notification2Activity : AppCompatActivity() {

    lateinit var notification2Binding: ActivityNotification2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notification2Binding = ActivityNotification2Binding.inflate(layoutInflater)
        setContentView(notification2Binding.root)

        notification2Binding.run{
            textViewNo2.run{
                val data3 = intent.getIntExtra("data3", 0)
                val data4 = intent.getStringExtra("data4")

                text = "data3 : ${data3}\n"
                append("data4 : ${data4}")
            }
        }
    }
}







