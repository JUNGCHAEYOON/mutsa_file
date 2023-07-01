package com.test.android19_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.test.android19_ex01.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var imm:InputMethodManager

    val studentList = mutableListOf<StudentInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        activityMainBinding.run{
            // 이름을 입력하는 editText에 포커스를 준다.
            editTextName.requestFocus()
            thread {
                SystemClock.sleep(500)

                imm.showSoftInput(currentFocus, 0)
            }

            // 국어점수 입력
            editTextKorean.run{
                setOnEditorActionListener { v, actionId, event ->
                    // 학생정보를 담을 객체를 생성한다.
                    val name = editTextName.text.toString()
                    val age = editTextAge.text.toString().toInt()
                    val temp1 = radioGroupGender.checkedRadioButtonId
                    var gender = "남자"
                    if(temp1 == R.id.radioButtonWoman){
                        gender = "여자"
                    }
                    val korean = editTextKorean.text.toString().toInt()

                    val studentInfo = StudentInfo(name, age, gender, korean)

                    if(checkBoxGame.isChecked == true){
                        studentInfo.addHobby("게임")
                    }
                    if(checkBoxSoccer.isChecked == true){
                        studentInfo.addHobby("축구")
                    }
                    if(checkBoxMovie.isChecked == true){
                        studentInfo.addHobby("영화감상")
                    }

                    // 학생 정보를 담는다.
                    studentList.add(studentInfo)

                    // 입력내용을 초기화한다.
                    editTextName.setText("")
                    editTextAge.setText("")
                    checkBoxGame.isChecked = false
                    checkBoxSoccer.isChecked = false
                    checkBoxMovie.isChecked = false
                    radioGroupGender.check(R.id.radioButtonMan)
                    editTextKorean.setText("")

                    editTextName.requestFocus()

                    false
                }
            }

            // 버튼
            buttonResult.run{
                setOnClickListener {
                    if(currentFocus != null) {
                        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                        currentFocus!!.clearFocus()
                    }

                    textViewResult.text = ""

                    // 국어 점수를 누적할 변수
                    var koreanTotal = 0

                    // 학생의 수 만큼 반복한다.
                    for(studentInfo in studentList){
                        textViewResult.append("이름 : ${studentInfo.name}\n")
                        textViewResult.append("나이 : ${studentInfo.age}\n")

                        textViewResult.append("취미\n")
                        for(hobby in studentInfo.hobby){
                            textViewResult.append("${hobby}\n")
                        }

                        textViewResult.append("성별  : ${studentInfo.gender}\n")
                        textViewResult.append("국어점수 : ${studentInfo.korean}\n\n")

                        koreanTotal += studentInfo.korean
                    }

                    textViewResult.append("\n")
                    textViewResult.append("국어 총점 : ${koreanTotal}\n")
                    textViewResult.append("국어 평균 : ${koreanTotal / studentList.size}\n")
                }
            }
        }
    }

    // 학생 정보를 담을 데이터 클래스
    data class StudentInfo(var name:String, var age:Int, var gender:String, var korean:Int){
        val hobby = mutableListOf<String>()

        fun addHobby(a1:String){
            hobby.add(a1)
        }
    }
}