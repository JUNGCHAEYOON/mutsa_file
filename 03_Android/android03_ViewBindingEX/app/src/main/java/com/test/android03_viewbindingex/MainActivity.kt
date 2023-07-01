package com.test.android03_viewbindingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android03_viewbindingex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding 객체를 담을 변수
    lateinit var activityMainBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 설정
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // View 설정
        activityMainBinding.run{
            // 첫 번째 버튼
            button.run{
                setOnClickListener {
                    var total = 0
                    for(a1 in 1..100){
                        total += a1
                    }
                    activityMainBinding.textView.text = "1~100까지의 총합 : $total"
                }
            }
            // 두 번째 버튼
            button2.run{
                setOnClickListener {
                    var total = 0
                    for(a1 in 101..200){
                        total += a1
                    }
                    activityMainBinding.textView.text = "101~200까지의 총합 : $total"
                }
            }
        }
    }
}