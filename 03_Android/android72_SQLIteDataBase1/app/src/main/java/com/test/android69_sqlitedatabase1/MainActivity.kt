package com.test.android69_sqlitedatabase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android69_sqlitedatabase1.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.setOnClickListener {
                // 현재 시간을 구해 년-월-일 양식의 문자열을 만들어준다.
                val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val now = sdf.format(Date())

                // 저장할 정보를 가지고 있는 객체를 생성한다.
                val obj1 = TestClass(0, "문자열1", 100, 11.11, now)
                DAO.insertData(this@MainActivity, obj1)

                val obj2 = TestClass(0, "문자열2", 200, 22.22, now)
                DAO.insertData(this@MainActivity, obj2)
            }

            button2.setOnClickListener {
                // 데이터를 가져온다.
                val dataList = DAO.selectAllData(this@MainActivity)

                // 출력
                textView.text = ""

                for(obj in dataList){
                    textView.append("idx : ${obj.idx}\n")
                    textView.append("textData : ${obj.textData}\n")
                    textView.append("intData : ${obj.intData}\n")
                    textView.append("doubleData : ${obj.doubleData}\n")
                    textView.append("dateData : ${obj.dateData}\n\n")
                }
            }

            button3.setOnClickListener {
                val obj = DAO.selectData(this@MainActivity, 1)

                textView.text = "idx : ${obj.idx}\n"
                textView.append("textData : ${obj.textData}\n")
                textView.append("intData : ${obj.intData}\n")
                textView.append("doubleData : ${obj.doubleData}\n")
                textView.append("dateData : ${obj.dateData}\n")
            }

            button4.setOnClickListener {
                val obj = DAO.selectData(this@MainActivity, 1)

                obj.textData = "새로운 문자열"

                DAO.updateData(this@MainActivity, obj)
            }

            button5.setOnClickListener {
                DAO.deleteData(this@MainActivity, 1)
            }
        }
    }
}

data class TestClass(var idx:Int,
                     var textData:String,
                     var intData :Int,
                     var doubleData:Double,
                     var dateData:String)