package com.test.android41_startactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android41_startactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            buttonMain.run{
                setOnClickListener {
                    // 다른 액티비티를 실행한다.
                    // Intent 객체를 생성한다.
                    val secondIntent = Intent(this@MainActivity, SecondActivity::class.java)
                    startActivity(secondIntent)
                }
            }
        }
    }
}