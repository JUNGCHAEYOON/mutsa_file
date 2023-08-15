package com.test.keepgardeningproject_customer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.keepgardeningproject_customer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

        }
    }

    // TestCommit
}