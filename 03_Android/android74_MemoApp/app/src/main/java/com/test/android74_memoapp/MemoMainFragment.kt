package com.test.android74_memoapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android74_memoapp.databinding.FragmentMemoMainBinding
import com.test.android74_memoapp.databinding.RowMainBinding


class MemoMainFragment : Fragment() {

    lateinit var fragmentMemoMainBinding: FragmentMemoMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMemoMainBinding = FragmentMemoMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentMemoMainBinding.run{
            toolbarMemoMain.run{
                title = "카테고리 이름"
                inflateMenu(R.menu.category_main_menu)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.categoryMainItem1 -> {
                            mainActivity.replaceFragment(MainActivity.MEMO_ADD_FRAGMENT, true, true)
                        }
                    }
                    false
                }
                // 백버튼
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.MEMO_MAIN_FRAGMENT)
                }
            }

            recyclerViewMemoMain.run{
                adapter = MemoMainRecyclerAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL))
            }
        }

        return fragmentMemoMainBinding.root
    }

    inner class MemoMainRecyclerAdapter : RecyclerView.Adapter<MemoMainRecyclerAdapter.MemoMainViewHolder>(){

        inner class MemoMainViewHolder(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            var textViewRow:TextView

            init {
                textViewRow = rowMainBinding.textViewRow

                rowMainBinding.root.setOnClickListener {
                    mainActivity.replaceFragment(MainActivity.MEMO_READ_FRAGMENT, true, true)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoMainViewHolder {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val memoMainViewHolder = MemoMainViewHolder(rowMainBinding)

            rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return memoMainViewHolder
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: MemoMainViewHolder, position: Int) {
            holder.textViewRow.text = "메모 : $position"
        }
    }
}












