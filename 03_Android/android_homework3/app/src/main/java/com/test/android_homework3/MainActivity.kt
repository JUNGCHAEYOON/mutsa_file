package com.test.android_homework3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.test.android_homework3.database.login.LoginDAO
import com.test.android_homework3.databinding.ActivityMainBinding
import com.test.android_homework3.domain.createPassword.CreatePasswordFragment
import com.test.android_homework3.domain.login.LoginFragment
import com.test.android_homework3.domain.main.MainFragment
import com.test.android_homework3.domain.main.memomain.MemoMainFragment
import com.test.android_homework3.domain.main.memomain.memoAdd.MemoAddFragment
import com.test.android_homework3.domain.main.memomain.memoShow.MemoShowFragment
import com.test.android_homework3.domain.main.memomain.memoShow.memoEdit.MemoEditFragment

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    companion object{
        // 화면 분기
        // intro
        val CREATE_PASSWORD_FRAGMENT = "CreatePasswordFragment"
        val LOGIN_FRAGMENT = "LoginFragment"
        val MAIN_FRAGMENT = "MainFragment"

        // main
        val MEMO_MAIN_FRAGMENT = "MemoMainFragment"

        // memoMain
        val MEMO_ADD_FRAGMENT = "MemoAddFragment"
        val MEMO_SHOW_FRAGMENT = "MemoShowFragment"

        // memoShow
        val MEMO_EDIT_FRAGMENT = "MemoEditFragment"

        // 현재 선택한 index
        var categoryIdx : Int = -1
        var memoIdx : Int = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            // onCreate 시 로그인 DAO 로 부터 비밀번호 정보가 있는지 불러옴
            when(LoginDAO.passwordExists(this@MainActivity)){
                false->{
                    // 어플리케이션에 비밀번호가 설정되지 않았을경우
                    replaceFragment(CREATE_PASSWORD_FRAGMENT,false,false)
                }
                true->{
                    // 비밀번호가 있을경우
                    replaceFragment(LOGIN_FRAGMENT,false,false)
                }
            }
        }
    }

    fun replaceFragment(name : String, addToBackStack : Boolean, animate : Boolean){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var newFragment = when(name){
            // 화면분기
            CREATE_PASSWORD_FRAGMENT->{
                CreatePasswordFragment()
            }
            LOGIN_FRAGMENT ->{
                LoginFragment()
            }
            MAIN_FRAGMENT->{
                MainFragment()
            }
            MEMO_MAIN_FRAGMENT->{
                MemoMainFragment()
            }
            MEMO_ADD_FRAGMENT->{
                MemoAddFragment()
            }
            MEMO_SHOW_FRAGMENT->{
                MemoShowFragment()
            }
            MEMO_EDIT_FRAGMENT->{
                MemoEditFragment()
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