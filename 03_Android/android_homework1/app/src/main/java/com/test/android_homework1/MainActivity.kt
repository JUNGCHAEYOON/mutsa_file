package com.test.android_homework1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android_homework1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    companion object{
        val MF = "MainFragment"
        val AF = "AddFragment"
        val SF = "ShowFragment"

        var chosenIdx : Int = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            replaceFragment(MF,false,false)
        }
    }

    fun replaceFragment(name : String, addToBackStack : Boolean, animate : Boolean){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var newFragment = when(name){
            MF ->{
                MainFragment()
            }
            AF ->{
                AddFragment()
            }
            SF ->{
                ShowFragment()
            }

            else->{
                Fragment()
            }
        }

        run{
            fragmentTransaction.replace(R.id.fcv_main, newFragment)
            if(animate) fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            if(addToBackStack) fragmentTransaction.addToBackStack(name)
            fragmentTransaction.commit()
        }
    }

    fun removeFragment(name : String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}