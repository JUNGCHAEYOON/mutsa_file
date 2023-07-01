package com.test.android25_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.test.android25_ex01.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    val movieList = mutableListOf<MovieInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            // 첫 번째 EditText에 포커스를 준다.
            editTextSubject.requestFocus()
            thread {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus, 0)
            }

            seekBarFair.run{
                // 사용자가 seekbar를 움직이는 경우
                setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        // 현재 seekbar의 값을 가지고 실제 가격을 계산하여 출력한다.
                        textViewFair.text = "요금 : ${progress * 100 + 5000}원"
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                        // TODO("Not yet implemented")
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                        // TODO("Not yet implemented")
                    }

                } )
            }

            editTextDirectorName.run{
                setOnEditorActionListener { v, actionId, event ->

                    val a1 = editTextSubject.text.toString()
                    val a2 = seekBarFair.progress * 100 + 5000
                    val a3 = when(chipGroupGrade.checkedChipId){
                        R.id.chipGrade1 -> "전체"
                        R.id.chipGrade2 -> "12세"
                        R.id.chipGrade3 -> "15세"
                        R.id.chipGrade4 -> "성인"
                        else -> ""
                    }
                    var a4 = ratingBarStar.rating
                    var a5 = editTextDirectorName.text.toString()

                    val movieInfo = MovieInfo(a1, a2, a3, a4, a5)
                    movieList.add(movieInfo)

                    editTextSubject.setText("")
                    seekBarFair.progress = 0
                    chipGroupGrade.check(R.id.chipGrade1)
                    ratingBarStar.rating = 0.0f
                    editTextDirectorName.setText("")

                    editTextSubject.requestFocus()

                    false
                }
            }

            buttonShowResult.run{
                setOnClickListener {
                    textViewShowResult.text = ""

                    for(movieInfo in movieList){
                        textViewShowResult.run{
                            append("영화 제목 : ${movieInfo.subject}\n")
                            append("요금 : ${movieInfo.fair}원\n")
                            append("등급 : ${movieInfo.grade}\n")
                            append("별점 : ${movieInfo.rating}\n")
                            append("감독 이름 : ${movieInfo.directorName}\n\n")
                        }
                    }

                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                    currentFocus!!.clearFocus()
                }
            }
        }
    }

    data class MovieInfo(var subject:String, var fair:Int, var grade:String, var rating:Float,
                         var directorName:String)
}







