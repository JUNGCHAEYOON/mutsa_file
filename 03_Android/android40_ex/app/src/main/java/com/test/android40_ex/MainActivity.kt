package com.test.android40_ex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.android40_ex.databinding.ActivityMainBinding
import com.test.android40_ex.databinding.RowBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    data class StudentInfo(var name:String, var age:Int, var korean:Int)
    val dataList = mutableListOf<StudentInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            // 초기에는 둘다 안보이게
            linearLayout1.visibility = View.GONE
            linearLayout2.visibility = View.GONE

            editTextKorean.run{
                setOnEditorActionListener { v, actionId, event ->
                    val name = editTextName.text.toString()
                    val age = editTextAge.text.toString().toInt()
                    val korean = editTextKorean.text.toString().toInt()

                    val studentInfo = StudentInfo(name, age, korean)
                    dataList.add(studentInfo)

                    editTextName.setText("")
                    editTextAge.setText("")
                    editTextKorean.setText("")

                    editTextName.requestFocus()

                    false
                }
            }

            recyclerViewResult.run{
                adapter = RecyclerViewAdapterClass()
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    // option menu 구성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }
    
    // 액션바 메서드
    // option menu를 선택하면 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menuItemShowInput->{
                activityMainBinding.linearLayout1.visibility = View.VISIBLE
                activityMainBinding.linearLayout2.visibility = View.GONE
                
                // 시작시 requestFocus 주기
                activityMainBinding.editTextName.requestFocus()
                thread {
                    SystemClock.sleep(100)
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(currentFocus, 0)
                }

            }
            R.id.menuItemShowResult->{
                activityMainBinding.linearLayout1.visibility = View.GONE
                activityMainBinding.linearLayout2.visibility = View.VISIBLE

                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                currentFocus?.clearFocus()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    inner class RecyclerViewAdapterClass : RecyclerView.Adapter<RecyclerViewAdapterClass.ViewHolderClass>(){
        inner class ViewHolderClass(rowBinding : RowBinding) : RecyclerView.ViewHolder(rowBinding.root){
            var textViewRow1 : TextView
            var textViewRow2 : TextView
            var textViewRow3 : TextView

            init{
                textViewRow1 = rowBinding.textViewRow1
                textViewRow2 = rowBinding.textViewRow2
                textViewRow3 = rowBinding.textViewRow3

                // 항목 하나의 View에 컨텍스트 메뉴 생성 이벤트를 붙혀준다.
                rowBinding.root.setOnCreateContextMenuListener { menu, v, menuInfo ->
                    menu.setHeaderTitle("${dataList[adapterPosition].name}")
                    menuInflater.inflate(R.menu.row_menu, menu)

                    // 첫번째 메뉴에 대한 이벤트 처리
                    menu[0].setOnMenuItemClickListener {
                        dataList.removeAt(adapterPosition)

                        this@RecyclerViewAdapterClass.notifyDataSetChanged()
                        false
                    }
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
            return dataList.size
        }

        override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
            val (name, age, korean) = dataList[position]

            holder.textViewRow1.text = name
            holder.textViewRow2.text = "$age"
            holder.textViewRow3.text = "$korean"
        }
    }

}