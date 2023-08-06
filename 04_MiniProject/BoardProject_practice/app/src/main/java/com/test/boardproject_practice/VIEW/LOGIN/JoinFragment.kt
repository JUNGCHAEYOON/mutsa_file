package com.test.boardproject_practice.VIEW.LOGIN

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.boardproject_practice.MainActivity
import com.test.boardproject_practice.R
import com.test.boardproject_practice.MODEL.VIEW_MODEL.UserViewModel
import com.test.boardproject_practice.databinding.FragmentJoinBinding


class JoinFragment : Fragment() {

    lateinit var fragmentJoinBinding: FragmentJoinBinding
    lateinit var mainActivity: MainActivity

    // 뷰모델 객체 생성
    lateinit var userViewModel : UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentJoinBinding = FragmentJoinBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        // 뷰모델 객체 초기화
        userViewModel = ViewModelProvider(mainActivity)[UserViewModel::class.java]
        userViewModel.run{
            userId.observe(mainActivity){
                fragmentJoinBinding.tietJoinId.setText(it)
            }
            userPw.observe(mainActivity){
                fragmentJoinBinding.tietJoinPw.setText(it)
            }
            userPw2.observe(mainActivity){
                fragmentJoinBinding.tietJoinPw2.setText(it)
            }
        }

        // 뷰바인딩
        fragmentJoinBinding.run{
            tbJoin.run{
                title = "회원가입"
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.JOIN)
                }
            }

            btnJoinNext.run{
                setOnClickListener {
                    next()
                }
            }

            tietJoinPw2.run{
                setOnEditorActionListener { v, actionId, event ->
                    next()
                    true
                }
            }
        }

        return fragmentJoinBinding.root
    }

    override fun onResume() {
        super.onResume()
        userViewModel.reset()
    }

    fun next(){
        fragmentJoinBinding.run{

            // 입력한 내용을 가져온다.
            val joinUserId = tietJoinId.text.toString()
            val joinUserPw = tietJoinPw.text.toString()
            val joinUserPw2 = tietJoinPw2.text.toString()

            if(joinUserId.isEmpty()){
                val builder = MaterialAlertDialogBuilder(mainActivity)
                builder.setTitle("로그인 오류")
                builder.setMessage("아이디를 입력해주세요")
                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                    mainActivity.showSoftInput(tietJoinId)
                }
                builder.show()
                return
            }

            if(joinUserPw.isEmpty()){
                val builder = MaterialAlertDialogBuilder(mainActivity)
                builder.setTitle("비빌번호 오류")
                builder.setMessage("비밀번호를 입력해주세요")
                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                    mainActivity.showSoftInput(tietJoinPw)
                }
                builder.show()
                return
            }

            if(joinUserPw2.isEmpty()){
                val builder = MaterialAlertDialogBuilder(mainActivity)
                builder.setTitle("비빌번호 오류")
                builder.setMessage("비밀번호를 입력해주세요")
                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                    mainActivity.showSoftInput(tietJoinPw2)
                }
                builder.show()
                return
            }

            if(joinUserPw != joinUserPw2){
                val builder = MaterialAlertDialogBuilder(mainActivity)
                builder.setTitle("비빌번호 오류")
                builder.setMessage("비밀번호가 일치하지 않습니다.")
                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                    tietJoinPw.setText("")
                    tietJoinPw2.setText("")
                    mainActivity.showSoftInput(tietJoinPw)
                }
                builder.show()
                return
            }

            val newBundle = Bundle()
            newBundle.putString("joinUserId", joinUserId)
            newBundle.putString("joinUserPw", joinUserPw)

            mainActivity.replaceFragment(MainActivity.ADD_USER_INFO, true, newBundle)
        }
    }

}