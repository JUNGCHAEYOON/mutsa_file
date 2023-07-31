package com.test.firebasetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.test.firebasetest.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            button.run{
                setOnClickListener {
                    val t1 = TestDataClass(100, "문자열1", true)
                    val t2 = TestDataClass(200, "문자열2", false)
                    val t3 = TestDataClass(300, "문자열3", true)

                    // firebase 객체를 생성한다.
                    val database = FirebaseDatabase.getInstance()
                    // TestData에 접근한다.
                    val testDataRef = database.getReference("TestData")

                    // 저장한다.
                    testDataRef.push().setValue(t1)
                    testDataRef.push().setValue(t2)
                    testDataRef.push().setValue(t3)
                }
            }

            button2.setOnClickListener {

                thread {
                    // firebase 객체를 생성한다.
                    val database = FirebaseDatabase.getInstance()
                    // TestData에 접근한다.
                    val testDataRef = database.getReference("TestData")

                    // 전부를 가져온다.
                    testDataRef.get().addOnCompleteListener{
                        textView.text = ""
                        // 가져온 데이터의 수 만큼 반복한다.
                        for(a1 in it.result.children){

                            // 데이터를 가져온다.
                            val data1 = a1.child("data1").value as Long
                            val data2 = a1.child("data2").value as String
                            val data3 = a1.child("data3").value as Boolean

                            textView.append("data1 : ${data1}\n")
                            textView.append("data2 : ${data2}\n")
                            textView.append("data3 : ${data3}\n\n")
                        }
                    }
                }
            }

            button3.run{
                setOnClickListener {

                    // firebase 객체를 생성한다.
                    val database = FirebaseDatabase.getInstance()
                    // TestData에 접근한다.
                    val testDataRef = database.getReference("TestData")

                    // data1이 200인 것만 가져온다.
                    // orderByChild("data1") : testDataRef안에 있는 객체 안의 데이터 이름을 설정한다.
                    // equalTo : 같은것
                    // 특정 이름의 값을 통해 데이터를 검색하려면
                    // firebase database의 규칙에 .indexOn 설정을 해줘야 한다.

                    // equalTo : 같은 것
                    // endAt : 지정한 값보다 작거나 같은 것
                    // endBefore : 지정한 값보다 작은 것
                    // startAt : 지정한 값보다 크거나 같은 것
                    // startAfter : 지정한 값보다 큰것
                    testDataRef.orderByChild("data1").equalTo(200.0).get().addOnCompleteListener { 
                        textView.text = ""
                        // 가져온 데이터의 수 만큼 반복한다.
                        for(a1 in it.result.children){

                            // 데이터를 가져온다.
                            val data1 = a1.child("data1").value as Long
                            val data2 = a1.child("data2").value as String
                            val data3 = a1.child("data3").value as Boolean

                            textView.append("data1 : ${data1}\n")
                            textView.append("data2 : ${data2}\n")
                            textView.append("data3 : ${data3}\n\n")
                        }
                    }
                }
            }
        }
    }
}

// 데이터 클래스
data class TestDataClass(var data1:Long, var data2:String, var data3:Boolean)






