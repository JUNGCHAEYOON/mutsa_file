package com.test.android16_textinputlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.test.android16_textinputlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            
            textInputLayout.run{
                editText?.run{
                    addTextChangedListener {
                        if(it!!.length > 10){
                            // error = "10 글자 이하로 입력해주세요"
                            textInputLayout.error = "10 글자 이하로 입력해주세요"
                        } else {
                            // error = null
                            textInputLayout.error = null
                        }
                    }
                }
            }
            
            button.run{
                setOnClickListener {
                    // 입력한 내용을 가져온다.
                    // val str1 = textInputEditText.text.toString()
                    // textView.text = str1

                    // TextInputLayout으로 부터 EditText를 추출하낟.
                    if(textInputLayout.editText != null) {
                        val str1 = textInputLayout.editText!!.text.toString()
                        textView.text = str1
                    }
                }
            }
        }
    }
}