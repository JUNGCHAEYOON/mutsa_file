package com.test.android48_ex01

import android.app.DatePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import com.test.android48_ex01.databinding.ActivityInputBinding
import java.util.Calendar
import kotlin.concurrent.thread

class InputActivity : AppCompatActivity() {

    lateinit var activityInputBinding : ActivityInputBinding
    
    val genderList = arrayOf(
        "남자", "여자"
    )

    val hobbyList = arrayOf(
        "영화감상", "독서", "헬스", "골프", "낚시", "당구", "게임"
    )
    val hobbyCheckArray = BooleanArray(hobbyList.size) { i -> false }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityInputBinding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(activityInputBinding.root)

        activityInputBinding.run{
            editTextName.requestFocus()

            thread {
                SystemClock.sleep(500)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editTextName, 0)
            }

            buttonInputDate.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val pickerDialog = DatePickerDialog(this@InputActivity, { datePicker: DatePicker, i: Int, i1: Int, i2: Int ->
                    buttonInputDate.text = "${i}년 ${i1 + 1}월 ${i2}일"
                }, year, month, day)

                pickerDialog.show()
            }
            
            buttonGender.setOnClickListener { 
                val builder = AlertDialog.Builder(this@InputActivity)
                builder.setTitle("성별")
                builder.setNegativeButton("취소", null)

                builder.setItems(genderList){ dialogInterface: DialogInterface, i: Int ->
                    buttonGender.text = genderList[i]
                }
                builder.show()
            }

            buttonHobby.setOnClickListener {
                val builder = AlertDialog.Builder(this@InputActivity)
                builder.setTitle("취미")
                builder.setMultiChoiceItems(hobbyList, hobbyCheckArray){ dialogInterface: DialogInterface, i: Int, b: Boolean ->
                    hobbyCheckArray[i] = b
                }

                builder.setNegativeButton("취소", null)
                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                    var c1 = 0
                    for(idx in 0 until hobbyCheckArray.size){
                        if(hobbyCheckArray[idx] == true){
                            if(c1 == 0){
                                buttonHobby.text = "${hobbyList[idx]}"
                                c1++
                            } else {
                                c1++
                            }
                        }
                    }

                    if(c1 > 1){
                        buttonHobby.append(" 외 ${c1 - 1}개")
                    }
                    if(c1 == 0){
                        buttonHobby.text = "취미 없음"
                    }
                }
                builder.show()
            }

            buttonOk.setOnClickListener {
                // 이름 유효성 검사
                if(editTextName.text.isEmpty()){
                    val builder = AlertDialog.Builder(this@InputActivity)
                    builder.setTitle("이름 입력 오류")
                    builder.setMessage("이름을 입력해주세요")
                    builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                        editTextName.requestFocus()
                    }
                    builder.show()
                    return@setOnClickListener
                }
                // 날짜
                if(buttonInputDate.text == "날짜입력"){
                    val builder = AlertDialog.Builder(this@InputActivity)
                    builder.setTitle("날짜 입력 오류")
                    builder.setMessage("날짜를 입력해주세요")
                    builder.setPositiveButton("확인", null)
                    builder.show()
                    return@setOnClickListener
                }
                // 성별
                if(buttonGender.text == "성별"){
                    val builder = AlertDialog.Builder(this@InputActivity)
                    builder.setTitle("성별 입력 오류")
                    builder.setMessage("성별을 입력해주세요")
                    builder.setPositiveButton("확인", null)
                    builder.show()
                    return@setOnClickListener
                }

                val name = editTextName.text.toString()
                val date = buttonInputDate.text.toString()
                val gender = buttonGender.text.toString()

                val inputHobbyList = mutableListOf<String>()
                for(idx in 0 until hobbyCheckArray.size){
                    if(hobbyCheckArray[idx] == true){
                        inputHobbyList.add(hobbyList[idx])
                    }
                }

                val humanInfo = HumanInfo(name, date, gender, inputHobbyList)
                DataClass.humanList.add(humanInfo)

                finish()
            }

            buttonCancel.setOnClickListener {
                finish()
            }
        }
    }
}