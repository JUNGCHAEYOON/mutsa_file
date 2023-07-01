package com.test.android55_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.test.android55_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 동물 리스트
    var animalList = mutableListOf<Animal>()
    // 포지션
    var animalPosition : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

        }
    }

    // 지정한 Fragment 를 보여주는 메서드
    fun replaceFragment(name : FragmentName, addToBackStack : Boolean, animate : Boolean){
        // fragment 를 교체상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 fragment 를 담을 변수
        var newFragment : Fragment? = null
        
        // 분기
        when(name){
            FragmentName.FRAGMENT_MAIN -> {
                // Fragment 객체를 생성한다.
                newFragment = MainFragment()
            }
            FragmentName.FRAGMENT_INPUT -> {
                
            }
            FragmentName.FRAGMENT_RESULT -> {
                
            }
        }
        
        // 실행
        if(newFragment != null){
            fragmentTransaction.replace(R.id.containerMain, newFragment)
        }
        
        if(animate == true){
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }
        
        if(addToBackStack == true){
            // Fragment 를 BackStack 에 넣어 이전으로 돌아가는 기능이 동작하도록 하기
            fragmentTransaction.addToBackStack(name.str)
        }
        
        // 교체명령 시작
        fragmentTransaction.commit()
    }
}

enum class FragmentName(val str : String){
    FRAGMENT_MAIN("MainFragment"),
    FRAGMENT_INPUT("InputFragment"),
    FRAGMENT_RESULT("ResultFragment")
}