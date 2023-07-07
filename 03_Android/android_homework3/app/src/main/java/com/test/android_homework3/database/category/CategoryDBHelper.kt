package com.test.android_homework3.database.category

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CategoryDBHelper(context : Context) : SQLiteOpenHelper(context, "Category.db",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create Table CategoryTable
            (idx integer primary key autoincrement,
            categoryName text 
            )
        """.trimIndent()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}