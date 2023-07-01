package com.test.android28_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import com.test.android28_listview.databinding.ActivityMainBinding

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

        // Adapter 생성
        // ArrayAdapter : 칸 하나에 문자열 하나만 사용하는 경우
        // android.R.layout.simple_list_item1 : 안드로이드에서
        // 리스트뷰의 항목 하나를 구성할 때 사용하라고 제공하는 layout
        // TextView 하나만 있다.

        // Context : 어떠한 작업을 위한 정보를 관리하는 요소들을 통칭한다.
        // 안드로이드는 시스템이나 애플리케이션과 관련된 정보를 가지고 있다.

        // Context, 항목하나를 구성하기 위해 사용할 레이아웃, 텍스트뷰에 채워줄 문자열 배열
        // 지정된 레이아웃 안에 있는 android.R.id.text1 이라는 View에 문자열을 셋팅해준다
        val adapter = ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, data1
        )

        activityMainBinding.run{
            listView.run{
                // ListView에 어뎁터를 설정한다.
                setAdapter(adapter)
                
                // ListView의 항목 하나를 선택하면 동작하는 리스너
                // position : 사용자가 터치한 항목의 순서값(0부터 1씩 증가)
                setOnItemClickListener { parent, view, position, id -> 
                    textView.text = "${data1[position]}를 눌렀습니다"
                }
            }
        }

    }
}