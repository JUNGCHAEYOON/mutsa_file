package com.test.android14_ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.inputmethod.InputMethodManager
import com.test.android14_ex03.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    val studentList = mutableListOf<StudentInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            // 첫 번째 EditText에 포커스를 준다.
            editTextName.requestFocus()

            thread {
                SystemClock.sleep(500)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus, 0)
            }

            // 수학점수
            editTextMath.run{
                setOnEditorActionListener { v, actionId, event ->
                    // 사용자가 입력한 내용을 모두 가져온다.
                    val name = editTextName.text.toString()
                    val age = editTextAge.text.toString().toInt()
                    val korean = editTextKorean.text.toString().toInt()
                    val english = editTextEnglish.text.toString().toInt()
                    val math = text.toString().toInt()

//                    Log.d("studentInfo", name)
//                    Log.d("studentInfo", age.toString())
//                    Log.d("studentInfo", korean.toString())
//                    Log.d("studentInfo", english.toString())
//                    Log.d("studentInfo", math.toString())

                    // 입력한 학생 정보를 담아준다.
                    val studentInfo = StudentInfo(name, age, korean, english, math)
                    studentList.add(studentInfo)

                    // 텍스트 뷰에 학생 수를 출력해준다.
                    textViewStudentCount.text = "현재 학생 수 : ${studentList.size}"

                    // EditText들을 비워준다.
                    editTextName.setText("")
                    editTextAge.setText("")
                    editTextKorean.setText("")
                    editTextEnglish.setText("")
                    setText("")

                    editTextName.requestFocus()

                    true
                }
            }

            buttonShowAgg.run{
                setOnClickListener {

                    // 각 과목별 총점과 평균
                    var koreanTotal = 0
                    var englishTotal = 0
                    var mathTotal = 0

                    // 학생의 수 만큼 반복한다.
                    for(studentInfo in studentList){
                        // 학생 정보 출력
                        studentInfo.run{
                            Log.d("studentInfo", "이름 : $name")
                            Log.d("studentInfo", "나이 : $age")
                            Log.d("studentInfo", "국어점수 : $korean")
                            Log.d("studentInfo", "영어점수 : $english")
                            Log.d("studentInfo", "수학점수 : $math")
                            Log.d("studentInfo", "----------------------------")

                            // 각 점수를 누적한다.
                            koreanTotal += korean
                            englishTotal += english
                            mathTotal += math
                        }
                    }

                    // 평균을 구한다.
                    val koreanAvg = koreanTotal / studentList.size
                    val englishAvg = englishTotal / studentList.size
                    val mathAvg = mathTotal / studentList.size

                    Log.d("studentInfo", "국어 총점 : $koreanTotal")
                    Log.d("studentInfo", "영어 총점 : $englishTotal")
                    Log.d("studentInfo", "수학 총점 : $mathTotal")
                    Log.d("studentInfo", "국어 평균 : $koreanAvg")
                    Log.d("studentInfo", "영어 평균 : $englishAvg")
                    Log.d("studentInfo", "수학 평균 : $mathAvg")
                }
            }
        }
    }

    // 사용자 정보를 담을 클래스
    data class StudentInfo(var name:String, var age:Int, var korean:Int, var english:Int, var math:Int)
}