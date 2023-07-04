package com.test.android66_codeview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.test.android66_codeview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    // 생성된 뷰들을 담을 리스트
    val viewList = mutableListOf<EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.setOnClickListener {
                // View의 가로세로 길이
                val params = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                // EditText를 생성한다.
                val newEditText = EditText(this@MainActivity)

                // 설정
                newEditText.layoutParams = params
                newEditText.hint = "문자열을 입력해주세요"

                viewList.add(newEditText)

                // 생성한 뷰를 추가한다.
                activityMainBinding.mainContainer.addView(newEditText)
            }

            button2.setOnClickListener {
                textView.text = ""
                // 리스트에 들어 있는 뷰의 수 만큼 반복한다.
                for(v1 in viewList){
                    textView.append("${v1.text.toString()}\n")
                }
            }

            button3.setOnClickListener {
                // 순서를 지정하여 제거
//                activityMainBinding.mainContainer.removeViewAt(viewList.size - 1)
//                viewList.removeLast()

                // View를 지정하여 제거
                val lastView = viewList.last()
                activityMainBinding.mainContainer.removeView(lastView)
                viewList.removeLast()
            }

            button4.setOnClickListener {
                // 모든 뷰를 제거한다.
                activityMainBinding.mainContainer.removeAllViews()
            }
        }
    }
}