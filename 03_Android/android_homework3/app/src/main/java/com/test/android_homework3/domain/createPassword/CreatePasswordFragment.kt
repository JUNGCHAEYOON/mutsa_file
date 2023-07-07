package com.test.android_homework3.domain.createPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.test.android_homework3.MainActivity
import com.test.android_homework3.Password
import com.test.android_homework3.R
import com.test.android_homework3.database.login.LoginDAO
import com.test.android_homework3.databinding.FragmentCreatePasswordBinding

class CreatePasswordFragment : Fragment() {

    lateinit var fragmentCreatePasswordBinding: FragmentCreatePasswordBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentCreatePasswordBinding = FragmentCreatePasswordBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentCreatePasswordBinding.run{
            tbCp.run{
                title = "비밀번호 설정"
            }
            // 설정완료 버튼
            btnCpFinish.setOnClickListener {
                // 둘다 공백이 아닐경우 and 둘이 일치할경우
                val pw1 = etCpPassword.text.toString()
                val pw2 = etCpPasswordCheck.text.toString()
                if(pw1 != "" && pw2 != "" && pw1 == pw2){
                    val newPassword = Password(0, pw1)
                    LoginDAO.insertData(mainActivity, newPassword)
                    mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT,true,true)
                }else{
                    Toast.makeText(mainActivity, "다시 입력해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return fragmentCreatePasswordBinding.root
    }
}