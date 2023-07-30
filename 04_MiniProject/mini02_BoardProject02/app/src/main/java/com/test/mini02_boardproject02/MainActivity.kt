package com.test.mini02_boardproject02

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import com.test.mini02_boardproject02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    다음 화면으로 넘어가지 못할 조건들을 설정
//
//    LoginFragment
//    1. 처음 시작시 포커스가 주어지는 입력 요소는 없다.
//    2. 로그인 버튼을 눌렀을 경우 아이디나 비밀번호 입력 요소에
//    입력되어 있는 것이 없을 경우 입력 오류
//    3. 비빌번호 입력 요소에 포커스가 있을 경우 엔터키를 누렀을 경우
//    아이디나 비밀번호 입력 요소에 입력되어 있는 것이 없을 경우 입력 오류
//
//    JoinFragment
//    1. 처음 시작시 아이디 입력요소에 포커스를 준다.
//    2. 다음 버튼을 누르거나 비밀번호 확인 칸에서 엔터키를 눌렀을 경우'
//    2-1. 비어 있는 입력 요소가 있다면 입력 오류
//    2-2. 비밀번호와 비밀번호 확인이 다르면 입력 오류
//
//    AddUserInfoFragment
//    1. 처음 시작시 닉네임 입력 요소에 포커스를 준다.
//    2. 가입 완료 버튼을 누르면 닉네임과 나이 칸이 비어 있다면 입력 오류
//    3. 체크 박스 관련 작업은 저랑 같이 할게요~
//
//    ModifyUserFragment
//    1. 처음 시작시 포커스를 주지 않는다.
//    2. 비밀번호와 비밀번화 확인은 서로 다를 때만 입력 오류
//    3. 닉네임과 나이는 비어있을 경우 입력 오류
//    4. 수정 완료 버튼을 눌렀을 때 위의 처리를 해준다.
//
//    PostWriteFragment
//    1. 처음 시작시 제목에 포커스를 준다.
//    2. 제목과 내용이 비어 있으면 입력 오류
//
//    PostModifyFragment
//    1. 처음 시작시 포커스를 주지 않는다.
//    2. Done 메뉴를 눌렀을 때 제목과 내용이 비어 있으면 입력 오류

    lateinit var activityMainBinding: ActivityMainBinding

    var newFragment:Fragment? = null
    var oldFragment:Fragment? = null

    companion object{
        val LOGIN_FRAGMENT = "LoginFragment"
        val JOIN_FRAGMENT = "JoinFragment"
        val ADD_USER_INFO_FRAGMENT = "AddUserInfoFragment"
        val BOARD_MAIN_FRAGMENT = "BoardMainFragment"
        val POST_WRITE_FRAGMENT = "PostWriteFragment"
        val POST_READ_FRAGMENT = "PostReadFragment"
        val POST_MODIFY_FRAGMENT = "PostModifyFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        // splashScreenCustomizing(splashScreen)

        // SystemClock.sleep(3000)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        replaceFragment(LOGIN_FRAGMENT, false, null)
    }

    // SplashScreen 커스터마이징
    fun splashScreenCustomizing(splashScreen: SplashScreen){
        // SplashScreen이 사라질 때 동작하는 리스너를 설정한다.
        splashScreen.setOnExitAnimationListener{
            // 가로 비율 애니메이션
            val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 2f, 1f, 0f)
            // 세로 비율 애니메이션
            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 2f, 1f, 0f)
            // 투명도
            val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 1f, 0.5f, 0f)

            // 애니메이션 관리 객체를 생성한다.
            // 첫 번째 : 애니메이션을 적용할 뷰
            // 나머지는 적용한 애니메이션 종류
            val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(it.iconView, scaleX, scaleY, alpha)
            // 애니메이션 적용을 위한 에이징
            objectAnimator.interpolator = AnticipateInterpolator()
            // 애니메이션 동작 시간
            objectAnimator.duration = 1000
            // 애니메이션이 끝났을 때 동작할 리스너
            objectAnimator.addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)

                    // SplashScreen을 제거한다.
                    it.remove()
                }
            })
            
            // 애니메이션 가동
            objectAnimator.start()
        }
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:String, addToBackStack:Boolean, bundle:Bundle?){

        SystemClock.sleep(200)

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
            BOARD_MAIN_FRAGMENT -> BoardMainFragment()
            POST_WRITE_FRAGMENT -> PostWriteFragment()
            POST_READ_FRAGMENT -> PostReadFragment()
            POST_MODIFY_FRAGMENT -> PostModifyFragment()
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

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name:String){
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}