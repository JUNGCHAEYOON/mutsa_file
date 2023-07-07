package com.test.android_homework3.domain.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.test.android_homework3.MainActivity
import com.test.android_homework3.R
import com.test.android_homework3.database.login.LoginDAO
import com.test.android_homework3.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentLoginBinding = FragmentLoginBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentLoginBinding.run{
            tbLogin.run{
                title = "로그인"
            }

            btnLogin.setOnClickListener {
                val inputPassword = etLoginPassword.text.toString()
                val originalPassword = LoginDAO.selectData(mainActivity).password

                // 비밀번호 검증
                if(inputPassword == originalPassword){
                    mainActivity.replaceFragment(MainActivity.MAIN_FRAGMENT,true,true)
                }else{
                    Toast.makeText(mainActivity, "비밀번호가 일치하지 않습니다. 다시 입력 해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return fragmentLoginBinding.root
    }

}