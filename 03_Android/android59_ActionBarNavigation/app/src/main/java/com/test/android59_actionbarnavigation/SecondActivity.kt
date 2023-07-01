package com.test.android59_actionbarnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Homebutton을 활성화 한다.
        supportActionBar?.setHomeButtonEnabled(true)
        // HomeButton을 노출시킨다.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 아이콘을 변경한다.
        // supportActionBar?.setHomeAsUpIndicator(R.mipmap.ic_launcher)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            // 백버튼
            android.R.id.home -> {
                // 현재 Activity를 종료한다.
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}