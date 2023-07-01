package com.test.android30_customitemview1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.test.android30_customitemview1.databinding.ActivityMainBinding

// AdapterView의 항목을 구성하기 위한 layout 파일을
// 개발자가 직접 만들고 문자열 하나를 설정하는 경우

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // ListView를 구성하기 위해 필요한 데이터
    val data1 = arrayOf(
        "문자열1", "문자열2", "문자열3", "문자열4", "문자열5",
        "문자열6", "문자열7", "문자열8", "문자열9", "문자열10",
        "문자열11", "문자열12", "문자열13", "문자열14", "문자열15",
        "문자열16", "문자열17", "문자열18", "문자열19", "문자열20",
        "문자열21", "문자열22", "문자열23", "문자열24", "문자열25"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            listView.run{
                adapter = ArrayAdapter<String>(
                    this@MainActivity, R.layout.row, R.id.textView2, data1
                )

                setOnItemClickListener { parent, view, position, id ->
                    textView.text = data1[position]
                }
            }
        }
    }
}






