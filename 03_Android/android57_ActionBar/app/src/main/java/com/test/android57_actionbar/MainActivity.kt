package com.test.android57_actionbar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.test.android57_actionbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(activityMainBinding.root)
    }

    // 옵션메뉴
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.item1 -> {
                activityMainBinding.textView.text = "메뉴1을 눌렀습니다"
            }
            R.id.item2 -> {
                activityMainBinding.textView.text = "메뉴2를 눌렀습니다"
            }
            R.id.item3 -> {
                activityMainBinding.textView.text = "메뉴3을 눌렀습니다"
            }
            R.id.item4 -> {
                activityMainBinding.textView.text = "메뉴4를 눌렀습니다"
            }
        }

        return super.onOptionsItemSelected(item)
    }
}









