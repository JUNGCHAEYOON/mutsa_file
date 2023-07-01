package com.test.android17_imageview

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android17_imageview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.run{
                setOnClickListener {
                    // drawable, mipmap 폴더에 있는 이미지를 지정한다.
                    imageView2.setImageResource(R.drawable.img_android)
                }
            }
        }
    }
}










