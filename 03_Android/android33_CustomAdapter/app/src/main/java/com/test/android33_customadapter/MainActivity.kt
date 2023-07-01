package com.test.android33_customadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.test.android33_customadapter.databinding.ActivityMainBinding
import com.test.android33_customadapter.databinding.RowBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val data1 = arrayOf(
        "데이터1", "데이터2", "데이터3", "데이터4", "데이터5",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            listView.run{
                adapter = CustomAdapter()
            }
        }

    }

    // AdapterClass
    // BaseAdapter를 상속받고 메서드를 구현해준다.
    inner class CustomAdapter : BaseAdapter() {
        // 리스트뷰의 로우의 개수를 결정하는 메서드
        // 이 메서드가 반환하는 정수 만큼 로우를 생성한다.
        override fun getCount(): Int {
            return data1.size
        }
        // 현재 번째의 로우 View를 반환하도록 만들어준다.
        override fun getItem(position: Int): Any? {
            // TODO("Not yet implemented")
            return null
        }
        // 현재 번째의 로우 View의 ID를 반환하도록 만들어준다.
        override fun getItemId(position: Int): Long {
            // TODO("Not yet implemented")
            return 0
        }
        // 로우로 사용할 View를 생성하여 반환하는 메서드
        // 여기서 반환하는 View를 현재 번째의 Row로 사용한다.
        // 첫 번째 : 구성하고자 하는 Row의 순서값(0부터 1씩 증가)
        // 두 번째 : 재사용 가능한 View가 있다면 매개변수로 들어온다.
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            // layout binding 객체를 담을 변수
            var rowBiding : RowBinding? = null
            // 항목 View를 담을 변수
            var mainView = convertView

            // 재사용 가능한 뷰가 없다면..
            if(mainView == null){
                // ViewBinding 객체를 생성한다.
                rowBiding = RowBinding.inflate(layoutInflater)
                mainView = rowBiding.root
                // ViewBinding 객체를 View에 저장한다.
                mainView!!.tag = rowBiding
            }
            // 재사용 가능한 View가 있다면
            else {
                // View에 저장되어 있는 ViewBinding 객체를 추출한다.
                rowBiding = mainView!!.tag as RowBinding
            }

            rowBiding.run{
                textViewRow1.run{
                    text = data1[position]
                }

                buttonRow1.run{
                    setOnClickListener {
                        activityMainBinding.textViewResult.text = "$position : 버튼1"
                    }
                }

                buttonRow2.run{
                    setOnClickListener {
                        activityMainBinding.textViewResult.text = "$position : 버튼2"
                    }
                }
            }


            return mainView
        }
    }
}










