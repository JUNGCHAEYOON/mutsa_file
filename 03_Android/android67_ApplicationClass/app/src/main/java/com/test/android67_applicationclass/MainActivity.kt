package com.test.android67_applicationclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android67_applicationclass.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        activityMainBinding.run{
            // ApplicatonClass
            // 애플리케이션 당 하나씩 둘 수 있으며 어디서든지 접근이 가능하다.
            // Application을 상속받은 클래스를 만들고 프로퍼티들을 정의한다.
            // AndroidManfest.xml에 application 태그의 name 속성에 지정하여 사용한다.

            val appClass = application as AppClass
            appClass.value1 = 100
            appClass.value2 = 11.11
            appClass.value3 = "문자열1"

            val secondIntent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(secondIntent)
        }

        setContentView(activityMainBinding.root)
    }
}