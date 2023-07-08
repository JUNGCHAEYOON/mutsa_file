package com.test.android_homework3.domain.main.memomain.memoShow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_homework3.MainActivity
import com.test.android_homework3.Memo
import com.test.android_homework3.R
import com.test.android_homework3.database.category.MemoDAO
import com.test.android_homework3.databinding.FragmentMemoShowBinding

class MemoShowFragment : Fragment() {

    lateinit var fragmentMemoShowBinding: FragmentMemoShowBinding
    lateinit var mainActivity: MainActivity

    lateinit var memo : Memo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMemoShowBinding = FragmentMemoShowBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        memo = MemoDAO.selectMemo(mainActivity, MainActivity.memoIdx)

        fragmentMemoShowBinding.run{
            tbMemoShow.run{
                title = "메모 보기"
                inflateMenu(R.menu.menu_memo_show)

                // 수정버튼, 삭제버튼
                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 수정버튼
                        R.id.item_memo_show_edit->{
                            mainActivity.replaceFragment(MainActivity.MEMO_EDIT_FRAGMENT,true,true)
                        }
                        // 삭제버튼
                        R.id.item_memo_show_delete->{
                            MemoDAO.deleteMemo(mainActivity,MainActivity.memoIdx)
                            mainActivity.replaceFragment(MainActivity.MEMO_MAIN_FRAGMENT,false,true)
                        }
                    }
                    false
                }

                // 백버튼
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.replaceFragment(MainActivity.MEMO_MAIN_FRAGMENT,false,true)
                }
            }
            
            // 메모 보여주기
            tvMemoShowMemoTitleTitle.text = memo.memoTitle
            tvMemoShowMemoContentContent.text = memo.memoContent
        }

        return fragmentMemoShowBinding.root
    }


}