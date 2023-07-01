package com.test.android36_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android36_ex01.databinding.ActivityMainBinding
import com.test.android36_ex01.databinding.RowBinding
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    var infoList = ArrayList<Info>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            editTextKor.run{
                setOnEditorActionListener { v, actionId, event ->
                    var _name = editTextName.text.toString()
                    var _age = editTextAge.text.toString()
                    var _kor = editTextKor.text.toString()

                    var info = Info(_name, _age, _kor)
                    infoList.add(info)

                    editTextName.setText("")
                    editTextAge.setText("")
                    editTextKor.setText("")

                    recyclerView.run{
                        adapter = RecyclerAdapterClass()
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }

                    false
                }
            }
        }
    }
    inner class Info(var name : String, var age : String, var kor : String)

    inner class RecyclerAdapterClass : RecyclerView.Adapter<RecyclerAdapterClass.ViewHolderClass>(){
        inner class ViewHolderClass(rowBinding : RowBinding) : RecyclerView.ViewHolder(rowBinding.root), OnClickListener{
            lateinit var textViewNameRow : TextView
            lateinit var textViewAgeRow : TextView
            lateinit var textViewKorRow : TextView

            init {
                textViewNameRow = rowBinding.textViewNameRow
                textViewAgeRow = rowBinding.textViewAgeRow
                textViewKorRow = rowBinding.textViewKorRow
            }

            override fun onClick(v: View?) {
                //nothing
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
            var rowBinding = RowBinding.inflate(layoutInflater)
            val viewHolderClass = ViewHolderClass(rowBinding)

            rowBinding.root.setOnClickListener(viewHolderClass)

            return viewHolderClass
        }

        override fun getItemCount(): Int {
            return infoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            holder.textViewNameRow.text = infoList[position].name
            holder.textViewAgeRow.text = infoList[position].age
            holder.textViewKorRow.text = infoList[position].kor
        }
    }
}