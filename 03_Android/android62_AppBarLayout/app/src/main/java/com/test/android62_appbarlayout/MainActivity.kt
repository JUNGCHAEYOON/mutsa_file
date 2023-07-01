package com.test.android62_appbarlayout

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import com.test.android62_appbarlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            appBarImage.setImageResource(R.drawable.img_android)

            // toolbar를 ActionBar로 사용하도록 설정한다.
            setSupportActionBar(toolbar)

            //  접혔을 때의 타이틀 색상
            toolbarLayout.setCollapsedTitleTextColor(Color.WHITE)
            // 펼쳐졌을 때의 타이틀 색상
            toolbarLayout.setExpandedTitleColor(Color.GREEN)

            // 접혓을 때의 타이틀 위치
            toolbarLayout.collapsedTitleGravity = Gravity.CENTER_HORIZONTAL
            // 펼쳐졌을 때의 타이틀 색상
            toolbarLayout.expandedTitleGravity = Gravity.RIGHT + Gravity.TOP

            // activity_main.xml에서 CollapsingToolbarLayout 의 layout_scrollFlags 속성
            // "scroll|enterAlways|enterAlwaysCollapsed" : 접었을 때 툴바가 사라진다.
            // "scroll|exitUntilCollapsed" : 접었을 때 툴바가 사라지지 않는다.
        }
    }
}