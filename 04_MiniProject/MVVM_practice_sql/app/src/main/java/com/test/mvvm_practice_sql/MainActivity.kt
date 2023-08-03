package com.test.mvvm_practice_sql

import android.content.Intent
import android.inputmethodservice.Keyboard.Row
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDivider
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.test.mvvm_practice_sql.databinding.ActivityMainBinding
import com.test.mvvm_practice_sql.databinding.RowBinding
import com.test.mvvm_practice_sql.vm.ViewModelTest1
import com.test.mvvm_practice_sql.vm.ViewModelTest2

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 뷰모델에서 사용할 context 객체
    companion object{
        lateinit var mainActivity: MainActivity
    }

    // 뷰모델 객체
    lateinit var viewModelTest1 : ViewModelTest1
    lateinit var viewModelTest2 : ViewModelTest2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        // 뷰모델에서 사용할 context 객체 초기화
        mainActivity = this

        // 뷰모델 객체 받아오기
        viewModelTest1 = ViewModelProvider(this@MainActivity)[ViewModelTest1::class.java]
        viewModelTest2 = ViewModelProvider(this@MainActivity)[ViewModelTest2::class.java]

        activityMainBinding.run{
            buttonMain.setOnClickListener {
                val newIntent = Intent(this@MainActivity, AddActivity::class.java)
                startActivity(newIntent)
            }

            recyclerViewMain.run{
                adapter = MainRecyclerAdapter()
                layoutManager = LinearLayoutManager(this@MainActivity)
                addItemDecoration(MaterialDividerItemDecoration(this@MainActivity, MaterialDividerItemDecoration.VERTICAL))
            }

            // onResume 에서 datalist 값 변경시 observe 함수가 인지하여 recyclerview change
            viewModelTest2.dataList.observe(this@MainActivity){
                recyclerViewMain.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // 뷰모델 동기화 화면이 메인으로 돌아올때 데이터 만 바꿔주면 뷰에 반영됨
        viewModelTest2.getAll()
    }

    inner class MainRecyclerAdapter : RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder>(){
        inner class MainViewHolder(rowBinding : RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
            var textViewRow : TextView

            init{
                textViewRow = rowBinding.textViewRow

                rowBinding.root.setOnClickListener{
                    val newIntent = Intent(this@MainActivity, ResultActivity::class.java)

                    val t1 = viewModelTest2.dataList.value?.get(adapterPosition)

                    newIntent.putExtra("testIdx",t1?.idx)

                    startActivity(newIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val mainViewHolder = MainViewHolder(rowBinding)

            rowBinding.root.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            return mainViewHolder
        }

        override fun getItemCount(): Int {
            return viewModelTest2.dataList.value?.size!!
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.textViewRow.text = viewModelTest2.dataList.value?.get(position)?.data1
        }
    }
}