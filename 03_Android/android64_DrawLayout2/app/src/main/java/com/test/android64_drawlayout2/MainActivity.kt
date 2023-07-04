package com.test.android64_drawlayout2

import android.os.Bundle
import android.view.Menu
import android.widget.LinearLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.test.android64_drawlayout2.databinding.ActivityMainBinding
import com.test.android64_drawlayout2.databinding.NavHeaderMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        // Step01 ==============================================
        // nav_header_main.xml 에 표시할 데이터를 설정한다.
        // Header 부분의 LinearLayout을 추출한다.
        val headerLayout = binding.navView.getHeaderView(0) as LinearLayout
        // 하위의 View들을 추출한다.
        val navHeaderMainBinding = NavHeaderMainBinding.bind(headerLayout)
        navHeaderMainBinding.imageViewHeader.setImageResource(R.drawable.imgflag8)
        navHeaderMainBinding.textViewHeader1.text = "한국사람"
        navHeaderMainBinding.textViewHeader2.text = "나는 가끔 눈물을 흘린다...."

        // Step01 ==============================================

        binding.appBarMain.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.firstFragment, R.id.secondFragment, R.id.thirdFragment
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}