package com.test.android_homework1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_homework1.databinding.FragmentShowBinding


class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity

    lateinit var info : Info

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        // 선택한 info 동기화
        info = InfoDAO.selectInfo(mainActivity,MainActivity.chosenIdx)

        fragmentShowBinding.run{
            tbShow.run{
                title = "사용자 정보 보기"
                // 백버튼
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener{
                    mainActivity.replaceFragment(MainActivity.MF,false,true)
                }
            }

            tvShowName.setText("이름 : ${info.name}")
            tvShowAge.setText("나이 : ${info.age.toString()} 세")
            tvShowKorean.setText("국어점수 : ${info.korean} 점")
        }

        return fragmentShowBinding.root
    }

}