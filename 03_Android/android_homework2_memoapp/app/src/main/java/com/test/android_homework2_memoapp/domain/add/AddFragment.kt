package com.test.android_homework2_memoapp.domain.add

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.android_homework2_memoapp.MainActivity
import com.test.android_homework2_memoapp.Memo
import com.test.android_homework2_memoapp.R
import com.test.android_homework2_memoapp.database.DAO
import com.test.android_homework2_memoapp.databinding.FragmentAddBinding
import com.test.android_homework2_memoapp.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddFragment : Fragment() {

    lateinit var fragmentAddBinding: FragmentAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentAddBinding = FragmentAddBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentAddBinding.run{
            tbAdd.run{
                title = "메모추가"

                // 저장버튼
                inflateMenu(R.menu.menu_add)
                setOnMenuItemClickListener {

                    // 저장하기
                    run{
                        val title = etAddTitle.text.toString()
                        val content = etAddContent.text.toString()
                        // 날짜
                        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val date = sdf.format(Date())

                        val memo = Memo(0,title = title,date = date,content = content)
                        // insert 시에는 idx 를 0 으로 줘도 알아서 순서대로 indexing
                        DAO.insertData(mainActivity,memo)
                    }

                    // 메인으로 돌아가기
                    mainActivity.replaceFragment(MainActivity.MAIN_FRAGMENT,true,true)
                    false
                }

                // 백버튼
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.replaceFragment(MainActivity.MAIN_FRAGMENT,true,true)
                }
            }

            // 시작시 포커스
            etAddTitle.requestFocus()
        }

        return fragmentAddBinding.root
    }

}