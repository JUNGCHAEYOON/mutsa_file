package com.test.boardproject_practice.VIEW.MAIN

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HeaderViewListAdapter
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import com.test.boardproject_practice.MainActivity
import com.test.boardproject_practice.R
import com.test.boardproject_practice.VIEW.POST.PostListFragment
import com.test.boardproject_practice.databinding.FragmentBoardMainBinding
import com.test.boardproject_practice.databinding.HeaderBoardMainBinding


class BoardMainFragment : Fragment() {

    lateinit var fragmentBoardMainBinding: FragmentBoardMainBinding
    lateinit var mainActivity: MainActivity

    var newFragment : Fragment? = null
    var oldFragment : Fragment? = null

    companion object{
        val POST_LIST = "PostListFragment"
        val MODIFY_USER = "ModifyUserFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentBoardMainBinding = FragmentBoardMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentBoardMainBinding.run{
            tbBm.run{
                title = "전체 게시판"
                setNavigationIcon(R.drawable.ic_menu_24px)
                setNavigationOnClickListener {
                    dlBm.open()
                }
            }

            nvBm.run{
                // 헤더설정 내장함수
                val headerBoardMainBinding = HeaderBoardMainBinding.inflate(layoutInflater)
                headerBoardMainBinding.textViewHeaderBoardMainNickName.text = "홍길동님"
                addHeaderView(headerBoardMainBinding.root)

                // 항목 선택시 동작 리스너
                setNavigationItemSelectedListener {
                    when(it.itemId){

                        // 전체 게시판
                        R.id.item_board_main_all -> {
                            tbBm.title = "전체게시판"
                            val newBundle = Bundle()
                            newBundle.putLong("postType", 0)
                            replaceFragment(POST_LIST, false, false, newBundle)
                            dlBm.close()
                        }
                        // 자유 게시판
                        R.id.item_board_main_free -> {
                            tbBm.title = "자유게시판"
                            val newBundle = Bundle()
                            newBundle.putLong("postType", 1)
                            replaceFragment(POST_LIST, false, false, newBundle)
                            dlBm.close()
                        }
                        // 유머 게시판
                        R.id.item_board_main_gag -> {
                            tbBm.title = "유머게시판"
                            val newBundle = Bundle()
                            newBundle.putLong("postType", 2)
                            replaceFragment(POST_LIST, false, false, newBundle)
                            dlBm.close()
                        }
                        // 질문 게시판
                        R.id.item_board_main_qna -> {
                            tbBm.title = "질문게시판"
                            val newBundle = Bundle()
                            newBundle.putLong("postType", 3)
                            replaceFragment(POST_LIST, false, false, newBundle)
                            dlBm.close()
                        }
                        // 스포츠 게시판
                        R.id.item_board_main_sports -> {
                            tbBm.title = "스포츠게시판"
                            val newBundle = Bundle()
                            newBundle.putLong("postType", 4)
                            replaceFragment(POST_LIST, false, false, newBundle)
                            dlBm.close()
                        }
                        // 사용자 정보 수정
                        R.id.item_board_main_user_info -> {
                            replaceFragment(MODIFY_USER, false, false, null)
                            dlBm.close()
                        }
                        // 로그아웃
                        R.id.item_board_main_logout -> {
                            mainActivity.replaceFragment(MainActivity.LOGIN, false, null)
                        }
                        // 회원탈퇴
                        R.id.item_board_main_sign_out -> {
                            mainActivity.replaceFragment(MainActivity.LOGIN, false, null)
                        }
                    }
                    false
                }
            }
        }

        return fragmentBoardMainBinding.root
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:String, addToBackStack:Boolean, animate:Boolean, bundle:Bundle?){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()

        // newFragment 에 Fragment가 들어있으면 oldFragment에 넣어준다.
        if(newFragment != null){
            oldFragment = newFragment
        }

        // 새로운 Fragment를 담을 변수
        newFragment = when(name){
            POST_LIST-> PostListFragment()
            MODIFY_USER-> ModifyUserFragment()
            else -> Fragment()
        }

        newFragment?.arguments = bundle

        if(newFragment != null) {

            if(animate == true) {
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
            fragmentTransaction.replace(R.id.fcv_bm, newFragment!!)

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
        mainActivity.supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}