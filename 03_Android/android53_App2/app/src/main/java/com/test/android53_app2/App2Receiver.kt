package com.test.android53_app2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class App2Receiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val str1 = "App2의 BR이 동작하였습니다"
        val t1 = Toast.makeText(context, str1, Toast.LENGTH_SHORT)
        t1.show()
    }
}