package com.test.android02_viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnDragListener
import android.view.View.OnTouchListener
import android.widget.Button
import android.widget.TextView
import com.test.android02_viewbinding.databinding.ActivityMainBinding
import com.test.android02_viewbinding.databinding.ActivityTestBinding

class MainActivity : AppCompatActivity() {

    // ViewBinding
    // res/layout 폴더에 있는 xml 파일 하나당 하나의 클래스가 만들어진다.
    // 이 클래스에는 xml 파일이 가지고 있는 View들을 관리하는 기능이 들어가 있다.
    // 이를 통해면 개발자가 View를 직접 추출하지고 사용할 수 있다.
    // 안드로이드 OS가 알아서 View를 추출하여 변수에 담아준다.

    // 자동으로 생성되는 클래스이 이름은 xml 파일의 이름으로 생성된다.
    // activity_main.xml -> ActivityMainBinding
    // activity_test.xml -> ActivityTestBinding
    // activity_second.xml -> ActivitySecondBinding
    // activity_kkk.xml -> ActivityKkkBinding

    // 1. app 수준의 build.graldle 파일에 다음 코드를 추가해준다.
    // viewBinding {
    //     enabled = true
    // }

    // 생성된 ViewBinding 객체에는 View의 ID와 동일한 이름의 변수가
    // 만들어지고 그 변수에는 View 객체가 들어가 있다.

    // View를 담을 변수
//    lateinit var textView:TextView
//    lateinit var button:Button
//    lateinit var button2:Button
//    lateinit var button3:Button
//    lateinit var button4:Button

    // ViewBinding 객체를 담을 변수
    lateinit var activityMainBinding:ActivityMainBinding
    lateinit var activityTestBinding: ActivityTestBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        // ViewBinding 객체를 가져온다.
        // layoutInflater : XML 파일을 통해 객체를 생성하는 도구
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        activityTestBinding = ActivityTestBinding.inflate(layoutInflater)

        // Viewbinding 객체가 관리하는 View 중에 최 상위 View를
        // 지정하여 화면에 나타나게 한다.
        setContentView(activityMainBinding.root)
        // setContentView(activityTestBinding.root)

        // View 객체를 가져온다.
//        textView = findViewById(R.id.textView)
//        button = findViewById(R.id.button)
//        button2 = findViewById(R.id.button2)
//        button3 = findViewById(R.id.button3)
//        button4 = findViewById(R.id.button4)

        // 리스너를 설정해준다.
        val button1ClickListener = Button1ClickListener()
        val button2ClickListener = Button2ClickListener()
        val button3ClickListener = Button3ClickListener()
        val button4ClickListener  = Button4ClickListener()

//        button.setOnClickListener(button1ClickListener)
//        button2.setOnClickListener(button2ClickListener)
//        button3.setOnClickListener(button3ClickListener)
//        button4.setOnClickListener(button4ClickListener)

//        activityMainBinding.button.setOnClickListener(button1ClickListener)
//        activityMainBinding.button2.setOnClickListener(button2ClickListener)
//        activityMainBinding.button3.setOnClickListener(button3ClickListener)
//        activityMainBinding.button4.setOnClickListener(button4ClickListener)


//        activityMainBinding.button.setOnClickListener {
//            activityMainBinding.textView.text = "10 + 10 = ${10 + 10}"
//        }
//
//        activityMainBinding.button2.setOnClickListener {
//            activityMainBinding.textView.text = "10 - 10 = ${10 - 10}"
//        }
//
//        activityMainBinding.button3.setOnClickListener {
//            activityMainBinding.textView.text = "10 * 10 = ${10 * 10}"
//        }
//
//        activityMainBinding.button4.setOnClickListener {
//            activityMainBinding.textView.text = "10 / 10 = ${10 / 10}"
//        }

        activityMainBinding.run {
            button.run{
                setOnClickListener {
                    activityMainBinding.textView.text = "10 - 10 = ${10 - 10}"
                }
            }

            button2.run{
                setOnClickListener {
                    activityMainBinding.textView.text = "10 - 10 = ${10 - 10}"
                }
            }

            button3.run{
                setOnClickListener {
                    activityMainBinding.textView.text = "10 * 10 = ${10 * 10}"
                }
            }

            button4.run{
                setOnClickListener {
                    activityMainBinding.textView.text = "10 / 10 = ${10 / 10}"
                }
            }
        }
    }


    // 각 버튼별 리스너
    inner class Button1ClickListener : OnClickListener{
        override fun onClick(v: View?) {
            // textView.text = "10 + 10 = ${10 + 10}"
            activityMainBinding.textView.text = "10 + 10 = ${10 + 10}"
        }
    }

    inner class Button2ClickListener : OnClickListener{
        override fun onClick(v: View?) {
            // textView.text = "10 - 10 = ${10 - 10}"
            activityMainBinding.textView.text = "10 - 10 = ${10 - 10}"
        }
    }

    inner class Button3ClickListener : OnClickListener{
        override fun onClick(v: View?) {
            // textView.text = "10 * 10 = ${10 * 10}"
            activityMainBinding.textView.text = "10 * 10 = ${10 * 10}"
        }
    }

    inner class Button4ClickListener : OnClickListener{
        override fun onClick(v: View?) {
            // textView.text = "10 / 10 = ${10 / 10}"
            activityMainBinding.textView.text = "10 / 10 = ${10 / 10}"
        }
    }
}










