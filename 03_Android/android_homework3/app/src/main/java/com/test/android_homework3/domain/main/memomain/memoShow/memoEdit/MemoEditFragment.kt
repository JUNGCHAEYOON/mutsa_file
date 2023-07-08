package com.test.android_homework3.domain.main.memomain.memoShow.memoEdit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_homework3.MainActivity
import com.test.android_homework3.Memo
import com.test.android_homework3.R
import com.test.android_homework3.database.category.MemoDAO
import com.test.android_homework3.databinding.FragmentMemoEditBinding

class MemoEditFragment : Fragment() {

    lateinit var fragmentMemoEditBinding: FragmentMemoEditBinding
    lateinit var mainActivity: MainActivity

    lateinit var memo : Memo
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentMemoEditBinding = FragmentMemoEditBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        memo = MemoDAO.selectMemo(mainActivity, MainActivity.memoIdx)

        fragmentMemoEditBinding.run{
            tbMemoEdit.run{
                title = "메모 수정"
                inflateMenu(R.menu.menu_memo_edit)

                // 저장버튼
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.item_memo_edit_edit->{
                            val memoTitle = etMemoEditMemoTitle.text.toString()
                            val memoContent = etMemoEditMemoContent.text.toString()
                            val cidx = MainActivity.categoryIdx
                            val idx = MainActivity.memoIdx

                            val newMemo = Memo(idx, cidx, memoTitle, memoContent)
                            MemoDAO.updateMemo(mainActivity, newMemo)
                        }
                    }
                    // memoShow 로 돌아가기
                    mainActivity.replaceFragment(MainActivity.MEMO_SHOW_FRAGMENT,false,true)
                    false
                }

                // 백버튼
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.replaceFragment(MainActivity.MEMO_SHOW_FRAGMENT,false,true)
                }
            }

            // 수정할 etv 적용
            etMemoEditMemoTitle.setText(memo.memoTitle)
            etMemoEditMemoContent.setText(memo.memoContent)

            etMemoEditMemoTitle.requestFocus()
        }

        return fragmentMemoEditBinding.root
    }

}