package com.test.mvvm_practice_sql.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Test.db",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create table TestTable 
            | (testIdx integer primary key autoincrement,
            |  testData1 text not null,
            |  testData2 text not null)
        """.trimMargin()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}