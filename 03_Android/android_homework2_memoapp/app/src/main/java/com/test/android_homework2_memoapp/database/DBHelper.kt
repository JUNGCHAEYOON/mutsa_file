package com.test.android_homework2_memoapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context : Context) : SQLiteOpenHelper(context, "Memo.db",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create Table MemoTable
            (idx integer primary key autoincrement,
            title text not null,
            date date not null,
            content text not null
            )
        """.trimMargin()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}