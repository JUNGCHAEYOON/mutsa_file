package com.test.android75_preference_practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android75_preference_practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.run{
                setOnClickListener {
                    // Preferences 객체를 추출한다.
                    // 첫 번째 : 이름
                    // 두 번째 : MODE_APPEND - 기존 데이터에 추가 해준다.
                    //          MODE_PRIVATE - 기존 데이터를 삭제하고 새롭게 저장한다.

                    val pref = getSharedPreferences("data", MODE_PRIVATE)
                    // 데이터 저장을 위한 객체를 이용해 데이터를 설정한다.
                    val editor = pref.edit()
                    // 값들을 설정한다.
                    editor.putBoolean("data1", true)
                    editor.putFloat("data2", 11.11f)
                    editor.putInt("data3", 100)
                    editor.putLong("data4", 200L)
                    editor.putString("data5", "문자열 데이터")

                    val set1 = mutableSetOf<String>()
                    set1.add("문자열1")
                    set1.add("문자열2")
                    set1.add("문자열3")
                    editor.putStringSet("data6", set1)

                    // 설정한 값을 저장한다.
                    editor.commit();

                    textView.text = "저장완료"
                }
            }

            button2.setOnClickListener {
                val pref = getSharedPreferences("data", MODE_PRIVATE)

                // 저장한 데이터를 가져온다.
                val data1 = pref.getBoolean("data1", false)
                val data2 = pref.getFloat("data2", 0.0f)
                val data3 = pref.getInt("data3", 0)
                val data4 = pref.getLong("data4", 0L)
                val data5 = pref.getString("data5", null)
                val data6 = pref.getStringSet("data6", null)

                textView.text = "data1 : ${data1}\n"
                textView.append("data2 : ${data2}\n")
                textView.append("data3 : ${data3}\n")
                textView.append("data4 : ${data4}\n")
                textView.append("data5 : ${data5}\n")

                for(str1 in data6!!){
                    textView.append("data6 : ${str1}\n")
                }
            }
        }
    }
}