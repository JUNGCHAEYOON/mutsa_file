package com.test.a01viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.a01viewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 바인딩 객체 생성
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        // 바인딩 객체의 멤버변수 root 로 setContentView함수 실행
        setContentView(activityMainBinding.root)

        // activity_main 의 화면을 만들어주기 위한 area 생성
        // 안해줘도 무방
        activityMainBinding.run{

        }
    }
}