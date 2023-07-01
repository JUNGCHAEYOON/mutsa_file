package com.test.android44_ex.memo.memoChange

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android44_ex.Memo
import com.test.android44_ex.R
import com.test.android44_ex.databinding.ActivityMemoBinding
import com.test.android44_ex.databinding.ActivityMemoChangeBinding

class MemoChangeActivity : AppCompatActivity() {

    lateinit var activityMemoChangeBinding: ActivityMemoChangeBinding

    lateinit var memoTitle : String
    lateinit var memoContent : String
    var memoPosition : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMemoChangeBinding = ActivityMemoChangeBinding.inflate(layoutInflater)
        setContentView(activityMemoChangeBinding.root)

        activityMemoChangeBinding.run{
            // 받아온 데이터
            memoTitle = intent.getStringExtra("memoTitle")!!
            memoContent = intent.getStringExtra("memoContent")!!
            memoPosition = intent.getIntExtra("memoPosition",-1)

            textViewMemoChangeTitle.text = memoTitle
            textViewMemoChangeContent.text = memoContent

            // 수정
            buttonMemoChangeChange.run{
                setOnClickListener {
                    memoTitle = editTextNewMemoChangeTitle.text.toString()
                    memoContent = editTextNewMemoChangeContent.text.toString()

                    textViewMemoChangeTitle.text = memoTitle
                    textViewMemoChangeContent.text = memoContent

                    editTextNewMemoChangeTitle.setText("")
                    editTextNewMemoChangeContent.setText("")
                }
            }

            // 뒤로가기
            buttonMemoChangeBack.run{
                setOnClickListener {
                    val resultIntent = Intent()
                    resultIntent.putExtra("newMemoTitleFrom",memoTitle)
                    resultIntent.putExtra("newMemoContentFrom",memoContent)
                    resultIntent.putExtra("newMemoPosition",memoPosition)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }
        }
    }
}