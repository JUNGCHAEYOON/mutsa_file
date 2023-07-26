package com.test.mini02_boardproject02

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.google.android.material.transition.MaterialSharedAxis
import com.test.mini02_boardproject02.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    companion object{
        val MODIFY = "ModifyUserFragment"
        val POSTLIST = "PostListFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run{
            replaceFragment(POSTLIST)

            toolbarMain.run{
                title = "메인화면"
                setNavigationIcon(R.drawable.ic_action_menu)
                setNavigationOnClickListener {
                    drawerLayout.run{
                        open()
                        setOnMenuItemClickListener {
                            when(it.itemId){
                                //카테고리별 클릭
                                R.id.item_main_all->{
                                    replaceFragment(POSTLIST)
                                }
                                R.id.item_main_free->{
                                    replaceFragment(POSTLIST)
                                }
                                R.id.item_main_humor->{
                                    replaceFragment(POSTLIST)
                                }
                                R.id.item_main_question->{
                                    replaceFragment(POSTLIST)
                                }
                                R.id.item_main_sport->{
                                    replaceFragment(POSTLIST)
                                }

                                // 사용자 정보 변경
                                R.id.item_main_userInfo->{
                                    replaceFragment(MODIFY)
                                }
                                // 로그아웃
                                R.id.item_main_logout->{
                                    mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT,false,null)
                                }
                                // 회원탈퇴
                                R.id.item_main_deleteAccount->{
                                    mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT,false,null)
                                }
                            }
                            false
                        }
                    }
                }
            }
        }

        return fragmentMainBinding.root
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name:String){
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = mainActivity.supportFragmentManager.beginTransaction()

        // 새로운 Fragment를 담을 변수
        var newFragment = when(name){
            MODIFY-> ModifyUserFragment()
            POSTLIST->PostListFragment()
            else -> Fragment()
        }

        if(newFragment != null) {
            fragmentTransaction.replace(R.id.fcv_main_frag, newFragment!!)
            fragmentTransaction.commit()
        }
    }
}