package com.test.android62_appbarlayout_practice

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.test.android62_appbarlayout_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            appBarImage.setImageResource(R.drawable.img_android)

            // toolbar를 Actionbar 로 사용하도록 설정한다.
            setSupportActionBar(toolbar)

            toolbarLayout.run{
                // 접혔을때 타이틀 색상
                setCollapsedTitleTextColor(Color.WHITE)

                // 펼쳐졌을때 타이틀 색상
                setExpandedTitleColor(Color.GREEN)

                // 접혔을때 타이틀 위치
                collapsedTitleGravity = Gravity.CENTER_HORIZONTAL

                // 펼쳐졌을때 타이틀 색상
                expandedTitleGravity = Gravity.RIGHT + Gravity.TOP

                // activity_main.xml에서 CollapsingToolbarLayout 의 layout_scrollFlags 속성
                // "scroll|enterAlways|enterAlwaysCollapsed" : 접었을 때 툴바가 사라진다.
                // "scroll|exitUntilCollapsed" : 접었을 때 툴바가 사라지지 않는다.
            }

        }
    }
}