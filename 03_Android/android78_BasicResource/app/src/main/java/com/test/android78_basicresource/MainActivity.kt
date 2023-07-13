package com.test.android78_basicresource

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android78_basicresource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        activityMainBinding.run{
            button.setOnClickListener {
                // textView의 문자열을 직접 설정한다.
                // textView.text = "새로운 문자열"

                // res/values 폴더에 있는 문자열을 가지고 온다.
                // res 폴더 -> R
                // val str1 = resources.getString(R.string.str2)
                // textView.text = str1

                // val str1 = getString(R.string.str2)
                // textView.text = str1

                textView.setText(R.string.str2)
            }

            button2.setOnClickListener {
                // 문자열을 가져온다.
                val str1 = getString(R.string.str3)
                // % 부분에 값을 설정하여 문자열을 완성한다.
                val str2 = String.format(str1, "홍길동", 30)

                textView.text = str2
            }

            button3.setOnClickListener {
                // 문자열 배열을 가져온다.
                val str4Array = resources.getStringArray(R.array.str4_array)
                textView.text = ""

                for(str1 in str4Array){
                    textView.append("${str1}\n")
                }
            }

            button4.setOnClickListener {
                // 사전 정의되어 있는 색상값 사용
                // textView.setTextColor(Color.RED)

                // RGB지정 (R - RED, G - Green, B - Blue, 빛의 삼원색)
                // val c1 = Color.rgb(227, 30, 89)
                // textView.setTextColor(c1)

                // ARGB 지정(A - alpha, R - Red, G - Green, B - Blue, 투명색과 빛의 삼원색)
                // val c2 = Color.argb(50, 227, 30, 89)
                // textView.setTextColor(c2)

                // context가 제공하는 메서드를 통해 색상값을 가져온다.
                val c3 = getColor(R.color.color4)
                textView.setTextColor(c3)
            }

            button5.setOnClickListener {
                // 크기 값들을 가져온다.
                val px = resources.getDimension(R.dimen.px)
                val dp = resources.getDimension(R.dimen.dp)
                val sp = resources.getDimension(R.dimen.sp)
                val inch = resources.getDimension(R.dimen.inch)
                val mm = resources.getDimension(R.dimen.mm)
                val pt = resources.getDimension(R.dimen.pt)

                textView.text = "1px = ${px}px\n"
                textView.append("1dp = ${dp}px\n")
                textView.append("1sp = ${sp}px\n")
                textView.append("1inch = ${inch}px\n")
                textView.append("1mm = ${mm}px\n")
                textView.append("1pt = ${pt}px\n")

                textView.textSize = 15 * sp
            }
        }

        setContentView(activityMainBinding.root)
    }
}