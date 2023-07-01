package com.test.android45_startactivityexport

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import com.test.android45_startactivityexport.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    lateinit var activityThirdBinding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityThirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(activityThirdBinding.root)

        activityThirdBinding.run{

            val data1 = intent.getIntExtra("data1", 0)
            val data2 = intent.getStringExtra("data2")

            textViewResult1.text = "data1 : ${data1}\n"
            textViewResult1.append("data2 : ${data2}")

            button.run{
                setOnClickListener {
                    val resultIntent = Intent()
                    resultIntent.putExtra("value1", 200)
                    resultIntent.putExtra("value2", "감사합니다")

                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }
}