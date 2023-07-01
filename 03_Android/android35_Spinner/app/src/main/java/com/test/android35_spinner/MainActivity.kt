package com.test.android35_spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.test.android35_spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val dataList = arrayOf(
        "항목1", "항목2", "항목3", "항목4", "항목5",
        "항목6", "항목7", "항목8", "항목9", "항목10",
        "항목11", "항목12", "항목13", "항목14", "항목15",
        "항목16", "항목17", "항목18", "항목19", "항목20",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            spinner.run{
                // 어뎁터 설정
                val a1 = ArrayAdapter<String>(
                    this@MainActivity,
                    // Spinner가 접혀있을 때의 모양
                    android.R.layout.simple_spinner_item,
                    dataList
                )
                // Spinner가 펼쳐져 있을 때의 항목 모양
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                adapter = a1

                // Spinner의 항목을 코드로 선택한다.
                // 0부터 시작하는 순서값을 넣어준다.
                setSelection(2)
                
                // 항목을 선택하면 동작하는 리스너
                // 3번째 : 선택한 항목의 순서값(0부터..)
                onItemSelectedListener = object : OnItemSelectedListener{
                    // 항목을 선택했을 호출되는 메서드
                    // 3 번째 : 선택한 항목의 순서 값(0부터..)
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        textView2.text = "${dataList[position]} 항목을 선택했습니다"
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // TODO("Not yet implemented")
                    }
                }
            }


            button.run{
                setOnClickListener {
                    // Spinner에서 선택된 항목의 순서값을 가져온다.
                    val position = spinner.selectedItemPosition
                    textView.text = "선택한 항목 : ${dataList[position]}"
                }
            }
        }
    }
}