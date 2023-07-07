package com.test.android_homework3.database.login

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LoginDBHelper(context : Context) : SQLiteOpenHelper(context, "Login.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table LoginTable
            (idx idx not null,
            password password not null)
            """.trimIndent()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}