package com.test.mini02_boardproject01

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.test.mini02_boardproject01.databinding.FragmentModifyUserBinding
import com.test.mini02_boardproject01.repository.UserRepository
import com.test.mini02_boardproject01.vm.UserViewModel

class ModifyUserFragment : Fragment() {
    lateinit var fragmentModifyUserBinding: FragmentModifyUserBinding
    lateinit var mainActivity: MainActivity

    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentModifyUserBinding = FragmentModifyUserBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        userViewModel = ViewModelProvider(mainActivity)[UserViewModel::class.java]

        fragmentModifyUserBinding.run {
            // 수정 완료 버튼
            buttonModifyUserAccept.run {
                setOnClickListener {
                    // 입력한 내용을 가져온다.
                    val modifyUserPw1 = textInputEditTextModifyUserPw.text.toString()
                    val modifyUserPw2 = textInputEditTextModifyUserPw2.text.toString()
                    val modifyUserNickName = textInputEditTextModifyUserNickName.text.toString()
                    val modifyUserAge = textInputEditTextModifyUserAge.text.toString()
                    val modifyHobby1 = materialCheckBoxModifyUserHobby1.isChecked
                    val modifyHobby2 = materialCheckBoxModifyUserHobby2.isChecked
                    val modifyHobby3 = materialCheckBoxModifyUserHobby3.isChecked
                    val modifyHobby4 = materialCheckBoxModifyUserHobby4.isChecked
                    val modifyHobby5 = materialCheckBoxModifyUserHobby5.isChecked
                    val modifyHobby6 = materialCheckBoxModifyUserHobby6.isChecked

                    if (modifyUserPw1.isNotEmpty() || modifyUserPw2.isNotEmpty()) {
                        if (modifyUserPw1 != modifyUserPw2) {
                            val builder = MaterialAlertDialogBuilder(mainActivity)
                            builder.setTitle("비밀번호 오류")
                            builder.setMessage("비밀번호가 다릅니다")
                            builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                                textInputEditTextModifyUserPw.setText("")
                                textInputEditTextModifyUserPw2.setText("")
                                mainActivity.showSoftInput(textInputEditTextModifyUserPw)
                            }
                            builder.show()
                            return@setOnClickListener
                        }
                    }

                    if (modifyUserNickName.isEmpty()) {
                        val builder = MaterialAlertDialogBuilder(mainActivity)
                        builder.setTitle("닉네임 입력 오류")
                        builder.setMessage("닉네임을 입력해주세요")
                        builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                            mainActivity.showSoftInput(textInputEditTextModifyUserNickName)
                        }
                        builder.show()
                        return@setOnClickListener
                    }

                    if (modifyUserAge.isEmpty()) {
                        val builder = MaterialAlertDialogBuilder(mainActivity)
                        builder.setTitle("나이 입력 오류")
                        builder.setMessage("나이를 입력해주세요")
                        builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                            mainActivity.showSoftInput(textInputEditTextModifyUserAge)
                        }
                        builder.show()
                        return@setOnClickListener
                    }

                    // 입력한 내용들을 담아준다.
                    if (modifyUserPw1.isNotEmpty() && modifyUserPw2.isNotEmpty()) {
                        if (modifyUserPw1 == modifyUserPw2) {
                            mainActivity.loginUserClass.userPw = modifyUserPw1
                        }
                    }

                    mainActivity.loginUserClass.userNickname = modifyUserNickName
                    mainActivity.loginUserClass.userAge = modifyUserAge.toLong()
                    mainActivity.loginUserClass.hobby1 = modifyHobby1
                    mainActivity.loginUserClass.hobby2 = modifyHobby2
                    mainActivity.loginUserClass.hobby3 = modifyHobby3
                    mainActivity.loginUserClass.hobby4 = modifyHobby4
                    mainActivity.loginUserClass.hobby5 = modifyHobby5
                    mainActivity.loginUserClass.hobby6 = modifyHobby6

                    // 저장한다.
                    UserRepository.modifyUserInfo(mainActivity.loginUserClass) {
                        Snackbar.make(fragmentModifyUserBinding.root, "수정되었습니다", Snackbar.LENGTH_SHORT).show()
                    }
                }
            }

            // 취미 전체 체크박스
            // 취미 모두 체크박스
            materialCheckBoxModifyUserAll.run {
                setOnCheckedChangeListener { compoundButton, b ->
                    userViewModel.run {
                        hobby1.value = b
                        hobby2.value = b
                        hobby3.value = b
                        hobby4.value = b
                        hobby5.value = b
                        hobby6.value = b
                    }
                }
            }

            // 취미 체크박스들 이벤트 설정
            for (a1 in materialCheckBoxGroupModifyUser1.children) {
                a1 as MaterialCheckBox
                a1.setOnCheckedChangeListener { compoundButton, b ->
                    setParentCheckBoxState()
                }
            }

            for (a1 in materialCheckBoxGroupModifyUser2.children) {
                a1 as MaterialCheckBox
                a1.setOnCheckedChangeListener { compoundButton, b ->
                    setParentCheckBoxState()
                }
            }

            userViewModel.run {
                userNickname.observe(mainActivity) {
                    fragmentModifyUserBinding.textInputEditTextModifyUserNickName.setText(it)
                }
                userAge.observe(mainActivity) {
                    fragmentModifyUserBinding.textInputEditTextModifyUserAge.setText(it.toString())
                }
                hobby1.observe(mainActivity) {
                    fragmentModifyUserBinding.materialCheckBoxModifyUserHobby1.isChecked = it
                }
                hobby2.observe(mainActivity) {
                    fragmentModifyUserBinding.materialCheckBoxModifyUserHobby2.isChecked = it
                }
                hobby3.observe(mainActivity) {
                    fragmentModifyUserBinding.materialCheckBoxModifyUserHobby3.isChecked = it
                }
                hobby4.observe(mainActivity) {
                    fragmentModifyUserBinding.materialCheckBoxModifyUserHobby4.isChecked = it
                }
                hobby5.observe(mainActivity) {
                    fragmentModifyUserBinding.materialCheckBoxModifyUserHobby5.isChecked = it
                }
                hobby6.observe(mainActivity) {
                    fragmentModifyUserBinding.materialCheckBoxModifyUserHobby6.isChecked = it
                }
                hobbyAll.observe(mainActivity) {
                    fragmentModifyUserBinding.materialCheckBoxModifyUserAll.isChecked = it
                }

                // 로그인한 사용자의 정보를 담아준다.
                userNickname.value = mainActivity.loginUserClass.userNickname
                userAge.value = mainActivity.loginUserClass.userAge
                hobby1.value = mainActivity.loginUserClass.hobby1
                hobby2.value = mainActivity.loginUserClass.hobby2
                hobby3.value = mainActivity.loginUserClass.hobby3
                hobby4.value = mainActivity.loginUserClass.hobby4
                hobby5.value = mainActivity.loginUserClass.hobby5
                hobby6.value = mainActivity.loginUserClass.hobby6
            }
        }

        return fragmentModifyUserBinding.root
    }

    // 하위의 체크박스들의 상태를 보고 상위 체크 박스 상태를 셋팅한다
    fun setParentCheckBoxState() {
        fragmentModifyUserBinding.run {
            // 전체 체크박스의 개수를 구한다.
            val allCheckBoxCount = materialCheckBoxGroupModifyUser1.childCount + materialCheckBoxGroupModifyUser2.childCount
            // 체크되어 있는 체크박스의 개수
            var checkedCount = 0

            // 체크 되어 있는 체크박스의 개수를 구한다.
            for (v1 in materialCheckBoxGroupModifyUser1.children) {
                v1 as MaterialCheckBox
                if (v1.checkedState == MaterialCheckBox.STATE_CHECKED) {
                    checkedCount++
                }
            }
            for (v1 in materialCheckBoxGroupModifyUser2.children) {
                v1 as MaterialCheckBox
                if (v1.checkedState == MaterialCheckBox.STATE_CHECKED) {
                    checkedCount++
                }
            }

            // 만약 체크되어 있는 것이 없다면
            if (checkedCount == 0) {
                materialCheckBoxModifyUserAll.checkedState = MaterialCheckBox.STATE_UNCHECKED
            }
            // 모두 체크되어 있다면
            else if (checkedCount == allCheckBoxCount) {
                materialCheckBoxModifyUserAll.checkedState = MaterialCheckBox.STATE_CHECKED
            }
            // 일부만 체크되어 있다면
            else {
                materialCheckBoxModifyUserAll.checkedState = MaterialCheckBox.STATE_INDETERMINATE
            }
        }
    }
}