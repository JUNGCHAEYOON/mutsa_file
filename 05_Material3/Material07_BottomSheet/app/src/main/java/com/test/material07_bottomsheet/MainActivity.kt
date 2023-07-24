package com.test.material07_bottomsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.test.material07_bottomsheet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            // BottomSheet의 행동을 관리하는 객체
            val sheetBehavior = BottomSheetBehavior.from(include1.bottomSheet)
            // 사라지게 한다.
            sheetBehavior.isHideable = true
            sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

            // 접힌 상태로 간다.
            // include 에 app:behavior_peekHeight="높이" 를 설정해준다.
//            sheetBehavior.isHideable = false
//            sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

            button.setOnClickListener {
                // 나타나게 한다.
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }

            button2.setOnClickListener {
                if(sheetBehavior.isHideable){
                    sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                } else {
                    sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }

            include1.run{
                buttonBottom1.setOnClickListener {
                    textView.text = "버튼1을 눌렀습니다"
                    if(sheetBehavior.isHideable){
                        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                    } else {
                        sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                }

                buttonBottom2.setOnClickListener {
                    textView.text = "버튼2을 눌렀습니다"
                    if(sheetBehavior.isHideable){
                        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                    } else {
                        sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                }

                buttonBottom3.setOnClickListener {
                    textView.text = "버튼3을 눌렀습니다"
                    if(sheetBehavior.isHideable){
                        sheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
                    } else {
                        sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                }
            }
        }
    }
}