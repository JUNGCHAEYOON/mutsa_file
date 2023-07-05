package com.test.android69_sqlitedatabase1_practice.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android69_sqlitedatabase1_practice.DAO
import com.test.android69_sqlitedatabase1_practice.MainActivity
import com.test.android69_sqlitedatabase1_practice.R
import com.test.android69_sqlitedatabase1_practice.UserInfo
import com.test.android69_sqlitedatabase1_practice.databinding.FragmentMainBinding
import com.test.android69_sqlitedatabase1_practice.databinding.RowMainBinding


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    var userInfoList = mutableListOf<UserInfo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        
        // DAO 에서 데이터 불러오기
        run{
            userInfoList = DAO.selectAllData(mainActivity)
        }

        fragmentMainBinding.run{
            tbMain.run{
                title = "MainFragment"
                inflateMenu(R.menu.menu_main)
                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.ADD_FRAGMENT,true,true)
                    false
                }
            }

            rvMain.run{
                adapter = MainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(DividerItemDecoration(mainActivity,DividerItemDecoration.VERTICAL))
            }
        }

        return fragmentMainBinding.root
    }

    inner class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolderClass>(){
        inner class MainViewHolderClass(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            var tvRowMainName : TextView

            init{
                tvRowMainName = rowMainBinding.tvRowMainName

                rowMainBinding.root.setOnClickListener{

                }
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
            return userInfoList.size
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            holder.tvRowMainName.text = userInfoList[position].name
        }
    }
}