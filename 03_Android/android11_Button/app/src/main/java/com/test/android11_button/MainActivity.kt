package com.test.android11_button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.test.android11_button.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button2.run{
                // 버튼의 문자열을 설정한다.
                text = "버튼 입니다"
                // 버튼을 눌렀을 때 반응하는 리스너
                setOnClickListener { 
                    activityMainBinding.textView.text = "버튼을 눌렀습니다"
                }
            }
            imageButton2.run{
                // 이미지 버튼의 이미지를 설정한다.
                setImageResource(R.drawable.imgflag8)
                // 이미지 버튼을 눌렀을 때 반응하는 리스너
                setOnClickListener { 
                    activityMainBinding.textView.text = "이미지 버튼을 눌렀습니다"
                }
            }
        }


    }
}