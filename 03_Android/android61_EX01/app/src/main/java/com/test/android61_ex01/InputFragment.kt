package com.test.android61_ex01

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android61_ex01.databinding.FragmentInputBinding


class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentInputBinding.run{
            toolbarInput.run{
                title = "정보 입력"
                setTitleTextColor(Color.WHITE)
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                    navigationIcon?.colorFilter = BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                }
            }
        }

        return fragmentInputBinding.root
    }

}