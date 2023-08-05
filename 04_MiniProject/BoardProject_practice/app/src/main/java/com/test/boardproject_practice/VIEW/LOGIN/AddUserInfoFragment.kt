package com.test.boardproject_practice.VIEW.LOGIN

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

            }

            // 체크박스
            mcbAuiAll.run{

            }

            // 버튼
            btnAuiSubmit.run{

            }
        }

        return fragmentAddUserInfoBinding.root
    }

}