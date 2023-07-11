package com.test.android74_memoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android74_memoapp.databinding.FragmentMemoAddBinding
import com.test.android74_memoapp.databinding.FragmentMemoReadBinding


class MemoReadFragment : Fragment() {

    lateinit var fragmentMemoReadBinding: FragmentMemoReadBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMemoReadBinding = FragmentMemoReadBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMemoReadBinding.run{
            toolbarMemoRead.run{
                title = "메모 읽기"
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.MEMO_READ_FRAGMENT)
                }
            }
        }

        return fragmentMemoReadBinding.root
    }

}