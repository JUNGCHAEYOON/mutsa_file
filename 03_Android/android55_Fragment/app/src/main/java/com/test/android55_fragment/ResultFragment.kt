package com.test.android55_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android55_fragment.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainActivity = activity as MainActivity
        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)

        fragmentResultBinding.run{
            // 데이터를 추출한다.
            val (name, age, korean) = mainActivity.studentList[mainActivity.rowPosition]

            textViewResultName.text = "이름 : $name"
            textViewResultAge.text = "나이 : $age"
            textViewResultKorean.text = "국어점수 : $korean"
        }

        return fragmentResultBinding.root
    }
}