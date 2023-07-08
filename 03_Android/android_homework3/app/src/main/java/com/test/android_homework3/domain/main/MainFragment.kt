package com.test.android_homework3.domain.main

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android_homework3.Category
import com.test.android_homework3.MainActivity
import com.test.android_homework3.Memo
import com.test.android_homework3.R
import com.test.android_homework3.database.category.CategoryDAO
import com.test.android_homework3.databinding.DialogMainAddBinding
import com.test.android_homework3.databinding.DialogMainEditBinding
import com.test.android_homework3.databinding.FragmentMainBinding
import com.test.android_homework3.databinding.RowMainBinding


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    var categoryNameList = mutableListOf<Category>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        // DAO 에서 categoryNameList 불러오기, reverse 를 해주므로써 최신이 위로 오게
        categoryNameList = CategoryDAO.selectAllCategory(mainActivity)
        categoryNameList.reverse()

        fragmentMainBinding.run{
            tbMain.run{
                title = "카테고리 목록"
                inflateMenu(R.menu.menu_main)
                
                // 메뉴 add icon 클릭
                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 카테고리 추가 다이얼로그 실행
                        R.id.item_main_add->{
                            val dialogMainAddBinding = DialogMainAddBinding.inflate(layoutInflater)
                            val builder = AlertDialog.Builder(mainActivity)
                            builder.setTitle("새 카테고리 추가")
                            builder.setView(dialogMainAddBinding.root)

                            // 다이얼로그 버튼
                            builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                val categoryName = dialogMainAddBinding.etDialogMainCategoryName.text.toString()
                                CategoryDAO.insertData(mainActivity,categoryName)
                                categoryNameList = CategoryDAO.selectAllCategory(mainActivity)
                                categoryNameList.reverse()
                                rvMain.adapter?.notifyDataSetChanged()
                            }
                            builder.setNegativeButton("취소",null)
                            builder.show()
                        }
                    }
                    false
                }
            }
            
            rvMain.run{
                adapter = MainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }

        return fragmentMainBinding.root
    }

    inner class MainRecyclerViewAdapter : RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolderClass>(){
        inner class MainViewHolderClass(rowMainBinding : RowMainBinding) : RecyclerView.ViewHolder(rowMainBinding.root){
            val tvRowMain : TextView
            val llRowMain : LinearLayout

            init{
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
            return categoryNameList.size
        }

        override fun onBindViewHolder(holder: MainViewHolderClass, position: Int) {
            holder.tvRowMain.text = categoryNameList[position].categoryName

            // 길게 누르면 수정 삭제
            holder.llRowMain.setOnLongClickListener {
                // 컨텍스트 메뉴 등장
                val pop = PopupMenu(mainActivity,holder.llRowMain)
                mainActivity.menuInflater.inflate(R.menu.menu_main_rv, pop.menu)
                pop.show()
                pop.setOnMenuItemClickListener {
                    when(it.itemId){
                        // 수정 다이얼로그 띄우기
                        R.id.item_menu_main_rv_edit->{
                            val dialogMainEditBinding = DialogMainEditBinding.inflate(layoutInflater)
                            val builder = AlertDialog.Builder(mainActivity)
                            builder.setTitle("카테고리 이름 수정")
                            builder.setView(dialogMainEditBinding.root)

                            // 다이얼로그 버튼
                            builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                val categoryName = dialogMainEditBinding.etDialogMainCategoryEditName.text.toString()

                                val ml = mutableListOf<Memo>()
                                val category = Category(categoryNameList[position].idx, categoryName,ml)

                                CategoryDAO.updateCategoryName(mainActivity,category)
                                categoryNameList = CategoryDAO.selectAllCategory(mainActivity)
                                categoryNameList.reverse()
                                fragmentMainBinding.rvMain.adapter?.notifyDataSetChanged()
                            }
                            builder.setNegativeButton("취소",null)
                            builder.show()
                        }

                        // 해당 position 삭제
                        R.id.item_menu_main_rv_delete->{
                            CategoryDAO.deleteCategoryName(mainActivity,categoryNameList[position].idx)
                            categoryNameList = CategoryDAO.selectAllCategory(mainActivity)
                            categoryNameList.reverse()
                            fragmentMainBinding.rvMain.adapter?.notifyDataSetChanged()
                        }
                    }
                    false
                }

                false
            }

            // 짧게 누르면 memo 보기
            holder.llRowMain.setOnClickListener {
                MainActivity.categoryIdx = categoryNameList[position].idx
                mainActivity.replaceFragment(MainActivity.MEMO_MAIN_FRAGMENT,true,true)
            }
        }
    }
}