package com.test.android48_ex01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android48_ex01.databinding.ActivityMainBinding
import com.test.android48_ex01.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val tempData = arrayOf(
        "홍길동", "김길동", "최길동", "박길동", "고길동"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            recyclerViewInfo.run{
                adapter = RecyclerAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            buttonAdd.setOnClickListener {
                val inputIntent = Intent(this@MainActivity, InputActivity::class.java)
                startActivity(inputIntent)
            }
        }
    }

    inner class RecyclerAdapterClass : RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>(){

        inner class ViewHolderClass(rowBinding: RowBinding) : RecyclerView.ViewHolder(rowBinding.root){

            var textViewRow:TextView

            init {
                textViewRow = rowBinding.textViewRow
                rowBinding.root.setOnClickListener {
                    val resultIntent = Intent(this@MainActivity, ResultActivity::class.java)
                    resultIntent.putExtra("position", adapterPosition)
                    startActivity(resultIntent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            val rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            val params = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
            rowBinding.root.layoutParams = params

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return DataClass.humanList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewRow.text = DataClass.humanList[position].name
        }
    }

    override fun onResume() {
        super.onResume()

        activityMainBinding.recyclerViewInfo.adapter?.notifyDataSetChanged()
    }
}



















