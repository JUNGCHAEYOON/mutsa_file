package com.test.android12_edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import com.test.android12_edittext.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            // 현재 포커스를 가지고 있는 View와 연결된 키보드를 올라오게 한다.
            // onCreate 메서드가 끝나야 화면이 나타나는데 그 전에 키보드가 올라오게 하는
            // 명령을 전달하기 때문에 무시된다. 이에, 쓰래드로 운영을 한다.
            thread {
                // 500ms 쉬게 한다.
                // onCreate 메서드의 수행이 끝날 때 까지 대기한다.
                SystemClock.sleep(500);
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus, 0);
            }

            editTextText.run{
                // EditText에 새로운 문자열을 설정한다.
                setText("코드에서 문자열 설정")
                // 현재 EditText에 포커스를 준다.
                requestFocus()

                // 엔터키를 눌렀을 때의 이벤트
                setOnEditorActionListener { v, actionId, event ->
                    textView.text = "엔터 버튼을 눌렀습니다"
                    textView2.text = text.toString()
                    // true를 반환하면 엔터키 누른 후에 포커스가 현재 EditText로 유지된다.
                    // false를 반환하면 엔터키 누른 후에 다음 EditText로 포커스가 이동한다.
                    false
                }

                // 입력 감시자 설정
                val editTextWatcher1 = EditTextWatcher1()
                addTextChangedListener(editTextWatcher1)
            }

            editTextTextPassword.run{
                // addTextChangedListener를 사용할 때 고차함수를 사용하면
                // TextWatcher의 after 역할을 수행한다.
                // 즉 실시간으로 사용자의 입력 내용을 받아 낼 수 있다.
                addTextChangedListener {
                    textView.text = it
                }
            }

            button.run{
                setOnClickListener {
                    // EditText의 문자열을 가지고 온다.
                    val str1 = editTextText.text.toString()
                    textView.text = str1

                    // 키보드를 내린다
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    if(currentFocus != null) {
                        // currentFocus : 현재 포커스를 가지고 있는 View를 지칭할 수 있다.
                        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                        // 포커스를 해제한다.
                        currentFocus!!.clearFocus()
                    }
                }
            }
        }
    }

    // EditText 입력 감시자
    inner class EditTextWatcher1 : TextWatcher{
        // 입력 내용 변경 전
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            activityMainBinding.textView.text = "before : ${s}"
        }

        // 입력 내용 변경 했을 때
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            activityMainBinding.textView2.text = "changed : ${s}"
        }

        // 입력 내용이 변경된 후
        override fun afterTextChanged(s: Editable?) {
            activityMainBinding.textView3.text = "after : ${s}"
        }

    }
}