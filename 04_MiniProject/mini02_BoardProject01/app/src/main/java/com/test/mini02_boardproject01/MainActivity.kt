package com.test.mini02_boardproject01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.mini02_boardproject01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        // SplashScreen을 적용한다.
        // setContentView 전에 작성해야 한다.
        installSplashScreen()

        setContentView(activityMainBinding.root)

        activityMainBinding.run{

        }
    }

//    //name : Fragment 의 이름 ex) MAIN_FRAGMENT -> MainFragment()
//    //addToBackStack :	백스택에 저장을 true 할경우 뒤로가기시 이전 화면으로 돌아감
//    //animate : 화면전환시 애니메이션 실행 여부, 해당 프래그먼트를 스택에서 꺼냄
//    fun replaceFragment(name : String, addToBackStack : Boolean, animate : Boolean){
//
//        // 프래그먼트 전환 상태 실행
//        val fragmentTransaction = supportFragmentManager.beginTransaction()
//
//        // 현재 선택한 화면 이름으로 분기
//        var newFragment = when(name){
//
//            // else 문은 사용하지 않음!
//            else->{
//                Fragment()
//            }
//        }
//
//        // fragment 교체 실행
//        run{
//            fragmentTransaction.replace(R.id.fcv_main, newFragment)
//
//            // animate 여부
//            if(animate){
//                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            }
//
//            // addToBackStack 여부 true 일경우 뒤로가기시 이전 화면으로 재전환
//            // false 일경우 프로그램 종료됨
//            // onBackPressed 를 따로 override 해주어야 뒤로가기시 안꺼짐
//            if(addToBackStack){
//                fragmentTransaction.addToBackStack(name)
//            }
//            fragmentTransaction.commit()
//        }
//    }
//
//    fun removeFragment(name : String){
//        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//    }
}