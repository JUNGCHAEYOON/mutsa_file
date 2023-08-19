package com.example.material22_tab

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import com.example.material22_tab.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{

            tabLayout.run{

                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        textView.text = when(tab?.position){
                            0 -> {
                                "첫 번째 탭을 선택했습니다"
                            }
                            1 -> {
                                "두 번째 탭을 선택했습니다"
                            }
                            2 -> {
                                "세 번째 탭을 선택했습니다"
                            }

                            else -> {
                                ""
                            }
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {

                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {

                    }

                })

                selectTab(tabLayout.getTabAt(1))
            }
        }
    }
}