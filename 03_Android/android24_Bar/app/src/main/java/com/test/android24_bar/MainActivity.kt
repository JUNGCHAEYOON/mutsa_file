package com.test.android24_bar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import com.test.android24_bar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.run{
                setOnClickListener {
                    // progressBar의 값을 설정한다.
                    progressBar2.progress = 70
                    // SeekBar의 값을 설정한다.
                    seekBar.progress = 70
                    seekBar2.progress = 70
                    // RatingBar의 값을 설정한다.
                    ratingBar.rating = 1.5F
                }
            }

            button2.run{
                setOnClickListener {
                    // SeekBar에 설정된 값을 가져와 출력한다.
                    textView.text = "SeekBar1 : ${seekBar.progress}\n"
                    textView.append("SeekBar2 : ${seekBar2.progress}\n")
                    // RatingBar에 설정된 별점을 출력한다.
                    textView.append("RatingBAr : ${ratingBar.rating}\n")
                }
            }

            seekBar.run{
                setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
                    // progress : 새롭게 설정된 값
                    // fromUser : 사용자가 변경한 것인지 여부
                    override fun onProgressChanged(
                        seekBar: SeekBar?,
                        progress: Int,
                        fromUser: Boolean
                    ) {
                        textView2.text = "${progress}\n"
                        if(fromUser){
                            textView2.append("사용자에 의해 변경되었습니다\n")
                        } else {
                            textView2.append("코드를 통해 변경되었습니다\n")
                        }
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    }

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    }

                })
            }

            ratingBar.run{
                // rating : 설정된 별점 값
                // fromUser : 사용자에 의해 설정되었는가...
                setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
                    textView2.text = "Rating : ${rating}\n"
                    if(fromUser){
                        textView2.append("사용자에 의해 변경되었습니다\n")
                    } else {
                        textView2.append("코드를 통해 변경되었습니다\n")
                    }
                }
            }
        }
    }
}