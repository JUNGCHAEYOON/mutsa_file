package com.test.android09_includeotherlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android09_includeotherlayout.databinding.ActivityMainBinding
import com.test.android09_includeotherlayout.databinding.SecondBinding
import com.test.android09_includeotherlayout.databinding.ThirdBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
//    lateinit var secondBinding: SecondBinding
//    lateinit var thirdBinding: ThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
//        secondBinding = SecondBinding.inflate(layoutInflater)
//        thirdBinding = ThirdBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.textView3.text = "첫 번째 문자열 입니다"
//        secondBinding.textView.text = "두 번째 문자열 입니다"
//        thirdBinding.textView2.text = "세 번째 문자열 입니다"

        // include를 통해 View에 접근한다.
        activityMainBinding.secondLayout.textView.text = "두 번째 문자열 입니다"
        activityMainBinding.thirdLayout.textView2.text = "세 번째 문자열 입니다"
    }
}