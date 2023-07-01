package com.test.android41_startactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android41_startactivity.databinding.ActivityMainBinding
import com.test.android41_startactivity.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var activitySecondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(activitySecondBinding.root)

        activitySecondBinding.run{
            buttonSecond.run{
                setOnClickListener {
                    // 현재 Activity를 종료하고 BackStack에서 제거한다
                    finish()
                }
            }
        }
    }
}