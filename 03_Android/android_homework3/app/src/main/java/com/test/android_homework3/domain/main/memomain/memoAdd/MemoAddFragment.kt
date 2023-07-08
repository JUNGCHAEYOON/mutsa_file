package com.test.android_homework3.domain.main.memomain.memoAdd

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_homework3.MainActivity
import com.test.android_homework3.Memo
import com.test.android_homework3.R
import com.test.android_homework3.database.category.MemoDAO
import com.test.android_homework3.databinding.FragmentMemoAddBinding


class MemoAddFragment : Fragment() {

    lateinit var fragmentMemoAddBinding: FragmentMemoAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMemoAddBinding = FragmentMemoAddBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentMemoAddBinding.run{
            tbMemoAdd.run{
                title = "메모 추가"
                inflateMenu(R.menu.menu_memo_add)

                // 저장버튼
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.item_memo_add_add->{
                            val memoTitle = etMemoAddMemoTitle.text.toString()
                            val memoContent = etMemoAddMemoContent.text.toString()
                            val cidx = MainActivity.categoryIdx

                            val memo = Memo(0,cidx,memoTitle,memoContent)
                            MemoDAO.insertData(mainActivity,memo)
                        }
                    }
                    // memomain 으로 돌아가기
                    mainActivity.replaceFragment(MainActivity.MEMO_MAIN_FRAGMENT,true,true)
                    false
                }


                // 백버튼
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.replaceFragment(MainActivity.MAIN_FRAGMENT,true,true)
                }
            }

            etMemoAddMemoTitle.requestFocus()
        }

        return fragmentMemoAddBinding.root
    }

}