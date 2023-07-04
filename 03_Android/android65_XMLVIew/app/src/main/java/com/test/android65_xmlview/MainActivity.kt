package com.test.android65_xmlview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.test.android65_xmlview.databinding.ActivityMainBinding
import com.test.android65_xmlview.databinding.LayoutSub1Binding
import com.test.android65_xmlview.databinding.LayoutSub3Binding
import com.test.android65_xmlview.databinding.LayoutSub4Binding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    lateinit var layoutSub3Binding: LayoutSub3Binding
    lateinit var layoutSub4Binding: LayoutSub4Binding
    lateinit var layoutSub1Binding: LayoutSub1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // LayoutInflater로 View 생성
        val sub1 = layoutInflater.inflate(R.layout.layout_sub1, null)
        val sub2 = layoutInflater.inflate(R.layout.layout_sub2, null)

        // ViewBiding 생성
        layoutSub3Binding = LayoutSub3Binding.inflate(layoutInflater)
        layoutSub4Binding = LayoutSub4Binding.inflate(layoutInflater)

        // 생성한 View들을 레이아웃에 추가해준다.
        activityMainBinding.mainContainer.addView(sub1)
        activityMainBinding.mainContainer.addView(sub2)
        activityMainBinding.mainContainer.addView(layoutSub3Binding.root)
        activityMainBinding.mainContainer.addView(layoutSub4Binding.root)

        // layout_sub1에 있는 Button과 TextView를 가져온다.
//        val buttonSub1 = sub1.findViewById<Button>(R.id.buttonSub1)
//        val textViewSub1 = sub1.findViewById<TextView>(R.id.textViewSub1)
//        buttonSub1.setOnClickListener {
//            textViewSub1.text = "Sub1 버튼을 눌렀습니다"
//        }
        // Viwe 객체를 바인딩 객체에 설정한다.
        layoutSub1Binding = LayoutSub1Binding.bind(sub1)
        layoutSub1Binding.run{
            buttonSub1.setOnClickListener {
                textViewSub1.text = "sub1 버튼을 눌렀습니다"
            }
        }

        layoutSub3Binding.run{
            buttonSub3.setOnClickListener {
                textViewSub3.text = "sub3 버튼을 눌렀습니다"
            }
        }
    }
}










