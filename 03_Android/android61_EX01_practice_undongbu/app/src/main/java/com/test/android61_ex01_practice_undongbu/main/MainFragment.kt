package com.test.android61_ex01_practice_undongbu.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.test.android61_ex01_practice_undongbu.FragmentName
import com.test.android61_ex01_practice_undongbu.MainActivity
import com.test.android61_ex01_practice_undongbu.R
import com.test.android61_ex01_practice_undongbu.databinding.FragmentMainBinding
import com.test.android61_ex01_practice_undongbu.databinding.RowMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run{
            tbMainFrag.run{
                title = "학생정보"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.menu_main)

                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT,true,true)
                    false
                }
            }

            rvMainFrag.run{
                adapter
            }
        }

        return fragmentMainBinding.root
    }


    inner class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolderClass>() {
        inner class MainViewHolderClass(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderClass {
            TODO("Not yet implemented")
        }

        override fun getItemCount(): Int {
            TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            TODO("Not yet implemented")
        }
    }
}