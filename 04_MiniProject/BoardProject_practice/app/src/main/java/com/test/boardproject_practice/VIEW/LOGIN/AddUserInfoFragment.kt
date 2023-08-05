package com.test.boardproject_practice.VIEW.LOGIN

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.test.boardproject_practice.MODEL.DATA.UserClass
import com.test.boardproject_practice.MODEL.REPOSITORY.UserRepository
import com.test.boardproject_practice.MainActivity
import com.test.boardproject_practice.R
import com.test.boardproject_practice.databinding.FragmentAddUserInfoBinding


class AddUserInfoFragment : Fragment() {

    lateinit var fragmentAddUserInfoBinding: FragmentAddUserInfoBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentAddUserInfoBinding = FragmentAddUserInfoBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentAddUserInfoBinding.run{
            // 툴바
            tbAui.run{
                title = "회원가입"
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.ADD_USER_INFO)
                }
            }

            // 체크박스
            mcbAuiAll.run{
                setOnCheckedChangeListener { buttonView, isChecked ->
                    for(v1 in llAuiMcbGroup1.children){
                        v1 as MaterialCheckBox
                        if(isChecked){
                            v1.checkedState = MaterialCheckBox.STATE_CHECKED
                        }else{
                            v1.checkedState = MaterialCheckBox.STATE_UNCHECKED
                        }
                    }

                    for(v1 in llAuiMcbGroup2.children){
                        v1 as MaterialCheckBox
                        if(isChecked){
                            v1.checkedState = MaterialCheckBox.STATE_CHECKED
                        }else{
                            v1.checkedState = MaterialCheckBox.STATE_UNCHECKED
                        }
                    }
                }
            }
            
            // 다른 체크박스
            run{
                for(v1 in llAuiMcbGroup1.children){
                    v1 as MaterialCheckBox
                    v1.setOnCheckedChangeListener { buttonView, isChecked ->
                        setParentCheckBoxState()
                    }
                }
                for(v1 in llAuiMcbGroup2.children){
                    v1 as MaterialCheckBox
                    v1.setOnCheckedChangeListener { buttonView, isChecked ->
                        setParentCheckBoxState()
                    }
                }
            }

            // 버튼
            btnAuiSubmit.run{
                setOnClickListener {
                    val joinUserNickName = tietAuiNickname.text.toString()
                    val joinUserAge = tietAuiAge.text.toString()

                    if(joinUserNickName.isEmpty()){
                        val builder = MaterialAlertDialogBuilder(mainActivity)
                        builder.setTitle("닉네임 입력 오류")
                        builder.setMessage("닉네임을 입력해주세요")
                        builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                            mainActivity.showSoftInput(tietAuiNickname)
                        }
                        builder.show()
                        return@setOnClickListener
                    }

                    if(joinUserAge.isEmpty()){
                        val builder = MaterialAlertDialogBuilder(mainActivity)
                        builder.setTitle("나이 입력 오류")
                        builder.setMessage("나이를 입력해주세요")
                        builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                            mainActivity.showSoftInput(tietAuiAge)
                        }
                        builder.show()
                        return@setOnClickListener
                    }

                    UserRepository.getUserIdx {
                        // 현재 사용자의 순서값을 가져온다.
                        var userIdx = it.result.value as Long

                        // 저장할 데이터들을 담는다.
                        val joinUserId = arguments?.getString("joinUserId")!!
                        val joinUserPw = arguments?.getString("joinUserPw")!!

                        // 사용자 인덱스 번호 1 증가
                        userIdx++

                        val userClass = UserClass(userIdx, joinUserId, joinUserPw,joinUserNickName,joinUserAge.toLong(),
                            mcbAuiHobby1.isChecked,
                            mcbAuiHobby2.isChecked,
                            mcbAuiHobby3.isChecked,
                            mcbAuiHobby4.isChecked,
                            mcbAuiHobby5.isChecked,
                            mcbAuiHobby6.isChecked)

                        UserRepository.addUserInfo(userClass){
                            UserRepository.setUserIdx(userIdx){
                                Snackbar.make(fragmentAddUserInfoBinding.root, "가입이 완료되었습니다.", Snackbar.LENGTH_SHORT).show()

                                mainActivity.removeFragment(MainActivity.ADD_USER_INFO)
                                mainActivity.removeFragment(MainActivity.JOIN)
                            }
                        }
                    }
                }
            }
            
            // 초기 포커스 키보드
            mainActivity.showSoftInput(tietAuiNickname)
        }

        return fragmentAddUserInfoBinding.root
    }

    fun setParentCheckBoxState() {
        fragmentAddUserInfoBinding.run{
            // 체크박스 개수
            val checkBoxCount = llAuiMcbGroup1.childCount + llAuiMcbGroup2.childCount
            // 체크된 박스의 개수
            var checkedCount = 0

            // 체크된 박스의 수 구하기
            for(v1 in llAuiMcbGroup1.children){
                v1 as MaterialCheckBox
                if(v1.isChecked) checkedCount++
            }
            for(v1 in llAuiMcbGroup2.children){
                v1 as MaterialCheckBox
                if(v1.isChecked) checkedCount++
            }

            // 체크된것이 없다면
            if(checkedCount == 0){
                mcbAuiAll.checkedState = MaterialCheckBox.STATE_UNCHECKED
            }
            // 모두체크
            else if(checkedCount == checkBoxCount){
                mcbAuiAll.checkedState = MaterialCheckBox.STATE_CHECKED
            }
            // 일부체크
            else{
                mcbAuiAll.checkedState = MaterialCheckBox.STATE_INDETERMINATE
            }

        }
    }

}