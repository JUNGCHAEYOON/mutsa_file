package com.test.boardproject_practice.VIEW.LOGIN

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.test.boardproject_practice.MODEL.DATA.UserClass
import com.test.boardproject_practice.MODEL.REPOSITORY.UserRepository
import com.test.boardproject_practice.MainActivity
import com.test.boardproject_practice.VIEW_MODEL.UserViewModel
import com.test.boardproject_practice.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding : FragmentLoginBinding
    lateinit var mainActivity: MainActivity

    // 뷰모델 객체 생성
    lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        // 뷰모델 초기화
        userViewModel = ViewModelProvider(mainActivity)[UserViewModel::class.java]

        fragmentLoginBinding.run{
            // toolbar
            tbLogin.run{
                title = "로그인"
            }

            // 회원가입 버튼
            btnLoginJoin.run{
                setOnClickListener {
                    // JoinFragment로 이동하기
                    mainActivity.replaceFragment(MainActivity.JOIN,true,null)
                }
            }

            // 로그인버튼
            btnLoginLogin.run{
                setOnClickListener {
                    loginSubmit()
                }
            }

            // 비밀번호 입력후 엔터시 loginsubmit
            tietLoginPw.run{
                setOnEditorActionListener { v, actionId, event ->
                    loginSubmit()
                    false
                }
            }
        }

        return fragmentLoginBinding.root
    }

    override fun onResume() {
        super.onResume()

        userViewModel.reset()
    }

    fun loginSubmit() : Unit{
        fragmentLoginBinding.run{
            // 사용자가 입력한 내용을 가져온다.
            val loginUserId = tietLoginId.text.toString()
            val loginUserPw = tietLoginPw.text.toString()

            // 빈칸일때 다이얼로그
            checkEmptyDialog(loginUserId, "로그인 오류", "아이디를 입력해주세요", tietLoginId)
            checkEmptyDialog(loginUserPw, "비밀번호 오류", "비밀번호를 입력해주세요", tietLoginPw)

            // 빈칸아닐때
            UserRepository.getUserInfoByUserId(loginUserId){
                // 가져온 데이터가 없음
                if(it.result.exists() == false){
                    val builder = MaterialAlertDialogBuilder(mainActivity)
                    builder.setTitle("로그인 오류")
                    builder.setMessage("존재하지 않는 아이디 입니다")
                    builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                        tietLoginId.setText("")
                        tietLoginPw.setText("")
                        mainActivity.showSoftInput(tietLoginId)
                    }
                    builder.show()
                }
                // 가져온 데이터 있음
                else{
                    for(c1 in it.result.children){
                        val userPw = c1.child("userPw").value as String

                        // 비밀번호 다를때
                        if(loginUserPw != userPw){
                            val builder = MaterialAlertDialogBuilder(mainActivity)
                            builder.setTitle("로그인 오류")
                            builder.setMessage("잘못된 비밀번호 입니다")
                            builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                tietLoginPw.setText("")
                                mainActivity.showSoftInput(tietLoginPw)
                            }
                            builder.show()
                        }
                        // 비밀번호 같을때
                        else {

                            // 로그인한 사용자 정보를 가져온다.
                            val userIdx = c1.child("userIdx").value as Long
                            val userId = c1.child("userId").value as String
                            val userPw = c1.child("userPw").value as String
                            val userNickname = c1.child("userNickname").value as String
                            val userAge = c1.child("userAge").value as Long
                            val hobby1 = c1.child("hobby1").value as Boolean
                            val hobby2 = c1.child("hobby2").value as Boolean
                            val hobby3 = c1.child("hobby3").value as Boolean
                            val hobby4 = c1.child("hobby4").value as Boolean
                            val hobby5 = c1.child("hobby5").value as Boolean
                            val hobby6 = c1.child("hobby6").value as Boolean

                            mainActivity.loginUserClass = UserClass(userIdx, userId, userPw, userNickname, userAge, hobby1, hobby2, hobby3, hobby4, hobby5, hobby6)
                            Snackbar.make(fragmentLoginBinding.root, "로그인 되었습니다", Snackbar.LENGTH_SHORT).show()

                            mainActivity.replaceFragment(MainActivity.BOARD_MAIN, false, null)
                        }
                    }
                }
            }
        }
    }

    // empty check
    fun checkEmptyDialog(editString : String, title : String, message : String, view: View){
        // 빈칸일때 다이얼로그
        if(editString.isEmpty()){
            val builder = MaterialAlertDialogBuilder(mainActivity)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                mainActivity.showSoftInput(view)
            }
            builder.show()
            return
        }
    }
}