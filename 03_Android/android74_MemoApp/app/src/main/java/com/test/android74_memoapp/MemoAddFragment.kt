package com.test.android74_memoapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.test.android74_memoapp.databinding.FragmentMemoAddBinding

class MemoAddFragment : Fragment() {

    lateinit var fragmentMemoAddBinding:FragmentMemoAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentMemoAddBinding = FragmentMemoAddBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentMemoAddBinding.run{

            mainActivity.showSoftInput(memoSubject, 200)

            toolbarMemoAdd.run{
                title = "메모 등록"
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.MEMO_ADD_FRAGMENT)
                }
                inflateMenu(R.menu.memo_add_menu)
                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.memo_add_item1 -> {
                            val str1 = memoSubject.text.toString()
                            val str2 = memoText.text.toString()

                            if(str1.length == 0){
                                val builder = AlertDialog.Builder(mainActivity)
                                builder.setTitle("제목 입력 오류")
                                builder.setMessage("제목을 입력해주세요")
                                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(memoSubject, 200)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener false
                            }

                            if(str2.length == 0){
                                val builder = AlertDialog.Builder(mainActivity)
                                builder.setTitle("내용 입력 오류")
                                builder.setMessage("내용을 입력해주세요")
                                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(memoText, 200)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener false
                            }


                            mainActivity.hideSoftInput()
                            mainActivity.removeFragment(MainActivity.MEMO_ADD_FRAGMENT)
                        }
                    }
                    false
                }
            }
        }

        return fragmentMemoAddBinding.root
    }

}