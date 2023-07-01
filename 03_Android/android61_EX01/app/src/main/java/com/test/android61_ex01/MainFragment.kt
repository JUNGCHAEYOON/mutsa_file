package com.test.android61_ex01

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android61_ex01.databinding.FragmentMainBinding
import com.test.android61_ex01.databinding.MainRowBinding

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
            toolbarMain.run{
                title = "학생정보"
                setTitleTextColor(Color.WHITE)
                inflateMenu(R.menu.main_menu)
                setOnMenuItemClickListener {

                    mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT, true, true)

                    false
                }
            }

            recyclerViewMain.run{
                adapter = MainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL))
            }

        }

        return fragmentMainBinding.root
    }

    inner class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolderClass>(){

        inner class MainViewHolderClass(mainRowBinding: MainRowBinding) : RecyclerView.ViewHolder(mainRowBinding.root){
            var textViewMainRow:TextView
            
            init{
                textViewMainRow = mainRowBinding.textViewMainRow
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderClass {
            val mainRowBinding = MainRowBinding.inflate(layoutInflater)
            val mainViewHolderClass = MainViewHolderClass(mainRowBinding)
            
            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            mainRowBinding.root.layoutParams = params
            
            return mainViewHolderClass
        }

        override fun getItemCount(): Int {
            return 10
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            holder.textViewMainRow.text = "홍길동"
        }
    }

}