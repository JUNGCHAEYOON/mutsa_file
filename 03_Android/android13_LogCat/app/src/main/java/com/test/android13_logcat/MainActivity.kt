package com.test.android13_logcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // LogCat : 안드로이드 스튜디오에서 에뮬레이터나 단말기에서 사용하는 콘솔
        // 일반출력
        // tag를 System.in으로 설정하여 Log.i 메서드로 로그캣에 출력한다.
        println("안녕하세요")

        // information, 정보를 출력하는 용도로 사용한다.
        Log.i("테스트", "반갑습니다")
        // debug, 개발중에 출력해보고 싶은 것을 있을 때 사용한다.
        Log.d("테스트", "aaa")
        // error, 오류 메세지를 출력해보고 싶을 때 사용한다.
        Log.e("테스트", "bbb")
        // warning, 경고 메시지를 출력해보고 싶을 때 사용한다.
        Log.w("테스트", "ccc")
        // verbose, 기타 용도로 사용한다.
        Log.v("테스트", "ddd")
    }
}