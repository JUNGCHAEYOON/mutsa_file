package com.test.android74_memoapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.test.android74_memoapp.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding:FragmentLoginBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentLoginBinding.run{
            toolbarLogin.run{
                title = "로그인"
            }
            // 키보드를 올린다.
            mainActivity.showSoftInput(loginPw, 150)

            loginSubmitBtn.run{
                setOnClickListener {
                    clickLoginSubmitButton()
                }
            }

            loginPw.setOnEditorActionListener { textView, i, keyEvent ->
                clickLoginSubmitButton()
                false
            }
        }

        return fragmentLoginBinding.root
    }

    fun clickLoginSubmitButton(){
        fragmentLoginBinding.run{
            val str1 = loginPw.text.toString()

            if(str1.length == 0){
                val builder = AlertDialog.Builder(mainActivity)
                builder.setTitle("로그인 오류")
                builder.setMessage("비밀번호를 입력해주세요")
                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                    mainActivity.showSoftInput(loginPw, 150)
                }
                builder.show()
            }

            val builder = AlertDialog.Builder(mainActivity)
            builder.setTitle("로그인 성공")
            builder.setMessage("로그인에 성공하였습니다")
            builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                mainActivity.replaceFragment(MainActivity.CATEGORY_MAIN_FRAGMENT, false, true)
            }
            builder.show()
        }
    }

}