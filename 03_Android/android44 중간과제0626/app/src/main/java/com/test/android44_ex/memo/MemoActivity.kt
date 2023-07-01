package com.test.android44_ex.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android44_ex.databinding.ActivityMemoBinding
import com.test.android44_ex.databinding.RowMemoBinding
import com.test.android44_ex.memo.memoChange.MemoChangeActivity

class MemoActivity : AppCompatActivity() {

    lateinit var activityMemoBinding: ActivityMemoBinding

    lateinit var categoryName : String
    var categoryPosition : Int = -1
    var memoTitleList = ArrayList<String>()
    var memoContentList = ArrayList<String>()

    lateinit var memoChangeActivityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMemoBinding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(activityMemoBinding.root)

        // 컨트랙트 생성
        val c1 = ActivityResultContracts.StartActivityForResult()

        // memoChangeActivity 콜백
        memoChangeActivityResultLauncher = registerForActivityResult(c1){
            if(it.resultCode == RESULT_OK){
                val newMemoTitleFrom = it.data?.getStringExtra("newMemoTitleFrom")!!
                val newMemoContentFrom = it.data?.getStringExtra("newMemoContentFrom")!!
                val newMemoPosition = it.data?.getIntExtra("newMemoPosition",-1)
                memoTitleList[newMemoPosition!!] = newMemoTitleFrom
                memoContentList[newMemoPosition!!] = newMemoContentFrom

                val adapter = activityMemoBinding.recyclerViewMemo.adapter as RecyclerAdapterMemoClass
                adapter.notifyDataSetChanged()
            }
        }

        activityMemoBinding.run {
            // 카테고리 제목
            textViewMemoTitle.run {
                categoryName = intent.getStringExtra("categoryName")!!
                categoryPosition = intent.getIntExtra("categoryPosition",-1)
                text = categoryName
            }

            // 추가 버튼
            buttonMemoNew.run {
                setOnClickListener {
                    val newMemoTitle = editTextMemoTitle.text.toString()
                    val newMemoContent = editTextMemoContent.text.toString()
                    memoTitleList.add(newMemoTitle)
                    memoContentList.add(newMemoContent)

                    val adapter = activityMemoBinding.recyclerViewMemo.adapter as RecyclerAdapterMemoClass
                    adapter.notifyDataSetChanged()

                    editTextMemoTitle.setText("")
                    editTextMemoContent.setText("")
                    editTextMemoTitle.requestFocus()
                }
            }

            // 뒤로가기 버튼
            buttonMemoBack.run {
                setOnClickListener {
                    val resultIntent = Intent()
                    resultIntent.putExtra("categoryNameFromMemoActivity", categoryName)
                    resultIntent.putExtra("categoryPositionFromMemoActivity",categoryPosition)
                    resultIntent.putExtra("memoTitleListFromMemoActivity", memoTitleList)
                    resultIntent.putExtra("memoContentListFromMemoActivity", memoContentList)
                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }

            // 리싸이클러뷰 연결
            recyclerViewMemo.run {
                memoTitleList = intent.getStringArrayListExtra("memoTitleList")!!
                memoContentList = intent.getStringArrayListExtra("memoContentList")!!

                adapter = RecyclerAdapterMemoClass()
                layoutManager = LinearLayoutManager(this@MemoActivity)
            }
        }
    }

    inner class RecyclerAdapterMemoClass : RecyclerView.Adapter<RecyclerAdapterMemoClass.ViewHolderClass>(){
        inner class ViewHolderClass(rowMemoBinding: RowMemoBinding) : RecyclerView.ViewHolder(rowMemoBinding.root){
            var textViewMemoTitle : TextView
            var buttonRowMemoChange : Button
            var buttonRowMemoDelete : Button

            init{
                textViewMemoTitle = rowMemoBinding.textViewRowMemo
                buttonRowMemoChange = rowMemoBinding.buttonRowMemoChange
                buttonRowMemoDelete = rowMemoBinding.buttonRowMemoDelete
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowMemoBinding = RowMemoBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowMemoBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            rowMemoBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return memoTitleList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewMemoTitle.text = memoTitleList[position]

            holder.textViewMemoTitle.run{
                setOnClickListener {
                    if(holder.buttonRowMemoChange.visibility == View.GONE){
                        holder.buttonRowMemoChange.visibility = View.VISIBLE
                        holder.buttonRowMemoDelete.visibility = View.VISIBLE
                    }else{
                        holder.buttonRowMemoChange.visibility = View.GONE
                        holder.buttonRowMemoDelete.visibility = View.GONE
                    }

                    // 메모 수정
                    holder.buttonRowMemoChange.run{
                        setOnClickListener {
                            val memoChangeIntent = Intent(this@MemoActivity, MemoChangeActivity::class.java)
                            memoChangeIntent.putExtra("memoTitle",memoTitleList[position])
                            memoChangeIntent.putExtra("memoContent",memoContentList[position])
                            memoChangeIntent.putExtra("memoPosition",position)
                            memoChangeActivityResultLauncher.launch(memoChangeIntent)
                        }
                    }

                    // 메모 삭제
                    holder.buttonRowMemoDelete.run{
                        setOnClickListener {
                            memoTitleList.removeAt(position)
                            memoContentList.removeAt(position)
                            val adapter = activityMemoBinding.recyclerViewMemo.adapter as RecyclerAdapterMemoClass
                            adapter.notifyDataSetChanged()
                        }
                    }

                }
            }
        }
    }
}