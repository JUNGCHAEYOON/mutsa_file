package com.test.android15_scrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnScrollChangeListener
import com.test.android15_scrollview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            scroll1.run{
                // ScrollView의 Scroll 이벤트
                // 이벤트가 발생한 뷰, 스크롤된 X 좌표, 스크롤된 Y 좌표, 스크롤 되기전 X 좌표, 스크롤 되기전 Y 좌표
                setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                    textView2.text = "Y : $oldScrollY -> $scrollY"
                }
            }

            scroll2.run{
                setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                    textView.text = "X : $oldScrollX -> $scrollX"
                }
            }

            button.setOnClickListener {
                // ScrollView의 Y 좌표를 가져온다.
                textView.text = "X : ${scroll2.scrollX}"
                textView2.text = "Y : ${scroll1.scrollY}"
            }

            button2.setOnClickListener {
                // 지정된 위치로 이동한다.
//                scroll1.scrollTo(0, 1000)
//                scroll2.scrollTo(1000, 0)
                // 현재 위치에서 지정된 만큼 이동한다.
//                scroll1.scrollBy(0, 100)
//                scroll2.scrollBy(100, 0)
                // 지정된 위치로 이동한다(애니메이션)
//                scroll1.smoothScrollTo(0, 1000)
//                scroll2.smoothScrollTo(1000, 0)
                // 지정된 만큼 이동한다.(애니메이션)
                scroll1.smoothScrollBy(0, 100)
                scroll2.smoothScrollBy(100, 0)
            }
        }
    }
}