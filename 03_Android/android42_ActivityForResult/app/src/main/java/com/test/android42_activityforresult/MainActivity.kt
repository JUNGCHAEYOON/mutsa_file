package com.test.android42_activityforresult

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.test.android42_activityforresult.databinding.ActivityFifthBinding
import com.test.android42_activityforresult.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // Activity를 구분하기 위해 사용하는 값
    val SECOND_ACTIVITY = 0
    val THIRD_ACTIVITY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            // 계약 객체를 생성한다.
            // 다른 Activity를 갔다 돌아 왔을 경우
            val c1 = ActivityResultContracts.StartActivityForResult()
            val fourthActivityLauncher =  registerForActivityResult(c1){
                // FourthActivity를 갔다 돌아왔을때의 코드를 구현한다.
                textView.text = "FourthActivity를 갔다 돌아왔습니다\n"

                // Result Code로 분기한다.
                if(it.resultCode == RESULT_OK){
                    // Intent를 통해 값들을 추출한다.
                    val value1 = it.data?.getIntExtra("value1", 0)
                    val value2 = it.data?.getDoubleExtra("value2", 0.0)

                    textView.append("value1 : ${value1}\n")
                    textView.append("value2 : ${value2}")
                }
            }

            val c2 = ActivityResultContracts.StartActivityForResult()
            val fifthActivityLauncher = registerForActivityResult(c2){
                // FifthActivity를 갔다 돌아왔을 때의 코드를 구현한다.
                textView.text = "FifthActivity를 갔따 돌아왔습니다\n"
            }


            button.run{
                setOnClickListener {
                    // SecondActivity 실행
                    val secondIntent = Intent(this@MainActivity, SecondActivity::class.java)
                    // startActivity(secondIntent)
                    
                    // 값들을 설정한다.
                    secondIntent.putExtra("data1", 100)
                    secondIntent.putExtra("data2", 11.11)
                    secondIntent.putExtra("data3", true)
                    secondIntent.putExtra("data4", "안녕하세요")

                    val t1 = TestClass()
                    t1.name = "홍길동"
                    t1.age = 100
                    secondIntent.putExtra("data5", t1)
                    
                    startActivityForResult(secondIntent, SECOND_ACTIVITY)
                }
            }

            button2.run{
                setOnClickListener {
                    // ThirdActivity 실행
                    val thirdIntent = Intent(this@MainActivity, ThirdActivity::class.java)
                    // startActivity(thirdIntent)
                    startActivityForResult(thirdIntent, THIRD_ACTIVITY)
                }
            }

            button3.run {
                setOnClickListener {
                    // FourthActivity 실행
                    val fourthIntent = Intent(this@MainActivity, FourthActivity::class.java)

                    fourthIntent.putExtra("data1", 100)
                    fourthIntent.putExtra("data2", 11.11)

                    fourthActivityLauncher.launch(fourthIntent)
                }
            }

            button4.run{
                setOnClickListener {
                    // FifthActivity 실행
                    val fifthIntent = Intent(this@MainActivity, FifthActivity::class.java)
                    fifthActivityLauncher.launch(fifthIntent)
                }
            }
        }
    }

    // startActivityForResult 메서드로 다른 Activity를 실행하고 다시 돌아왔을 때 자동으로 호출되는 메서드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // requestCode로 어떤 Activity를 갔다 왔는지 구분한다.
        when(requestCode){
            SECOND_ACTIVITY -> {
                activityMainBinding.textView.text = "SecondActivity에서 돌아왔습니다\n"
                // resultCode로 작업의 결과를 구분한다.
                when(resultCode){
                    RESULT_OK -> {
                        activityMainBinding.textView.append("작업 성공\n")
                        // 3번째 매개변수로 전달되는 Intent 객체로 부터 데이터를 추출해 사용한다.
                        if(data != null){
                            val value1 = data.getIntExtra("value1", 0)
                            val value2 = data.getDoubleExtra("value2", 0.0)
                            val value3 = data.getBooleanExtra("value3", false)
                            val value4 = data.getStringExtra("value4")

                            activityMainBinding.textView.append("value1 : ${value1}\n")
                            activityMainBinding.textView.append("value2 : ${value2}\n")
                            activityMainBinding.textView.append("value3 : ${value3}\n")
                            activityMainBinding.textView.append("value4 : ${value4}\n")
                        }
                    }
                    RESULT_CANCELED -> {
                        activityMainBinding.textView.append("작업 취소\n")
                    }
                }
            }
            THIRD_ACTIVITY -> {
                activityMainBinding.textView.text = "ThirdActivity에서 돌아왔습니다\n"
            }
        }
    }
}

// Parcelable
// 안드로이드에서 4대 구성요소 간에 객체를 전달하기 위한 직렬화를 수행할 수 있다.
class TestClass() : Parcelable{

    lateinit var name:String
    var age:Int = 0

    constructor(parcel: Parcel) : this() {
        // 멤버 변수에 값을 담는다.
        // Parcel에 저장한 순서대로 추출한다.
        name = parcel.readString()!!
        age = parcel.readInt()
    }

    // 객체를 Intent에 넣으려고 할 때 호출된다.
    // 매개 변수로 전달되는 parcel 객체에 객체 복원에 필요한 값들을 넣어준다.
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TestClass> {
        // Intent로 부터 객체를 추출할 때 호출되는 메서드
        // 새로운 객체를 생성하고 parcel 에 저장되어 있는 값을 객체 맴버 변수에 담아준다
        override fun createFromParcel(parcel: Parcel): TestClass {
            return TestClass(parcel)
        }

        override fun newArray(size: Int): Array<TestClass?> {
            return arrayOfNulls(size)
        }
    }
}











