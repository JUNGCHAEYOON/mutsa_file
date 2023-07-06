package com.test.android_homework2_memoapp.domain.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.test.android_homework2_memoapp.MainActivity
import com.test.android_homework2_memoapp.Memo
import com.test.android_homework2_memoapp.R
import com.test.android_homework2_memoapp.database.DAO
import com.test.android_homework2_memoapp.databinding.FragmentMainBinding
import com.test.android_homework2_memoapp.databinding.RowMainBinding
import org.w3c.dom.Text

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // 리싸이클러에 들어갈 메모리스트
    var memoList = mutableListOf<Memo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run{
            tbMain.run{
                title = "메모앱 메인"
                inflateMenu(R.menu.menu_main)
                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.ADD_FRAGMENT,true,true)
                    false
                }
            }

            rvMain.run{
                // sql 로부터 memoList 불러오기
                memoList = DAO.selectAllData(mainActivity)

                // adapter 연결
                adapter = MainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                // row 사이에 선긋기
                addItemDecoration(DividerItemDecoration(mainActivity,DividerItemDecoration.VERTICAL))
            }
        }

        return fragmentMainBinding.root
    }

    // 리싸이클러 어댑터
    inner class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolderClass>(){
        inner class MainViewHolderClass(rowMainBinding : RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            var tvRowMainDate : TextView
            var tvRowMainTitle : TextView
            var llRowMain : LinearLayout

            init{
                tvRowMainDate = rowMainBinding.tvRowMainDate
                tvRowMainTitle = rowMainBinding.tvRowMainTitle
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
            return memoList.size
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            holder.tvRowMainDate.text = memoList[position].date
            holder.tvRowMainTitle.text = memoList[position].title

            holder.llRowMain.setOnClickListener {
                val idx = memoList[position].idx
                MainActivity.nowIdx = idx
                mainActivity.replaceFragment(MainActivity.SHOW_FRAGMENT,true,true)
            }
        }
    }
}