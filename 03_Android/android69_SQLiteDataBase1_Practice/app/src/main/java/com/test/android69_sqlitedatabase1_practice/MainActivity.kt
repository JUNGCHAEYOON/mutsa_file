package com.test.android69_sqlitedatabase1_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android69_sqlitedatabase1_practice.add.AddFragment
import com.test.android69_sqlitedatabase1_practice.databinding.ActivityMainBinding
import com.test.android69_sqlitedatabase1_practice.main.MainFragment
import com.test.android69_sqlitedatabase1_practice.show.ShowFragment

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    companion object{
        val MAIN_FRAGMENT = "MainFragment"
        val ADD_FRAGMENT = "AddFragment"
        val SHOW_FRAGMENT = "ShowFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        replaceFragment(MAIN_FRAGMENT,false, false)
    }

    // 지정한 fragment 보여주기
    fun replaceFragment(name : String, addToBackStack : Boolean, animate : Boolean){
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // 분기
        var newFragment = when(name){
            MAIN_FRAGMENT->{
                MainFragment()
            }
            ADD_FRAGMENT->{
                AddFragment()
            }
            SHOW_FRAGMENT ->{
                ShowFragment()
            }
            // 얘는 사용되지 않는다!
            else->{
                Fragment()
            }
        }

        // 실행
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

    // backstack 에서 제거
    fun removeFragment(name : String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}

data class UserInfo(var idx : Int, var name : String, var age : Int, var korean : Int)