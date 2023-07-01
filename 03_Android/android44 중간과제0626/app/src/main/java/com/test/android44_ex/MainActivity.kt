package com.test.android44_ex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android44_ex.add.AddActivity
import com.test.android44_ex.categoryChange.CategoryChangeActivity
import com.test.android44_ex.memo.MemoActivity
import com.test.android44_ex.databinding.ActivityMainBinding
import com.test.android44_ex.databinding.RowCategoryBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // addActivity 런쳐
    lateinit var addActivityResultLauncher : ActivityResultLauncher<Intent>
    // categoryActivity 런쳐
    lateinit var categoryActivityResultLauncher : ActivityResultLauncher<Intent>
    // memoActivity 런쳐
    lateinit var memoActivityResultLauncher: ActivityResultLauncher<Intent>

    // 카테고리 객체를 집어넣을 리스트
    var categoryList = ArrayList<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)
        
        // 컨트랙트 생성
        val c1 = ActivityResultContracts.StartActivityForResult()
        
        // 추가 액티비티 콜백
        addActivityResultLauncher = registerForActivityResult(c1){
            if(it.resultCode == RESULT_OK){
                // addActivity 에서 넘어온 경우
                val categoryName = it.data?.getStringExtra("categoryName")
                val memoList = ArrayList<Memo>()
                val t1 = Category(categoryName!!, memoList)
                categoryList.add(t1)

                //리싸이클러뷰 연결
                val adapter = activityMainBinding.recyclerViewMain.adapter as RecyclerAdapterClass
                adapter.notifyDataSetChanged()
            }
        }
        
        // 수정 액티비티 콜백
        categoryActivityResultLauncher = registerForActivityResult(c1){
            if(it.resultCode == RESULT_OK){
                //categoryChangeActivity 에서 넘어온 경우
                val newCategoryName = it.data?.getStringExtra("newCategoryName")
                val position = it.data?.getIntExtra("position",-1)
                if(position != -1){
                    if (newCategoryName != null) {
                        categoryList[position!!].categoryName = newCategoryName
                    }
                }

                //리싸이클러뷰 연결
                val adapter = activityMainBinding.recyclerViewMain.adapter as RecyclerAdapterClass
                adapter.notifyDataSetChanged()
            }
        }
        
        // 메모 액티비티 콜백
        memoActivityResultLauncher = registerForActivityResult(c1){
            if(it.resultCode == RESULT_OK){
                val categoryNameFromMemoActivity = it.data?.getStringExtra("categoryNameFromMemoActivity")!!
                val categoryPositionFromMemoActivity = it.data?.getIntExtra("categoryPositionFromMemoActivity",-1)!!
                val memoTitleListFromMemoActivity = it.data?.getStringArrayListExtra("memoTitleListFromMemoActivity")!!
                val memoContentListFromMemoActivity = it.data?.getStringArrayListExtra("memoContentListFromMemoActivity")!!

                categoryList[categoryPositionFromMemoActivity].categoryName = categoryNameFromMemoActivity
                categoryList[categoryPositionFromMemoActivity].memoList.clear()
                for(index in 0 .. memoTitleListFromMemoActivity.size - 1){
                    val memo = Memo(memoTitleListFromMemoActivity[index], memoContentListFromMemoActivity[index])
                    categoryList[categoryPositionFromMemoActivity].memoList.add(memo)
                }
            }
        }

        activityMainBinding.run{
            recyclerViewMain.run{
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    // 액션바 버튼
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    // 액션바 메뉴 클릭 리스너
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.mainMenuAdd ->{

                // AddActivity 실행하기
                val addIntent = Intent(this@MainActivity, AddActivity::class.java)
                addActivityResultLauncher.launch(addIntent)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerAdapterClass : RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>(){
        inner class ViewHolderClass (rowCategoryBinding :RowCategoryBinding) : RecyclerView.ViewHolder(rowCategoryBinding.root){
            var textViewRow1 : TextView
            var buttonRowChange : Button
            var buttonRowDelete : Button
            var buttonRowNewMemo : Button

            init{
                textViewRow1 = rowCategoryBinding.textViewRow1
                buttonRowChange = rowCategoryBinding.buttonRowChange
                buttonRowDelete = rowCategoryBinding.buttonRowDelete
                buttonRowNewMemo = rowCategoryBinding.buttonRowNewMemo
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowCategoryBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

            rowBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return categoryList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewRow1.text = "${categoryList[position].categoryName}"

            holder.textViewRow1.run{
                setOnClickListener {
                    // 한번 클릭시 수정삭제, 한번더 누르면 다시 없어짐
                    if(holder.buttonRowChange.visibility == View.GONE){
                        holder.buttonRowNewMemo.visibility = View.VISIBLE
                        holder.buttonRowChange.visibility = View.VISIBLE
                        holder.buttonRowDelete.visibility = View.VISIBLE
                    }else{
                        holder.buttonRowNewMemo.visibility = View.GONE
                        holder.buttonRowChange.visibility = View.GONE
                        holder.buttonRowDelete.visibility = View.GONE
                    }

                    // 메모등록
                    holder.buttonRowNewMemo.run{
                        setOnClickListener {
                            // 메모리스트를, 메모제목리스트, 메모내용리스트로 분리하여 넘겨줌
                            val memoIntent = Intent(this@MainActivity, MemoActivity::class.java)
                            memoIntent.putExtra("categoryName", categoryList[position].categoryName)
                            memoIntent.putExtra("categoryPosition", position)

                            var memoTitleList = ArrayList<String>()
                            var memoContentList = ArrayList<String>()

                            for(memo in categoryList[position].memoList){
                                memoTitleList.add(memo.memoTitle)
                                memoContentList.add(memo.memoContent)
                            }

                            memoIntent.putExtra("memoTitleList",memoTitleList)
                            memoIntent.putExtra("memoContentList", memoContentList)
                            memoActivityResultLauncher.launch(memoIntent)
                        }
                    }

                    // 수정
                    holder.buttonRowChange.run{
                        setOnClickListener {
                            val categoryChangeIntent = Intent(this@MainActivity, CategoryChangeActivity::class.java)
                            categoryChangeIntent.putExtra("oldCategoryName",categoryList[position].categoryName)
                            categoryChangeIntent.putExtra("position",position)
                            categoryActivityResultLauncher.launch(categoryChangeIntent)
                        }
                    }

                    // 삭제
                    holder.buttonRowDelete.run{
                        setOnClickListener {
                            categoryList.removeAt(position)
                            val adapter = activityMainBinding.recyclerViewMain.adapter as RecyclerAdapterClass
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }
}