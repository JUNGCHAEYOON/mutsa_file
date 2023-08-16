package com.test.keepgardeningproject_customer

import android.os.Bundle
import android.preference.PreferenceActivity.Header
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.keepgardeningproject_customer.databinding.FragmentHomeBinding
import com.test.keepgardeningproject_customer.databinding.HeaderHomeBinding

class HomeFragment : Fragment() {

    lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentHomeBinding.run{
            // 툴바
            toolbarHome.run{

                setNavigationIcon(R.drawable.ic_menu_24px)
                setNavigationOnClickListener {
                    drawerHome.open()
                }
            }

            // 네비게이션
            navigationHome.run{
                val headerHomeBinding = HeaderHomeBinding.inflate(inflater)
                headerHomeBinding.textView.text = "유저님"
                addHeaderView(headerHomeBinding.root)

                setNavigationItemSelectedListener {
                    false
                }
            }

            // 바텀네비게이션

        }

        return fragmentHomeBinding.root
    }

}