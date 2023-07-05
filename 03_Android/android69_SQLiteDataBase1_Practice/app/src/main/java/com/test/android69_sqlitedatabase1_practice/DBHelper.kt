package com.test.android69_sqlitedatabase1_practice

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context : Context) : SQLiteOpenHelper(context, "Test.db",null,1) {
    override fun onCreate(sqliteDatabase: SQLiteDatabase?) {
        val sql = """create Table UserInfoTable
            (idx integer primary key autoincrement,
            name name not null,
            age age not null,
            korean not null
            )
        """.trimMargin()

        // 쿼리문을 수행한다.
        sqliteDatabase?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}