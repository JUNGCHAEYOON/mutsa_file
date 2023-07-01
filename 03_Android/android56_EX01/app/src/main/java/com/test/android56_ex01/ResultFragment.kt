package com.test.android56_ex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android56_ex01.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentResultBinding.run{
            buttonResultToMain.setOnClickListener {
                mainActivity.removeFragment(FragmentName.FRAGMENT_RESULT)
            }

            buttonResultRemove.setOnClickListener {
                mainActivity.animalList.removeAt(mainActivity.rowPosition)
                mainActivity.removeFragment(FragmentName.FRAGMENT_RESULT)
            }

            // 현재 항목 번째의 데이터를 추출한다.
            val (type, name, age, weight) = mainActivity.animalList[mainActivity.rowPosition]
            textViewResultType.text = "종류 : $type"
            textViewResultName.text = "이름 : $name"
            textViewResultAge.text = "나이 : $age"
            textViewResultWeight.text = "몸무게 : $weight"


        }

        return fragmentResultBinding.root
    }

}