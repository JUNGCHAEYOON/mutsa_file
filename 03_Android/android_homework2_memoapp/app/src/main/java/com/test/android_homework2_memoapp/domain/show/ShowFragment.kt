package com.test.android_homework2_memoapp.domain.show

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_homework2_memoapp.MainActivity
import com.test.android_homework2_memoapp.Memo
import com.test.android_homework2_memoapp.R
import com.test.android_homework2_memoapp.database.DAO
import com.test.android_homework2_memoapp.databinding.FragmentShowBinding

class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity

    lateinit var memo : Memo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentShowBinding = FragmentShowBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        memo = DAO.selectData(mainActivity,MainActivity.nowIdx)

        fragmentShowBinding.run{
            tbShow.run{
                title = "메모 읽기"
                inflateMenu(R.menu.menu_show)

                setOnMenuItemClickListener {
                    when(it.itemId){
                        // 수정버튼 -> 수정프래그먼트로 교체
                        R.id.item_show_modify->{
                            mainActivity.replaceFragment(MainActivity.MODIFY_FRAGMENT,true,true)
                        }
                        // 삭제버튼 -> 메인프래그먼트로 돌아감
                        R.id.item_show_delete->{
                            DAO.deleteData(mainActivity,memo.idx)
                            mainActivity.replaceFragment(MainActivity.MAIN_FRAGMENT,true,true)
                        }
                    }
                    false
                }

                // 뒤로가기
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.replaceFragment(MainActivity.MAIN_FRAGMENT,true,true)
                }
            }

            // 데이터 보여주기
            tvShowTitle.setText(memo.title)
            tvShowDate.setText(memo.date)
            tvShowContent.setText(memo.content)
        }

        return fragmentShowBinding.root
    }
}