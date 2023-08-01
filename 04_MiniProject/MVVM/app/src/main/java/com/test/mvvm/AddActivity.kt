package com.test.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.mvvm.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    lateinit var activityAddBinding: ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityAddBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(activityAddBinding.root)

        activityAddBinding.run{
            buttonAdd.run{
                setOnClickListener {
                    finish()
                }
            }
        }
    }
}