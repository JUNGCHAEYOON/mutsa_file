package com.test.android56_ex01

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.core.text.isDigitsOnly
import com.test.android56_ex01.databinding.FragmentInputBinding
import kotlin.concurrent.thread

class InputFragment : Fragment() {

    lateinit var fragmentInputBinding:FragmentInputBinding
    lateinit var mainActivity: MainActivity
    
    // Spinner 데이터
    val animalType = arrayOf(
        "코끼리", "기린", "토끼"
    )
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        
        fragmentInputBinding = FragmentInputBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        val imm = mainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        fragmentInputBinding.run{
            spinnerInputType.run{
                val spinnerAdapter = ArrayAdapter<String>(
                    mainActivity, android.R.layout.simple_spinner_item, animalType
                )
                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                adapter = spinnerAdapter
            }

            editTextInputName.requestFocus()

            thread {
                SystemClock.sleep(100)

                imm.showSoftInput(editTextInputName, 0)
            }

            editTextInputWeight.run{
                setOnEditorActionListener { textView, i, keyEvent ->

                    if(editTextInputName.text.length == 0){
                        val builder = AlertDialog.Builder(mainActivity)
                        builder.setTitle("이름 입력 오류")
                        builder.setMessage("이름을 입력해주세요")
                        builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                            editTextInputName.requestFocus()
                            thread {
                                SystemClock.sleep(100)
                                imm.showSoftInput(editTextInputName, 0)
                            }

                        }
                        builder.show()
                        return@setOnEditorActionListener false
                    }

                    if(editTextInputAge.text.length == 0 ||
                        editTextInputAge.text.isDigitsOnly() == false ||
                            editTextInputAge.text.toString().toInt() < 0){
                        val builder = AlertDialog.Builder(mainActivity)
                        builder.setTitle("나이 입력 오류")
                        builder.setMessage("나이를 잘못 입력하였습니다")
                        builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                            editTextInputAge.setText("")
                            editTextInputAge.requestFocus()
                            thread {
                                SystemClock.sleep(100)
                                imm.showSoftInput(editTextInputAge, 0)
                            }
                        }
                        builder.show()
                        return@setOnEditorActionListener false
                    }

                    if(editTextInputWeight.text.length == 0 ||
                        editTextInputWeight.text.isDigitsOnly() == false ||
                        editTextInputWeight.text.toString().toInt() < 0){
                        val builder = AlertDialog.Builder(mainActivity)
                        builder.setTitle("몸무게 입력 오류")
                        builder.setMessage("몸무게를 잘못 입력하였습니다")
                        builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                            editTextInputWeight.setText("")
                            editTextInputWeight.requestFocus()
                            thread {
                                SystemClock.sleep(100)
                                imm.showSoftInput(editTextInputWeight, 0)
                            }
                        }
                        builder.show()
                        return@setOnEditorActionListener false
                    }

                    val type = animalType[spinnerInputType.selectedItemPosition]
                    val name = editTextInputName.text.toString()
                    val age = editTextInputAge.text.toString().toInt()
                    val weight = editTextInputWeight.text.toString().toInt()

                    val animalInfo = AnimalInfo(type, name, age, weight)
                    mainActivity.animalList.add(0, animalInfo)

                    mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)

                    false
                }
            }
        }
        
        return fragmentInputBinding.root
    }

}