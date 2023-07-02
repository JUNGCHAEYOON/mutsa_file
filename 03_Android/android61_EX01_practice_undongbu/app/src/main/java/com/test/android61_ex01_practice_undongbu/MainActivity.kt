package com.test.android61_ex01_practice_undongbu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android61_ex01_practice_undongbu.databinding.ActivityMainBinding
import com.test.android61_ex01_practice_undongbu.input.InputFragment
import com.test.android61_ex01_practice_undongbu.main.MainFragment
import com.test.android61_ex01_practice_undongbu.modify.ModifyFragment
import com.test.android61_ex01_practice_undongbu.show.ShowFragment

class MainActivity : AppCompatActivity() {

    lateinit var  activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

        }
    }

    // 지정한 Fragment를 fcv_main 에 담는 메서드
    fun replaceFragment(name : FragmentName, addToBackStack : Boolean, animate : Boolean){
        // fragment 를 교체상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // 새로운 fragment를 담을 변수
        var newFragment : Fragment? = null

        // 이름으로 분기
        when(name){
            FragmentName.FRAGMENT_MAIN->{
                newFragment = MainFragment()
            }
            FragmentName.FRAGMENT_INPUT->{
                newFragment = InputFragment()
            }
            FragmentName.FRAGMENT_MODIFY->{
                newFragment = ModifyFragment()
            }
            FragmentName.FRAGMENT_SHOW->{
                newFragment = ShowFragment()
            }
        }

        // 해당 fragment 로 실행
        fragmentTransaction.replace(R.id.fcv_main, newFragment)

        if(animate){
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        }

        if(addToBackStack){
            fragmentTransaction.addToBackStack(name.str)
        }

        fragmentTransaction.commit()

    }

    // backStack 에서 제거
    fun removeFragment(name : FragmentName){
        supportFragmentManager.popBackStack(name.str, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

// fragment 이름을 이용한 분기
enum class FragmentName(var str : String){
    FRAGMENT_MAIN("MainFragment"),
    FRAGMENT_INPUT("InputFragment"),
    FRAGMENT_SHOW("ShowFragment"),
    FRAGMENT_MODIFY("ModifyFragment")
}