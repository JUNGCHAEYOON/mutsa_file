package com.test.android_wireframe.Domain.splash

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_wireframe.Domain.MainActivity
import com.test.android_wireframe.R
import com.test.android_wireframe.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    lateinit var fragmentSplashBinding: FragmentSplashBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentSplashBinding = FragmentSplashBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentSplashBinding.run{
            Handler(Looper.getMainLooper()).postDelayed({
                mainActivity.replaceFragment(MainActivity.LOGIN,false,true)
            }, 3000)
            
        }

        return fragmentSplashBinding.root
    }

}