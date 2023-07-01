package com.test.android47_dialog

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AlertDialog
import com.test.android47_dialog.databinding.ActivityMainBinding
import com.test.android47_dialog.databinding.DialogBinding
import java.util.Calendar
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val dateList = arrayOf(
        "항목1", "항목2", "항목3", "항목4", "항목5", "항목6",
        "항목7", "항목8", "항목9", "항목10", "항목11", "항목12",
        "항목13", "항목14", "항목15", "항목16", "항목17", "항목18"
    )

    val mutilChoiceList = BooleanArray(dateList.size){i -> false}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.setOnClickListener {
                // 다이얼로그 생성을 위한 객체를 생성한다.
                val builder = AlertDialog.Builder(this@MainActivity)

                // 타이틀
                builder.setTitle("기본 다이얼로그")
                // 메시지
                builder.setMessage("기본 다이얼로그 입니다")
                // 아이콘
                builder.setIcon(R.mipmap.ic_launcher)

                // 버튼을 배치한다.
                // builder.setPositiveButton("Positive", null)
                // builder.setNegativeButton("Negative", null)
                // builder.setNeutralButton("Neutral", null)

                builder.setPositiveButton("Positive"){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = "Positive 버튼을 눌렀습니다"
                }
                builder.setNegativeButton("Negative"){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = "Negative 버튼을 눌렀습니다"
                }
                builder.setNeutralButton("Neutral"){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = "Neutral 버튼을 눌렀습니다"
                }


                // 다이얼로그를 띄운다.
                builder.show()
            }

            button2.setOnClickListener {
                val dialogBinding = DialogBinding.inflate(layoutInflater)

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("커스텀 다이얼로그")
                builder.setIcon(R.mipmap.ic_launcher)

                // 새로운 뷰를 설정한다.
                builder.setView(dialogBinding.root)

                dialogBinding.editTextDialog1.requestFocus()

                thread {
                    SystemClock.sleep(500)
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(dialogBinding.editTextDialog1, 0)
                }

                builder.setPositiveButton("확인", ){ dialogInterface: DialogInterface, i: Int ->
                    // 입력한 내용을 가져온다.
                    val str1 = dialogBinding.editTextDialog1.text.toString()
                    val str2 = dialogBinding.editTextDialog2.text.toString()

                    textView.text = "${str1}\n"
                    textView.append("${str2}")
                }
                builder.setNegativeButton("취소", null)
                builder.show()
            }

            button3.setOnClickListener {
                // 날짜를 선택하기 위해 사용하는 다이얼로그

                val calendar = Calendar.getInstance()

                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                // 날짜 선택하면 동작할 리스너
                val datePickerListener = object : DatePickerDialog.OnDateSetListener{
                    // 2번째, 3번째, 4번째 : 년, 월, 일
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                        textView.text = "${p1}년 ${p2 + 1}월 ${p3}일"
                    }
                }

                val pickerDialog = DatePickerDialog(this@MainActivity, datePickerListener, year, month, day)
                pickerDialog.show()
            }

            button4.setOnClickListener {
                val calendar = Calendar.getInstance()

                val hour = calendar.get(Calendar.HOUR)
                val minute = calendar.get(Calendar.MINUTE)


                val timePickerListener = object : TimePickerDialog.OnTimeSetListener{
                    // 두 번째 : 시간, 세 번째 : 분
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        textView.text = "${p1}시 ${p2}분"
                    }
                }

                val pickerDialog = TimePickerDialog(this@MainActivity, timePickerListener,
                    hour, minute, true)
                pickerDialog.show()
            }

            button5.setOnClickListener {
                val adapter = ArrayAdapter<String>(
                    this@MainActivity, android.R.layout.simple_list_item_1, dateList
                )

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("리스트 다이얼로그")
                builder.setIcon(R.mipmap.ic_launcher)

                // 어뎁터를 설정한다.
                // 두 번째 매개변수에는 사용자가 선택한 항목의 순서값이 들어온다.
                builder.setAdapter(adapter){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = "선택한 항목 : ${dateList[i]}"
                }

                builder.setNegativeButton("취소", null)
                builder.show()
            }

            button6.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("다중 선택 리스트 다이얼로그")

                val boolArray = BooleanArray(dateList.size) { i -> false }

                boolArray[0] = true
                boolArray[2] = true
                boolArray[4] = true

                builder.setMultiChoiceItems(dateList, boolArray, null)

                builder.setNegativeButton("취소", null)
                builder.setPositiveButton("확인", ){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = ""

                    // 다이얼로그에서 ListView를 추출한다.
                    val alertDialog = dialogInterface as AlertDialog
                    // 현재 체크되어 있는 상태 정보를 추출한다.
                    val position = alertDialog.listView.checkedItemPositions
                    // 위에서 반환된 객체에는 체크 되어 있는 것과 체크 상태가 변경된 것들의 정보만 담겨져있다.
                    // {순서값=체크여부}
                    for(idx in 0 until  position.size()){
                        // 현재 항목의 위치값을 가져온다.
                        val pos1 = position.keyAt(idx)
                        // 현재 번째 배열에 값을 넣어준다.
                        boolArray[pos1] = position.get(pos1)
                    }

                    for(idx in 0 until boolArray.size){
                        if(boolArray[idx] == true){
                            textView.append("${dateList[idx]}\n")
                        }
                    }
                }

                builder.show()
            }

            button7.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("다중 선택 리스트 다이얼로그")

                // 마지막 매개변수에 체크 상태가 변경되었을 때 동작하는 리스너를 설정해준다.
                // 여기에서 체크 상태가 변경된 항목의 체크 상태 값을 BooleanArray에 담아 준다
                // 두 번째 : 체크 상태가 변경된 항목의 순서값
                // 세 번째 : 체크 상태
                builder.setMultiChoiceItems(dateList, mutilChoiceList){ dialogInterface: DialogInterface, i: Int, b: Boolean ->
                    mutilChoiceList[i] = b
                }

                builder.setNegativeButton("취소", null)
                builder.setPositiveButton("확인", ){ dialogInterface: DialogInterface, i: Int ->
                    textView.text = ""

                    for(idx in 0 until mutilChoiceList.size){
                        if(mutilChoiceList[idx] == true){
                            textView.append("${dateList[idx]}\n")
                        }
                    }
                }

                builder.show()
            }
        }
    }
}






