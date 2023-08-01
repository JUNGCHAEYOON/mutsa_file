package com.test.mvvm_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.test.mvvm_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val testViewModel = ViewModelProvider(this@MainActivity).get<TestViewModel>()
        testViewModel.number1.observe(this){
            activityMainBinding.textView.text = "number : $it"
        }

        testViewModel.number1.value = 0

        activityMainBinding.run{

        }
    }
}

class TestViewModel : ViewModel(){
    val number1 = MutableLiveData<Int>()
}