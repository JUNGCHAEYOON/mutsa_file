package com.test.android44_ex.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android44_ex.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {

    lateinit var activityAddBinding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityAddBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(activityAddBinding.root)

        activityAddBinding.run {
            buttonAddAdd.run{
                setOnClickListener{
                    val categoryName = editTextAdd1.text.toString()
                    val resultIntent = Intent()
                    resultIntent.putExtra("categoryName",categoryName)

                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }

            buttonAddBack.run{
                setOnClickListener {
                    finish()
                }
            }
        }
    }
}