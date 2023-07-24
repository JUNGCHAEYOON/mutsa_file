package com.test.material05_fab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.material05_fab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        
        
        activityMainBinding.run{
            fab1.setOnClickListener{
                textView.text = "FAB1 버튼을 눌렀습니다"

                // fab2가 보이는 상태라면
                if(fab2.isShown){
                    fab2.hide()
                }
                // fab2가 보이지 않는 상태라면
                else {
                    fab2.show()
                }

                // fab3이 펼쳐져 있는 상태라면
                if(fab3.isExtended){
                    fab3.shrink()
                }
                // 접혀져 있는 상태라면
                else {
                    fab3.extend()
                }
            }
        }
    }
}









