package com.test.android68_assets

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android68_assets.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.setOnClickListener {
                // Assets 폴더에 있는 파일과 연결된 스트림을 추출한다.
                val inputStream = assets.open("text/data.txt")
                val inputStreamReader = InputStreamReader(inputStream, "UTF-8")
                val bufferedReader = BufferedReader(inputStreamReader)

                var str:String? = null
                val stringBinding = StringBuffer()

                do{
                    str = bufferedReader.readLine()
                    if(str != null){
                        stringBinding.append("${str}\n")
                    }
                }while(str != null)

                bufferedReader.close()

                textView.text = stringBinding.toString()
            }

            button2.setOnClickListener {
                // Assets 폴더에 있는 폰트 파일을 이용해 폰트 객체를 생성한다.
                val typeFace = Typeface.createFromAsset(assets, "fonts/NanumPen.ttf")
                // TextView에 적용한다.
                textView.typeface = typeFace
                textView.textSize = 50.0f
            }
        }
    }
}









