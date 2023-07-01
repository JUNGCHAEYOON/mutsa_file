package com.test.android44_ex.categoryChange

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android44_ex.databinding.ActivityCategoryChangeBinding

class CategoryChangeActivity : AppCompatActivity() {

    lateinit var activityCategoryChangeBinding: ActivityCategoryChangeBinding

    var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityCategoryChangeBinding = ActivityCategoryChangeBinding.inflate(layoutInflater)
        setContentView(activityCategoryChangeBinding.root)

        activityCategoryChangeBinding.run{
            textViewCCName.run{
                text = intent.getStringExtra("oldCategoryName")!!
                position = intent.getIntExtra("position",-1)
            }
            // 변경버튼
            buttonCCChange.run{
                setOnClickListener {
                    val newCategoryName = editTextCCNewName.text.toString()

                    val resultIntent = Intent()
                    resultIntent.putExtra("newCategoryName",newCategoryName)
                    resultIntent.putExtra("position",position)

                    setResult(RESULT_OK,resultIntent)
                    finish()
                }
            }

            // 취소버튼
            buttonCCCancel.run{
                setOnClickListener {
                    val newCategoryName = activityCategoryChangeBinding.textViewCCName.text.toString()

                    val resultIntent = Intent()
                    resultIntent.putExtra("newCategoryName",newCategoryName)
                    resultIntent.putExtra("position",position)

                    setResult(RESULT_OK,resultIntent)
                    finish()
                }
            }
        }
    }
}