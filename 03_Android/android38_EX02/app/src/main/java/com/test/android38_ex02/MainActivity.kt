package com.test.android38_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android38_ex02.databinding.ActivityMainBinding
import com.test.android38_ex02.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    var studentInfoList = ArrayList<StudentInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            // 좌측버튼 추가
            buttonAdd.run{
                setOnClickListener{
                    // 입력화면 등장, 리싸이클러뷰 안보이기
                    lineaLayoutInput.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
            }

            // 입력
            lineaLayoutInput.run{
                editTextKorean.run{
                    setOnEditorActionListener { v, actionId, event ->
                        var name = editTextName.text.toString()
                        var age = editTextAge.text.toString().toInt()
                        var korean = editTextKorean.text.toString().toInt()

                        var studentInfo = StudentInfo(name, age, korean)
                        studentInfoList.add(studentInfo)

                        editTextName.setText("")
                        editTextAge.setText("")
                        editTextKorean.setText("")

                        // 포커스를 준다.
                        editTextName.requestFocus()
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(editTextName, 0)

                        false
                    }
                }
            }

            // 우측버튼 보기
            buttonShow.run{
                setOnClickListener{
                    // 리싸이클러뷰 등장, 입력화면 안보이기
                    recyclerView.visibility = View.VISIBLE
                    lineaLayoutInput.visibility = View.GONE

                    recyclerView.run{
                        adapter = RecyclerAdapter()
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }
        }
    }

    inner class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.ViewHolderClass>(){
        inner class ViewHolderClass(rowBinding : RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
            var textViewRowName : TextView
            var textViewRowAge : TextView
            var textViewRowKorean : TextView
            var buttonRowDelete : Button

            init{
                textViewRowName = rowBinding.textViewRowName
                textViewRowAge = rowBinding.textViewRowAge
                textViewRowKorean = rowBinding.textViewRowKorean
                buttonRowDelete = rowBinding.buttonRowDelete
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
            return studentInfoList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            var (name, age, korean) = studentInfoList[position]

            holder.textViewRowName.text = name
            holder.textViewRowAge.text = "${age}살"
            holder.textViewRowKorean.text = "${korean}점"

            holder.buttonRowDelete.run{
                setOnClickListener{
                    studentInfoList.removeAt(position)
                    val adapter = activityMainBinding.recyclerView.adapter as RecyclerAdapter
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    data class StudentInfo(var name : String, var age : Int, var korean : Int)
}