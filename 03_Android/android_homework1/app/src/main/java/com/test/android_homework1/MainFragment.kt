package com.test.android_homework1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android_homework1.databinding.FragmentMainBinding
import com.test.android_homework1.databinding.RowMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    var infoList = mutableListOf<Info>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        // infoList 동기화
        infoList = InfoDAO.selectAllData(mainActivity)

        fragmentMainBinding.run {
            tbMain.run {
                title = "메인화면"
                inflateMenu(R.menu.menu_main)
                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.AF, true, true)
                    false
                }
            }

            rvMain.run {
                adapter = MainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                // row 사이에 선긋기
                addItemDecoration(
                    DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL)
                )
            }
        }

        return fragmentMainBinding.root
    }

    inner class MainRecyclerViewAdapter :
        RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolderClass>() {
        inner class MainViewHolderClass(rowMainBinding: RowMainBinding) :
            RecyclerView.ViewHolder(rowMainBinding.root) {
            val tvRowMain: TextView
            val llRowMain: LinearLayout

            init {
                tvRowMain = rowMainBinding.tvRowMain
                llRowMain = rowMainBinding.llRowMain
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderClass {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val mainViewHolderClass = MainViewHolderClass(rowMainBinding)

            rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return mainViewHolderClass
        }

        override fun getItemCount(): Int {
            return infoList.size
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            holder.tvRowMain.text = infoList[position].name

            holder.llRowMain.setOnClickListener {
                MainActivity.chosenIdx = infoList[position].idx
                mainActivity.replaceFragment(MainActivity.SF, true, true)
            }
        }
    }
}