package com.test.mini02_boardproject01

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import com.test.mini02_boardproject01.databinding.FragmentBoardMainBinding
import com.test.mini02_boardproject01.databinding.HeaderBoardMainBinding

class BoardMainFragment : Fragment() {
    lateinit var fragmentBoardMainBinding: FragmentBoardMainBinding
    lateinit var mainActivity: MainActivity

    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null

    var nowPostType = 0

    companion object {
        val POST_LIST_FRAGMENT = "PostListFragment"
        val MODIFY_USER_FRAGMENT = "ModifyUserFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentBoardMainBinding = FragmentBoardMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentBoardMainBinding.run {
            toolbarMain.run {
                title = if (nowPostType == 0) {
                    "전체게시판"
                } else {
                    mainActivity.boardTypeList[nowPostType - 1]
                }

                setNavigationOnClickListener {
                    drawerLayoutBoardMain.open()
                    true
                }
            }

            // DrawerView
            navigationViewBoardMain.run {

                // 헤더설정
                val headerBoardMainBinding = HeaderBoardMainBinding.inflate(inflater)
                headerBoardMainBinding.textViewHeaderBoardMainNickName.text = "${mainActivity.loginUserClass.userNickname}님"
                addHeaderView(headerBoardMainBinding.root)

                // 항목 선택시 동작하는 리스너
                setNavigationItemSelectedListener {
                    // 누른 메뉴를 체크상태로 둔다.
                    // it.isChecked = true

                    // 사용자가 누르 메뉴의 id로 분기한다.
                    when (it.itemId) {
                        // 전체 게시판
                        R.id.item_board_main_all -> {
                            toolbarMain.title = "전체 게시판"
                            nowPostType = 0
                            val newBundle = Bundle()
                            newBundle.putLong("postType", 0)
                            replaceFragment(POST_LIST_FRAGMENT, false, false, newBundle)
                            drawerLayoutBoardMain.close()
                        }
                        // 자유 게시판
                        R.id.item_board_main_free -> {
                            toolbarMain.title = "자유게시판"
                            nowPostType = 1
                            val newBundle = Bundle()
                            newBundle.putLong("postType", 1)
                            replaceFragment(POST_LIST_FRAGMENT, false, false, newBundle)
                            drawerLayoutBoardMain.close()
                        }
                        // 유머 게시판
                        R.id.item_board_main_gag -> {
                            toolbarMain.title = "유머게시판"
                            nowPostType = 2
                            val newBundle = Bundle()
                            newBundle.putLong("postType", 2)
                            replaceFragment(POST_LIST_FRAGMENT, false, false, newBundle)
                            drawerLayoutBoardMain.close()
                        }
                        // 질문 게시판
                        R.id.item_board_main_qna -> {
                            toolbarMain.title = "질문게시판"
                            nowPostType = 2
                            val newBundle = Bundle()
                            newBundle.putLong("postType", 3)
                            replaceFragment(POST_LIST_FRAGMENT, false, false, newBundle)
                            drawerLayoutBoardMain.close()
                        }
                        // 스포츠 게시판
                        R.id.item_board_main_sports -> {
                            toolbarMain.title = "스포츠게시판"
                            nowPostType = 4
                            val newBundle = Bundle()
                            newBundle.putLong("postType", 4)
                            replaceFragment(POST_LIST_FRAGMENT, false, false, newBundle)
                            drawerLayoutBoardMain.close()
                        }
                        // 사용자 정보 수정
                        R.id.item_board_main_user_info -> {
                            toolbarMain.title = "사용자 정보 수정"
                            replaceFragment(MODIFY_USER_FRAGMENT, false, false, null)
                            drawerLayoutBoardMain.close()
                        }
                        // 로그아웃
                        R.id.item_board_main_logout -> {
                            mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT, false, null)
                        }
                        // 회원탈퇴
                        R.id.item_board_main_sign_out -> {
                            mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT, false, null)
                        }
                    }
                    false
                }
            }

            // 첫 화면이 나오도록 한다.
            val newBundle = Bundle()
            newBundle.putLong("postType", nowPostType.toLong())
            replaceFragment(POST_LIST_FRAGMENT, false, false, newBundle)
        }

        return fragmentBoardMainBinding.root
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name: String, addToBackStack: Boolean, animate: Boolean, bundle: Bundle?) {

        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()

        // newFragment 에 Fragment가 들어있으면 oldFragment에 넣어준다.
        if (newFragment != null) {
            oldFragment = newFragment
        }

        // 새로운 Fragment를 담을 변수
        newFragment = when (name) {
            POST_LIST_FRAGMENT -> PostListFragment()
            MODIFY_USER_FRAGMENT -> ModifyUserFragment()
            else -> Fragment()
        }

        newFragment?.arguments = bundle

        if (newFragment != null) {
            // oldFragment -> newFragment로 이동
            // oldFramgent : exit
            // newFragment : enter

            // oldFragment <- newFragment 로 되돌아가기
            // oldFragment : reenter
            // newFragment : return

            if (animate == true) {
                // 애니메이션 설정
                if (oldFragment != null) {
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
            } else {
                // 애니메이션 설정
                if (oldFragment != null) {
                    oldFragment?.exitTransition = null
                    oldFragment?.reenterTransition = null
                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
                newFragment?.enterTransition = null
                newFragment?.returnTransition = null
            }

            // Fragment를 교채한다.
            fragmentTransaction.replace(R.id.boardMainContainer, newFragment!!)

            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name: String) {
        mainActivity.supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}