package com.test.android_wireframe.Domain.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_wireframe.Domain.MainActivity
import com.test.android_wireframe.R
import com.test.android_wireframe.databinding.FragmentCreateAccountBinding

class CreateAccountFragment : Fragment() {

    lateinit var fragmentCreateAccountBinding: FragmentCreateAccountBinding
    lateinit var mainActivity : MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentCreateAccountBinding = FragmentCreateAccountBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentCreateAccountBinding.run{
            btnCa.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.LOGIN,false,true)
            }
        }

        return fragmentCreateAccountBinding.root
    }

}