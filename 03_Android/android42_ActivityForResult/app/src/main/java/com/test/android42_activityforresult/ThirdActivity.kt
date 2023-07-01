package com.test.android42_activityforresult

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android42_activityforresult.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    lateinit var thirdBinding: ActivityThirdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        thirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(thirdBinding.root)

        thirdBinding.run{
            buttonThird.run{
                setOnClickListener {
                    // Activity를 종료한다.
                    finish()
                }
            }
        }
    }
}