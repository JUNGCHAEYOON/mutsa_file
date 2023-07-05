package com.test.android69_ex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android69_ex01.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentResultBinding = FragmentResultBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        // 파일로 부터 데이터를 가져온다.
        val studentCount = mainActivity.getStudentCount()
        val studentList = mainActivity.getStudentInfo(studentCount)

        // 선택한 행 번째의 객체에서 데이터를 가져와 출력한다.
        fragmentResultBinding.run{
            textViewResult1.text = studentList[mainActivity.rowPosition].name
            textViewResult2.text = studentList[mainActivity.rowPosition].age.toString()
            textViewResult3.text = studentList[mainActivity.rowPosition].korean.toString()
        }

        return fragmentResultBinding.root
    }

}