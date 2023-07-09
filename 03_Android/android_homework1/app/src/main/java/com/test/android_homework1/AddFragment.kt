package com.test.android_homework1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_homework1.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    lateinit var fragmentAddBinding: FragmentAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentAddBinding = FragmentAddBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentAddBinding.run {
            tbAdd.run{
                title = "새로운 정보 입력"
                inflateMenu(R.menu.menu_add)

                // 저장하기
                setOnMenuItemClickListener {
                    val name = etAddName.text.toString()
                    val age = etAddAge.text.toString().toInt()
                    val korean = etAddKorean.text.toString().toInt()

                    val info = Info(0, name, age, korean)
                    InfoDAO.insertInfo(mainActivity,info)

                    mainActivity.replaceFragment(MainActivity.MF,false,true)
                    false
                }

                // 백버튼
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener{
                    mainActivity.replaceFragment(MainActivity.MF,false,true)
                }
            }

            etAddName.requestFocus()
        }

        return fragmentAddBinding.root
    }
}