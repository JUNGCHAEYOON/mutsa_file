package com.test.android63_tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.test.android63_tablayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding

    // 탭에 표시할 이름
    val tabName = arrayOf(
        "탭1", "탭2", "탭3", "탭4", "탭5"
    )

    // 표시할 Fragment들을 담을 리스트
    val fragmentList = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run{
            // 사용할 프래그먼트들을 생성하여 리스트에 담는다.
            fragmentList.add(Sub1Fragment())
            fragmentList.add(Sub2Fragment())
            fragmentList.add(Sub3Fragment())
            fragmentList.add(Sub4Fragment())
            fragmentList.add(Sub5Fragment())

            // ViewPager에 어뎁터를 설정한다.
            pager2.adapter = TabAdapterClass(this@MainActivity)

            // 탭을 구성한다.
            val tabLayoutMediator = TabLayoutMediator(tabs, pager2){ tab: TabLayout.Tab, i: Int ->
                tab.text = tabName[i]
            }
            tabLayoutMediator.attach()
        }
    }

    // 어뎁터 클래스
    inner class TabAdapterClass(fragmentActivity:FragmentActivity) : FragmentStateAdapter(fragmentActivity){
        override fun getItemCount(): Int {
            return fragmentList.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragmentList[position]
        }
    }

}








