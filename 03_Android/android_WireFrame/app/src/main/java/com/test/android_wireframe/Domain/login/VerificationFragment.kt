package com.test.android_wireframe.Domain.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_wireframe.Domain.MainActivity
import com.test.android_wireframe.R
import com.test.android_wireframe.databinding.FragmentVerificationBinding

class VerificationFragment : Fragment() {

    lateinit var fragmentVerificationBinding: FragmentVerificationBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentVerificationBinding = FragmentVerificationBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentVerificationBinding.run{
            btnVeriVerify.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.MAIN, false,true)
            }
        }

        return fragmentVerificationBinding.root
    }

}