package com.test.android_homework2_memoapp.domain.modify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_homework2_memoapp.MainActivity
import com.test.android_homework2_memoapp.Memo
import com.test.android_homework2_memoapp.R
import com.test.android_homework2_memoapp.database.DAO
import com.test.android_homework2_memoapp.databinding.FragmentModifyBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ModifyFragment : Fragment() {

    lateinit var fragmentModifyBinding: FragmentModifyBinding
    lateinit var mainActivity: MainActivity

    lateinit var memo : Memo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentModifyBinding = FragmentModifyBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity
        memo = DAO.selectData(mainActivity,MainActivity.nowIdx)

        fragmentModifyBinding.run{
            etModifyTitle.setText(memo.title)
            etModifyContent.setText(memo.content)
            etModifyTitle.requestFocus()

            tbModify.run{
                title = "메모수정"
                inflateMenu(R.menu.menu_modify)

                // 저장버튼
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.item_modify_modify->{
                            // 저장하기
                            run{
                                val idx = memo.idx
                                val title = etModifyTitle.text.toString()
                                val content = etModifyContent.text.toString()
                                // 날짜
                                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                                val date = sdf.format(Date())

                                val newMemo = Memo(idx = idx,title = title,date = date,content = content)
                                DAO.updateData(mainActivity,newMemo)
                            }

                            // 쇼프래그먼트로 돌아가기
                            mainActivity.replaceFragment(MainActivity.SHOW_FRAGMENT,true,true)
                            false
                        }

                        else -> {
                            false
                        }
                    }
                }

                // 백버튼
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.replaceFragment(MainActivity.SHOW_FRAGMENT,true,true)
                }
            }
        }

        return fragmentModifyBinding.root
    }

}