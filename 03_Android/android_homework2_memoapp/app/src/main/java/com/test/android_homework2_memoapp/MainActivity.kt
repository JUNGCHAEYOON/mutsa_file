package com.test.android_homework2_memoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android_homework2_memoapp.databinding.ActivityMainBinding
import com.test.android_homework2_memoapp.domain.add.AddFragment
import com.test.android_homework2_memoapp.domain.main.MainFragment
import com.test.android_homework2_memoapp.domain.modify.ModifyFragment
import com.test.android_homework2_memoapp.domain.show.ShowFragment

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    companion object{
        // 화면 분기
        val MAIN_FRAGMENT = "MainFragment"
        val ADD_FRAGMENT = "AddFragment"
        val SHOW_FRAGMENT = "ShowFragment"
        val MODIFY_FRAGMENT = "ModifyFragment"

        // SHOW_FRAGMENT 에서 보여줄 data 의 현재 idx
        var nowIdx : Int = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            replaceFragment(MAIN_FRAGMENT,false,false)
        }
    }

    fun replaceFragment(name : String, addToBackStack : Boolean, animate : Boolean){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var newFragment = when(name){
            MAIN_FRAGMENT->{
                MainFragment()
            }
            ADD_FRAGMENT->{
                AddFragment()
            }
            SHOW_FRAGMENT->{
                ShowFragment()
            }
            MODIFY_FRAGMENT->{
                ModifyFragment()
            }
            // else 문은 사용하지 않음!
            else->{
                Fragment()
            }
        }

        // fragment 교체 실행
        run{
            fragmentTransaction.replace(R.id.fcv_main, newFragment)
            if(animate){
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }
            if(addToBackStack){
                fragmentTransaction.addToBackStack(name)
            }
            fragmentTransaction.commit()
        }
    }

    fun removeFragment(name : String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
