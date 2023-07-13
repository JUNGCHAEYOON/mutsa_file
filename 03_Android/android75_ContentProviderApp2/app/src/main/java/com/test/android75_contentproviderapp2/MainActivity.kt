package com.test.android75_contentproviderapp2

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android75_contentproviderapp2.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        activityMainBinding.run {

            buttonInsertData.run{
                setOnClickListener {

                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val now = sdf.format(Date())

                    // 저장할 데이터를 ContentValues에 담아 준다.
                    val cv1 = ContentValues()
                    cv1.put("textData", "문자열1")
                    cv1.put("intData", 100)
                    cv1.put("doubleData", 11.11)
                    cv1.put("dateData", now)

                    val cv2 = ContentValues()
                    cv2.put("textData", "문자열2")
                    cv2.put("intData", 200)
                    cv2.put("doubleData", 22.22)
                    cv2.put("dateData", now)

                    // Content Provider의 이름을 가지고 있는 Uri 객체를 생성한다.
                    val uri = Uri.parse("content://com.test.dbprovider")
                    
                    // Content Provider를 이용할 수 있는 객체를 통해 사용한다.
                    contentResolver.insert(uri, cv1)
                    contentResolver.insert(uri, cv2)
                    
                    textViewResult.text = "저장 완료"
                }
            }

            buttonSelectData.run{
                setOnClickListener {

                    val uri = Uri.parse("content://com.test.dbprovider")
                    // Content Provider를 통해서 데이터를 가져온다.

                    // 두 번째 : 가져올 컬럼 목록. null을 설정하면 모든 컬럼
                    // 세 번째 : 조건절
                    // 네 번째 : 조건절의 ?에 설정된 값 배열
                    // 다섯 번째 : 정렬 기준 컬럼 목록
                    val cursor = contentResolver.query(uri, null, null, null, null)

                    textViewResult.text = ""

                    while(cursor?.moveToNext()!!){
                        val idx1 = cursor?.getColumnIndex("idx")
                        val idx2 = cursor?.getColumnIndex("textData")
                        val idx3 = cursor?.getColumnIndex("intData")
                        val idx4 = cursor?.getColumnIndex("doubleData")
                        val idx5 = cursor?.getColumnIndex("dateData")

                        val idx = cursor.getInt(idx1!!)
                        val textData = cursor.getString(idx2!!)
                        val intData = cursor.getInt(idx3!!)
                        val doubleData = cursor.getDouble(idx4!!)
                        val dateData = cursor.getString(idx5!!)

                        textViewResult.append("idx : ${idx}\n")
                        textViewResult.append("textData : ${textData}\n")
                        textViewResult.append("intData : ${intData}\n")
                        textViewResult.append("doubleData : ${doubleData}\n")
                        textViewResult.append("dateData : ${dateData}\n\n")
                    }
                }
            }
            
            buttonUpdateData.run{
                setOnClickListener { 
                    
                    val cv1 = ContentValues()
                    cv1.put("textData", "새로운 문자열")
                    
                    val where = "idx = ?"
                    val args = arrayOf("1")

                    val uri = Uri.parse("content://com.test.dbprovider")
                    
                    // 수정한다.
                    contentResolver.update(uri, cv1, where, args)
                    
                    textViewResult.text = "수정 완료"
                }
            }

            buttonDeleteData.run{
                setOnClickListener {

                    val where = "idx = ?"
                    val args = arrayOf("1")

                    val uri = Uri.parse("content://com.test.dbprovider")

                    contentResolver.delete(uri, where, args)

                    textViewResult.text = "삭제 완료"
                }
            }
        }

        setContentView(activityMainBinding.root)
    }
}









