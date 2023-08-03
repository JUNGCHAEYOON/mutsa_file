package com.test.mvvm_practice_sql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.test.mvvm_practice_sql.databinding.ActivityResultBinding
import com.test.mvvm_practice_sql.vm.ViewModelTest1

class ResultActivity : AppCompatActivity() {

    lateinit var activityResultBinding: ActivityResultBinding

    // ViewModel
    lateinit var viewModelTest1: ViewModelTest1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityResultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(activityResultBinding.root)

        viewModelTest1 = ViewModelProvider(this)[ViewModelTest1::class.java]

        activityResultBinding.run{
            viewModelTest1.run{
                data2.observe(this@ResultActivity){
                    textView.text = it
                }

                data2.observe(this@ResultActivity){
                    textView2.text = it
                }
            }

            // 데이터를 가져온다.
            viewModelTest1.getOne(intent.getIntExtra("testIdx",0))
        }
    }
}