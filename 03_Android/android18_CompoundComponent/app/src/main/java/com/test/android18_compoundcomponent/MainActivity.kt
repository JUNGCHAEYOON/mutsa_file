package com.test.android18_compoundcomponent

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android18_compoundcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.run{
                setOnClickListener {
                    // 체크 상태를 true로 설정한다.
                    checkBox.isChecked = true
                    checkBox2.isChecked = true
                    checkBox3.isChecked = true

                    // 라디오 그룹에서 하나를 선택한다.
                    // 매개변수로 선택할 라디오 버튼의 id를 설정해준다.
                    radioGroup1.check(R.id.radioButton3)
                    radioGroup2.check(R.id.radioButton6)
                }
            }

            button2.run{
                setOnClickListener {
                    // 체크 상태를 false로 설정한다.
                    checkBox.isChecked = false
                    checkBox2.isChecked = false
                    checkBox3.isChecked = false
                }
            }

            button3.run{
                setOnClickListener {
                    // 체크 상태를 반전한다.
                    checkBox.toggle()
                    checkBox2.toggle()
                    checkBox3.toggle()
                }
            }

            button4.run{
                setOnClickListener {
                    textView.text = ""

                    if(checkBox.isChecked == true){
                        textView.append("첫 번째 체크박스는 체크 되어 있습니다\n")
                    } else {
                        textView.append("첫 번째 체크박스는 체크 되어 있지 않습니다\n")
                    }

                    if(checkBox2.isChecked == true){
                        textView.append("두 번째 체크박스는 체크 되어 있습니다\n")
                    } else {
                        textView.append("두 번째 체크박스느 체크 되어 있지 않습니다\n")
                    }

                    if(checkBox3.isChecked == true){
                        textView.append("세 번째 체크박스는 체크 되어 있습니다\n")
                    } else {
                        textView.append("세 번째 체크박스는 체크 되어 있지 않습니다\n")
                    }

                    // 라디오 그룹을 통해 선택되어 있는 라디오 버튼의 id를 가져온다.
                    when(radioGroup1.checkedRadioButtonId){
                        R.id.radioButton -> {
                            textView.append("라디오 1-1 선택\n")
                        }
                        R.id.radioButton2 -> {
                            textView.append("라디오 1-2 선택\n")
                        }
                        R.id.radioButton3 -> {
                            textView.append("라디오 1-3 선택\n")
                        }
                    }

                    when(radioGroup2.checkedRadioButtonId){
                        R.id.radioButton4 -> {
                            textView.append("라디오 2-1 선택\n")
                        }
                        R.id.radioButton5 ->{
                            textView.append("라디오 2-2 선택\n")
                        }
                        R.id.radioButton6 ->{
                            textView.append("라디오 2-3 선택\n")
                        }
                    }

                }
            }
            
            checkBox.run{
                // 체크박스의 선택상태가 변경되었을 때 동작한다.
                // isChecked 안에 선택 상태에 대한 값이 전달된다.
                setOnCheckedChangeListener { buttonView, isChecked -> 
                    if(isChecked == true){
                        textView.text = "첫 번째 체크박스가 체크 되었습니다"
                    } else {
                        textView.text = "첫 번째 체크박스가 체크 해제 되었습니다"
                    }
                }
            }
            
            radioGroup1.run{
                // 라디오 그룹내의 라디오 버튼 선택이 변경될 경우...
                // checkedId : 선택된 라디오 버튼의 ID가 전달된다.
                setOnCheckedChangeListener { group, checkedId ->  
                    when(checkedId){
                        R.id.radioButton ->{
                            textView.text = "라디오 1-1 선택"
                        } 
                        R.id.radioButton2 ->{
                            textView.text = "라디오 1-2 선택"
                        }
                        R.id.radioButton3 ->{
                            textView.text = "라이도 1-3 선택"
                        }
                    }
                }
            }
        }
    }
}