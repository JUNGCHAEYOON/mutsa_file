package com.test.android_wireframe.Domain.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_wireframe.Domain.MainActivity
import com.test.android_wireframe.R
import com.test.android_wireframe.databinding.FragmentLoginBinding

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
            //  Login -> CreateAccount -> Login
            //  Login -> Verification -> main

            // 로그인 버튼
            btnLoginLoginin.setOnClickListener {
                tilLoginEmail.visibility = View.VISIBLE
                tilLoginPassword.visibility = View.VISIBLE

                if(tilLoginEmail.visibility == View.VISIBLE){
                    mainActivity.replaceFragment(MainActivity.VERIFICATION,false,true)
                }
            }

            // 새 계정 만들기 버튼
            btnLoginSignup.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.CREATE_ACCOUNT,true, true)
            }
        }

        return fragmentLoginBinding.root
    }

}