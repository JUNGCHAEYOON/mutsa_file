package com.test.android69_sqlitedatabase1_practice.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.test.android69_sqlitedatabase1_practice.DAO
import com.test.android69_sqlitedatabase1_practice.MainActivity
import com.test.android69_sqlitedatabase1_practice.R
import com.test.android69_sqlitedatabase1_practice.UserInfo
import com.test.android69_sqlitedatabase1_practice.databinding.FragmentAddBinding


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

        fragmentAddBinding.run{
            tbAdd.run{
                title = "AddFragment"
                // 백 버튼 아이콘을 표시한다.
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                // 백 버튼을 누르면 동작하는 리스너
                setNavigationOnClickListener {
                    mainActivity.replaceFragment(MainActivity.MAIN_FRAGMENT,true,true)
                }
            }

            etAddKorean.setOnEditorActionListener { v, actionId, event ->
                val name = etAddName.text.toString()
                val age = etAddAge.text.toString().toInt()
                val korean = etAddKorean.text.toString().toInt()

                val userInfo = UserInfo(0,name, age, korean)
                DAO.insertData(mainActivity, userInfo)

                Toast.makeText(mainActivity, "저장완료", Toast.LENGTH_SHORT).show()

                etAddName.setText("")
                etAddAge.setText("")
                etAddKorean.setText("")

                false
            }
        }

        return fragmentAddBinding.root
    }
}