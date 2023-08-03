package com.test.mvvm_practice_sql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.test.mvvm_practice_sql.databinding.ActivityAddBinding
import com.test.mvvm_practice_sql.repository.Test1Repository
import com.test.mvvm_practice_sql.vm.TestData
import com.test.mvvm_practice_sql.vm.ViewModelTest2

class AddActivity : AppCompatActivity() {

    lateinit var activityAddBinding: ActivityAddBinding

    // ViewModel
    lateinit var viewModelTest2: ViewModelTest2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityAddBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(activityAddBinding.root)

        viewModelTest2 = ViewModelProvider(MainActivity.mainActivity)[ViewModelTest2::class.java]

        activityAddBinding.run{
            buttonAdd.run{
                setOnClickListener {
                    val data1 = editTextAddData1.text.toString()
                    val data2 = editTextAddData2.text.toString()

                    val t1 = TestData(0, data1, data2)

                    Test1Repository.addData(this@AddActivity,t1)

                    finish()
                }
            }
        }
    }
}