package com.test.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.test.mvvm.databinding.ActivityResultBinding
import com.test.mvvm.vm.ViewModelTest1
import com.test.mvvm.vm.ViewModelTest2

class ResultActivity : AppCompatActivity() {

    lateinit var activityResultBinding: ActivityResultBinding

    // ViewModel
    lateinit var viewModelTest2: ViewModelTest1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityResultBinding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(activityResultBinding.root)

        // ViewModel 객체를 가져온다.
        viewModelTest2 = ViewModelProvider(this)[ViewModelTest1::class.java]

        activityResultBinding.run{

            // ViewModel 객체가 가지고 있는 프로퍼티에 대한 감시자를 설정한다.
            viewModelTest2.run{
                data1.observe(this@ResultActivity){
                    textView.text = it
                }

                data2.observe(this@ResultActivity){
                    textView2.text = it
                }
            }

            // 데이터를 가져온다.
            viewModelTest2.getOne(intent.getIntExtra("testIdx", 0))

            // ViewModel 객체에 새로운 값을 설정한다.
//            viewModelTest2.data1.value = "새로운 문자열1"
//            viewModelTest2.data2.value = "새로운 문자열2"
        }
    }
}










