package com.test.android45_app2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.test.android45_app2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val c1 = ActivityResultContracts.StartActivityForResult()
        val activityLauncher = registerForActivityResult(c1){
            val value1 = it.data?.getIntExtra("value1", 0)
            val value2 = it.data?.getStringExtra("value2")

            activityMainBinding.run{
                textView.run{
                    text = "value1 : ${value1}\n"
                    append("value2 : ${value2}")
                }
            }
        }

        activityMainBinding.run{
            button.run{
                setOnClickListener {
                    // 다른 애플리케이션의 Activity에 붙혀준 이름을 지정하여 Intent를 생성한다.
                    // Intent Filter의 Action name을 넣어준다.
                    val newIntent = Intent("com.test.android45_third_activity")

                    newIntent.putExtra("data1", 100)
                    newIntent.putExtra("data2", "안녕하세요")

                    // startActivity(newIntent)
                    activityLauncher.launch(newIntent)
                }
            }

            buttonShowMap.run{
                setOnClickListener {
                    // 위도와 경도를 문자열로 만들어준다.
                    // val address = "geo:37.243243,131.861601"
                    // val uri = Uri.parse(address)
                    // val newIntent = Intent(Intent.ACTION_VIEW, uri)

                    // 웹사이트
                    val address = "http://developer.android.com"
                    val uri = Uri.parse(address)
                    val newIntent = Intent(Intent.ACTION_VIEW, uri)

                    startActivity(newIntent)
                }
            }
        }
    }
}