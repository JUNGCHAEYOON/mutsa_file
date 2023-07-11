package com.test.android74_memoapp

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android74_memoapp.databinding.CategoryMainInputDialogBinding
import com.test.android74_memoapp.databinding.FragmentCategoryMainBinding
import com.test.android74_memoapp.databinding.RowMainBinding


class CategoryMainFragment : Fragment() {

    lateinit var fragmentCategoryMainBinding: FragmentCategoryMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        fragmentCategoryMainBinding = FragmentCategoryMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentCategoryMainBinding.run{

            toolbarCategoryMain.run{
                title = "카테고리 목록"
                // 툴바의 메뉴 구성
                inflateMenu(R.menu.category_main_menu)
                // 툴바의 메뉴를 눌렀을 경우
                setOnMenuItemClickListener {

                    when(it.itemId){
                        R.id.categoryMainItem1 -> {
                            val builder = AlertDialog.Builder(mainActivity)
                            builder.setTitle("카테고리 이름 입력")
                            val categoryMainInputDialogBinding = CategoryMainInputDialogBinding.inflate(layoutInflater)
                            builder.setView(categoryMainInputDialogBinding.root)
                            builder.setPositiveButton("확인", null)
                            builder.setNegativeButton("취소", null)

                            builder.show()
                            mainActivity.showSoftInput(categoryMainInputDialogBinding.dialogInput, 500)
                        }
                    }

                    false
                }

            }

            categoryRecyclerView.run{
                adapter = CategoryMainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
                addItemDecoration(DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL))
            }
        }

        return fragmentCategoryMainBinding.root
    }

    inner class CategoryMainRecyclerViewAdapter : RecyclerView.Adapter<CategoryMainRecyclerViewAdapter.CategoryMainViewHolder>(){

        inner class CategoryMainViewHolder(rowMainBinding: RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){

            var textViewRow:TextView

            init {
                textViewRow = rowMainBinding.textViewRow

                rowMainBinding.root.setOnCreateContextMenuListener { contextMenu, view, contextMenuInfo ->
                    contextMenu.setHeaderTitle("카테고리 $adapterPosition")
                    mainActivity.menuInflater.inflate(R.menu.category_main_context_menu, contextMenu)
                }

                rowMainBinding.root.setOnClickListener {
                    mainActivity.replaceFragment(MainActivity.MEMO_MAIN_FRAGMENT, true, true)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryMainViewHolder {
            val rowMainBinding = RowMainBinding.inflate(layoutInflater)
            val categoryMainViewHolder = CategoryMainViewHolder(rowMainBinding)

            rowMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return categoryMainViewHolder
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: CategoryMainViewHolder, position: Int) {
            holder.textViewRow.text = "카테고리 $position"
        }
    }
}











