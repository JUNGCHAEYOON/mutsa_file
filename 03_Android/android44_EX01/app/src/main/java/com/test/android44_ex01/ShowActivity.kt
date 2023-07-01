package com.test.android44_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android44_ex01.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var activityShowBinding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityShowBinding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(activityShowBinding.root)

        activityShowBinding.run{
            val position = intent.getIntExtra("position", 0)

            textViewType.text = DataClass.fluitList[position].type
            textViewVolume.text = "${DataClass.fluitList[position].volume}개"
            textViewRegion.text = DataClass.fluitList[position].region

            buttonToMain2.run{
                setOnClickListener {
                    finish()
                }
            }
        }
    }
}








