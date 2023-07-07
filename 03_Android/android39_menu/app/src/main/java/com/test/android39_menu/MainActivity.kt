package com.test.android39_menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.SubMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.PopupMenu
import com.test.android39_menu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val dataList = arrayOf(
        "항목1", "항목2", "항목3", "항목4", "항목5"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            // textView에 컨텍스트 메뉴를 등록한다.
            registerForContextMenu(textView)
            // ListView에 컨텍스트 메뉴를 등록한다.
            registerForContextMenu(listView)

            button.run{
                setOnClickListener {
                    // 팝업 메뉴 객체를 생성한다.
                    // 제일 상단에 있는 TextView에 나타날 메뉴이다
                    val pop = PopupMenu(this@MainActivity, textView)

                    // 메뉴를 구성한다.
                    menuInflater.inflate(R.menu.popup_menu, pop.menu)

                    // 팝업메뉴를  띄운다.
                    pop.show()

                    // 팝업메뉴를 눌렀을 때 동작하는 리스너
                    pop.setOnMenuItemClickListener {
                        // 메뉴의 id로 분기한다.
                        when(it.itemId){
                            R.id.popup1 -> textView.text = "팝업 메뉴1을 선택했습니다"
                            R.id.popup2 -> textView.text = "팝업 메뉴2를 선택했습니다"
                            R.id.popup3 -> textView.text = "팝업 메뉴3을 선택했습니다"
                        }

                        false
                    }
                }
            }

            listView.run{
                adapter = ArrayAdapter<String>(
                    this@MainActivity, android.R.layout.simple_list_item_1, dataList
                )
            }
        }
    }

    // 옵션 메뉴를 구성하기 위해 사용하는 메서드
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // xml 파일로 부터 메뉴를 성성한다.
        // menuInflater.inflate(R.menu.main_menu, menu)

        // 코드를 통한 메뉴 구성
        menu?.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드 메뉴 1")
        menu?.add(Menu.NONE, Menu.FIRST + 1, Menu.NONE, "코드 메뉴 2")

        val subMenu = menu?.addSubMenu("코드 메뉴 3")
        subMenu?.add(Menu.NONE, Menu.FIRST + 2, Menu.NONE, "하위 메뉴 3-1")
        subMenu?.add(Menu.NONE, Menu.FIRST + 3, Menu.NONE, "하위 메뉴 3-2")

        menu?.add(Menu.NONE, Menu.FIRST + 4, Menu.NONE, "코드 메뉴 4")

        // true를 반환해야 메뉴가 나타났었습니다..ㅠㅠㅠㅠㅠㅠ
        return true
    }

    // 옵션 메뉴에서 메뉴 항목을 선택하면 호출되는 메서드
    // 매개변수로 사용자가 선택한 메뉴 항목 객체가 들어온다.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        
        activityMainBinding.run{
            // 메뉴 항목의 id로 분기한다.
//            when(item.itemId){
//                R.id.menu_item1 -> textView.text = "메뉴 항목1을 선택했습니다"
//                R.id.menu_item2 -> textView.text = "메뉴 항목2를 선택했습니다"
//                R.id.menu_item31 -> textView.text = "하위 메뉴 3-1을 선택했습니다"
//                R.id.menu_item32 -> textView.text = "하위 메뉴 3-2를 선택했습니다"
//                R.id.menu_item4 -> textView.text = "메뉴 항목4를 선택했습니다"
//            }
            
            when(item.itemId){
                Menu.FIRST -> textView.text = "코드 메뉴1을 선택했습니다"
                Menu.FIRST + 1 -> textView.text = "코드 메뉴2을 선택했습니다"
                Menu.FIRST + 2 -> textView.text = "하위 메뉴 3-1을 선택했습니다"
                Menu.FIRST + 3 -> textView.text = "하위 메뉴 3-2를 선택했습니다"
                Menu.FIRST + 4 -> textView.text = "하위 메뉴 4를 선택했습니다"
            }
        }
        
        return super.onOptionsItemSelected(item)
    }

    // 두 번째 매개 변수(v) : 사용자가 길게 누르면 뷰 객체가 들어온다.
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        // View의 아이디로 분기한다.
        if(v != null) {
            when (v.id) {
                // textView
                R.id.textView -> {
                    // 메뉴의 제목
                    menu?.setHeaderTitle("텍스트뷰의 메뉴")
                    menuInflater.inflate(R.menu.context_menu, menu);
                }
                // ListView
                R.id.listView -> {
                    // 3번째 매개변수로 들어오는 객체로 부터
                    // 사용자가 길게 누른 항목이 몇 번째인지 파악한다.
                    val info = menuInfo as AdapterView.AdapterContextMenuInfo
                    menu?.setHeaderTitle("${dataList[info.position]}의 메뉴")
                    menuInflater.inflate(R.menu.list_menu, menu)
                }
            }
        }
    }

    // 컨텍스트 메뉴의 항목을 터치했을 때 호출되는 메서드
    // 이 메서드에서 메뉴를 띄우기 위해 길게 누른 뷰가 무엇인지 구분할 방법이 없다
    // 이에 서로 다른 뷰의 컨스트 메뉴라고 하더라도 메뉴의 id는 다 다르게 구성해줘야 한다.
    override fun onContextItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            // TextView의 컨텍스트 메뉴
            R.id.context1 -> activityMainBinding.textView.text = "텍스트뷰 - 메뉴1"
            R.id.context2 -> activityMainBinding.textView.text = "텍스트뷰 - 메뉴2"
            R.id.context3 -> activityMainBinding.textView.text = "텍스트뷰 - 메뉴3"
            // ListView의 컨텍스트 메뉴
            R.id.list_menu1 -> {
                val info  = item.menuInfo as AdapterView.AdapterContextMenuInfo
                activityMainBinding.textView.text = "리스트뷰 - ${dataList[info.position]}의 메뉴1"
            }
            R.id.list_menu2 -> {
                val info  = item.menuInfo as AdapterView.AdapterContextMenuInfo
                activityMainBinding.textView.text = "리스트뷰 - ${dataList[info.position]}의 메뉴2"
            }
            R.id.list_menu3 -> {
                val info  = item.menuInfo as AdapterView.AdapterContextMenuInfo
                activityMainBinding.textView.text = "리스트뷰 - ${dataList[info.position]}의 메뉴3"
            }
        }

        return super.onContextItemSelected(item)
    }
}









