package com.test.naversearchstudy.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.naversearchstudy.MainActivity
import com.test.naversearchstudy.R
import com.test.naversearchstudy.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentShowBinding.run{
            tbShow.run{
                title = "기사 보기"
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener{
                    mainActivity.replaceFragment(MainActivity.MAIN,false,false)
                }
            }

            tvShowTitle.text = MainActivity.newsTitle
            tvShowContent.text = MainActivity.newsContent
        }

        return fragmentShowBinding.root
    }
}