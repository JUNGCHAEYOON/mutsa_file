package com.test.android23_chip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android23_chip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.run{
                setOnClickListener {
                    if(chip2.isChecked){
                        textView.text = "선택 되어 있습니다\n"
                    } else {
                        textView.text = "선택되어 있지 않습니다\n"
                    }

                    when(chipGroup.checkedChipId){
                        R.id.chip5 -> {
                            textView.append("라디오1 선택\n")
                        }
                        R.id.chip6 -> {
                            textView.append("라디오2 선택\n")
                        }
                        R.id.chip7 -> {
                            textView.append("라이도3 선택\n")
                        }
                    }
                }
            }
        }
    }
}