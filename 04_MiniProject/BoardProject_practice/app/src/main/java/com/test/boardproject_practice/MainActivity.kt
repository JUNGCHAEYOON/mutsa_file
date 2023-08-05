package com.test.boardproject_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.test.boardproject_practice.MODEL.DATA.UserClass
import com.test.boardproject_practice.databinding.ActivityMainBinding
import android.Manifest
import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import com.test.boardproject_practice.VIEW.LOGIN.JoinFragment
import com.test.boardproject_practice.VIEW.LOGIN.LoginFragment
import com.test.boardproject_practice.VIEW.LOGIN.AddUserInfoFragment
import com.test.boardproject_practice.VIEW.MAIN.BoardMainFragment
import com.test.boardproject_practice.VIEW.POST.PostModifyFragment
import com.test.boardproject_practice.VIEW.POST.PostReadFragment
import com.test.boardproject_practice.VIEW.POST.PostWriteFragment
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    // 바인딩 객체
    lateinit var activityMainBinding : ActivityMainBinding

    // replaceFragment 함수에 사용할 backstack 변수
    var newFragment : Fragment? = null
    var oldFragment : Fragment? = null

    // 화면 분기
    companion object{
        //LOGIN
        val LOGIN = "LoginFragment"
        val JOIN = "JoinFragment"
        //MAIN
        val BOARD_MAIN = "BoardMainFragment"
        val ADD_USER_INFO = "AddUserInfoFragment"
        //POST
        val POST_WRITE = "PostWriteFragment"
        val POST_READ = "PostReadFragment"
        val POST_MODIFY = "PostModifyFragment"
    }

    // 로그인한 사용자의 정보를 담을 객체
    lateinit var loginUserClass : UserClass

    // 확인할 권한 목록
    val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_MEDIA_LOCATION,
        Manifest.permission.INTERNET
    )

    // 게시판 종류
    val boardTypeList = arrayOf(
        "자유게시판", "유머게시판", "질문게시판", "스포츠게시판"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 스플래시 실행
        installSplashScreen()

        // 뷰바인딩 연결
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 권한 요청
        requestPermissions(permissionList, 0)

        // 로그인프래그먼트로 실행하기
        activityMainBinding.run{
            replaceFragment(LOGIN, false, null)
        }
    }

    fun replaceFragment(name : String, addToBackStack : Boolean, bundle : Bundle?){
        SystemClock.sleep(200)
        // 시작
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if(newFragment != null){
            oldFragment = newFragment
        }
        // 분기
        newFragment = when(name){
            // LOGIN
            LOGIN -> LoginFragment()
            JOIN -> JoinFragment()
            // MAIN
            ADD_USER_INFO -> AddUserInfoFragment()
            BOARD_MAIN -> BoardMainFragment()
            // POST
            POST_WRITE -> PostWriteFragment()
            POST_READ -> PostReadFragment()
            POST_MODIFY -> PostModifyFragment()

            else -> Fragment()
        }

        // 번들 데이터 설정
        newFragment?.arguments = bundle

        if(newFragment != null){
            // 애니메이션
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

            // replace
            fragmentTransaction.replace(R.id.fcv_main, newFragment!!)
            if(addToBackStack) fragmentTransaction.addToBackStack(name)
            fragmentTransaction.commit()
        }
    }

    fun removeFragment(name:String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun showSoftInput(view : View){
        view.requestFocus()

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        thread {
            SystemClock.sleep(200)
            inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}