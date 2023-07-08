package com.test.android_homework3.domain.main.memomain

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android_homework3.Category
import com.test.android_homework3.MainActivity
import com.test.android_homework3.Memo
import com.test.android_homework3.R
import com.test.android_homework3.database.category.CategoryDAO
import com.test.android_homework3.database.category.MemoDAO
import com.test.android_homework3.databinding.FragmentMemoMainBinding
import com.test.android_homework3.databinding.RowMainBinding
import com.test.android_homework3.databinding.RowMemoMainBinding
import com.test.android_homework3.domain.main.MainFragment
import com.test.android_homework3.domain.main.memomain.memoShow.MemoShowFragment

class MemoMainFragment : Fragment() {

    lateinit var fragmentMemoMainBinding: FragmentMemoMainBinding
    lateinit var mainActivity: MainActivity

    lateinit var categoryName : String
    var cidx : Int = -1
    var memoList = mutableListOf<Memo>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMemoMainBinding = FragmentMemoMainBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        // 현재 선택된 카테고리의 이름과 idx 저장, 메모리스트 동기화
        var chosenCategory = CategoryDAO.selectCategory(mainActivity,MainActivity.categoryIdx)
        categoryName = chosenCategory.categoryName
        cidx = chosenCategory.idx
        memoList = MemoDAO.selectAllMemo(mainActivity,cidx)
        memoList.reverse()

        fragmentMemoMainBinding.run{
            tbMemoMain.run{
                title = categoryName

                inflateMenu(R.menu.menu_memo_main)
                setOnMenuItemClickListener {
                    // 메모 추가하기 버튼
                    mainActivity.replaceFragment(MainActivity.MEMO_ADD_FRAGMENT,true,true)
                    false
                }

                // 백버튼
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.replaceFragment(MainActivity.MAIN_FRAGMENT,false,true)
                }
            }

            rvMemoMain.run{
                adapter = MemoMainRecyclerViewAdapter()
                layoutManager = LinearLayoutManager(mainActivity)
            }
        }

        return fragmentMemoMainBinding.root
    }

    inner class MemoMainRecyclerViewAdapter : RecyclerView.Adapter<MemoMainRecyclerViewAdapter.MemoMainViewHolderClass>(){
        inner class MemoMainViewHolderClass(rowMemoMainBinding: RowMemoMainBinding) : RecyclerView.ViewHolder(rowMemoMainBinding.root){
            val tvRowMemoMain : TextView
            val llRowMemoMain : LinearLayout
            init{
                tvRowMemoMain = rowMemoMainBinding.tvRowMemoMain
                llRowMemoMain = rowMemoMainBinding.llRowMemoMain
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoMainViewHolderClass {
            val rowMemoMainBinding = RowMemoMainBinding.inflate(layoutInflater)
            val memoMainViewHolderClass = MemoMainViewHolderClass(rowMemoMainBinding)

            rowMemoMainBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return memoMainViewHolderClass
        }

        override fun getItemCount(): Int {
            return memoList.size
        }

        override fun onBindViewHolder(holder: MemoMainViewHolderClass, position: Int) {
            holder.tvRowMemoMain.text = memoList[position].memoTitle
            
            // 메모 터치시 memoShow 로 넘어감
            holder.llRowMemoMain.setOnClickListener {
                MainActivity.memoIdx = memoList[position].idx
                mainActivity.replaceFragment(MainActivity.MEMO_SHOW_FRAGMENT,true,true)
            }
        }
    }
}