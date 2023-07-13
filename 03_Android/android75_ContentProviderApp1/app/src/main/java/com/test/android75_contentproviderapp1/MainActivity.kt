package com.test.android75_contentproviderapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android75_contentproviderapp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            buttonSelectData.setOnClickListener {
                val sql = "select * from TestTable"

                val dbHelper = DBHelper(this@MainActivity)

                val cursor = dbHelper.writableDatabase.rawQuery(sql, null)

                textViewResult.text = ""

                while(cursor.moveToNext()){
                    val idx1 = cursor.getColumnIndex("idx")
                    val idx2 = cursor.getColumnIndex("textData")
                    val idx3 = cursor.getColumnIndex("intData")
                    val idx4 = cursor.getColumnIndex("doubleData")
                    val idx5 = cursor.getColumnIndex("dateData")

                    val idx = cursor.getInt(idx1)
                    val textData = cursor.getString(idx2)
                    val intData = cursor.getInt(idx3)
                    val doubleData = cursor.getDouble(idx4)
                    val dateData = cursor.getString(idx5)

                    textViewResult.append("idx : ${idx}\n")
                    textViewResult.append("textData : ${textData}\n")
                    textViewResult.append("intData : ${intData}\n")
                    textViewResult.append("doubleData : ${doubleData}\n")
                    textViewResult.append("dateData : ${dateData}\n\n")
                }

                dbHelper.close()
            }
        }
    }
}










