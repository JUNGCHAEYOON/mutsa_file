package com.test.android41_activitylifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log



//Activity가 실행 : onCreate -> onStart -> onResume
//다른 Activity의 화면이 보일 때 : onPause -> Activity 일시 정지
//다시 Activity의 화면이 보일 때 : onResume -> Activity 다시 동작
//Activity의 화면이 완전히 보이지 않게 될 때 : onPause -> onStop
//Activity의 화면이 다시 보여지게 될 때 : onRestart -> onstart -> onResume -> Activitiy 다시 동작
//Activity가 종료될 때 : onPause -> onStop -> onDestroy


// onCreate : Activity 처음 실행시나 화면 회전 발생시에 대한 처리
// onPause : 일시 정지되기 전에 대한 처리
// onResume : 일시 정지 되고 난 후 다시 돌아올 때의 처리
// onDestroy : Activity가 종료되기 전에 필요한 처리



class MainActivity : AppCompatActivity() {
    // 액티비티가 처음 실행 될 때 자동으로 호출된다.
    // 액티비티가 관리할 View들을 생성하고 View들에 대한 초기작을 수행한다.
    // 화면 회전이 발생 했을 때의 처리도 수행한다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("lion", "onCreate")
    }

    // 액티비티가 처음 생성될 때 onCreate 다음으로 호출된다.
    // 액티비가 보이지 않는 상태가 되었다가 다시 보이게 될 때 onRestart 다음에 호출된다
    override fun onStart() {
        super.onStart()
        Log.d("lion", "onStart")
    }

    // 액티비티가 처음 생성될 때 onStart 다음으로 호출된다.
    // 다른 액티비티가 보여지고 다시 이 액티비티가 보여 질 때 호출된다.
    // 액티비티가 보여지지 않았다가 다시 보일 때 onStart 호출된다.
    override fun onResume() {
        super.onResume()
        Log.d("lion", "onResume")
    }

    // 다른 액티비티가 눈에 보여질 때 호출
    // 현재 액티비티는 일시 정지된다.
    override fun onPause() {
        super.onPause()
        Log.d("lion", "onPause")
    }

    // 현재 액티비티의 화면이 완전히 보이지 않게 될 때
    // onPause 다음에 호출된다.
    override fun onStop() {
        super.onStop()
        Log.d("lion", "onStop")
    }

    // onStop이 호출된 이후 다시 액티비티가 보여지는 상태가 될 때
    // 호출된다.
    override fun onRestart() {
        super.onRestart()
        Log.d("lion", "onRestart")
    }

    // 액티비티가 완전 종료될 때 호출
    override fun onDestroy() {
        super.onDestroy()
        Log.d("lion", "onDestroy")
    }

}







