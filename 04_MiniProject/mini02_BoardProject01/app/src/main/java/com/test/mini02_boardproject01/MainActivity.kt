package com.test.mini02_boardproject01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.window.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.transition.MaterialSharedAxis
import com.test.mini02_boardproject01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    var newFragment:Fragment? = null
    var oldFragment:Fragment? = null

    companion object{
        val LOGIN_FRAGMENT = "LoginFragment"
        val JOIN_FRAGMENT = "JoinFragment"
        val ADD_USER_INFO_FRAGMENT = "AddUserInfoFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        // SplashScreen을 적용한다.
        // setContentView 전에 작성해야 한다.
        val splashScreen = installSplashScreen()

        setContentView(activityMainBinding.root)

        activityMainBinding.run{

        }
    }


    fun splashScreenCustomizing(splashScreen: SplashScreen){

    }

    fun replaceFragment(name : String, addToBackStack : Boolean, bundle : Bundle?){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        // newFragment 에 Fragment가 들어있으면 oldFragment에 넣어준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 새로운 Fragment를 담을 변수
        newFragment = when(name){
            LOGIN_FRAGMENT -> LoginFragment()
            JOIN_FRAGMENT -> JoinFragment()
            ADD_USER_INFO_FRAGMENT -> AddUserInfoFragment()
            else -> Fragment()
        }

        newFragment?.arguments = bundle

        if(newFragment != null) {

            // oldFragment -> newFragment로 이동
            // oldFramgent : exit
            // newFragment : enter

            // oldFragment <- newFragment 로 되돌아가기
            // oldFragment : reenter
            // newFragment : return
            // 애니메이션 설정
            if(oldFragment != null){
                oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
                oldFragment?.enterTransition = null
                oldFragment?.returnTransition = null
            }

            newFragment?.exitTransition = null
            newFragment?.reenterTransition = null
            newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
            newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)

            // Fragment를 교채한다.
            fragmentTransaction.replace(R.id.mainContainer, newFragment!!)

            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    fun removeFragment(name : String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}