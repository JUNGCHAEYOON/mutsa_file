package com.test.android_homework1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class InfoDBHelper(context : Context) : SQLiteOpenHelper(context, "Info.db",null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val sql = """create Table InfoTable
            (idx integer primary key autoincrement,
            name text not null,
            age integer not null,
            korean integer not null
            )
            """.trimIndent()

        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

}

class InfoDAO {
    companion object{
        fun insertInfo(context: Context, info : Info){
            val cv = ContentValues()
            cv.put("name",info.name)
            cv.put("age",info.age)
            cv.put("korean",info.korean)
            val dh = InfoDBHelper(context)
            dh.writableDatabase.insert("InfoTable",null,cv)
            dh.close()
        }

        fun selectInfo(context : Context, idx : Int) : Info{
            val dh = InfoDBHelper(context)
            val selection = "idx = ?"
            val args = arrayOf("$idx")
            val cursor = dh.writableDatabase.query("InfoTable",null, selection, args, null,null,null)
            cursor.moveToNext()
            // 컬럼의 이름을 지정하여 컬럼의 순서값을 가져온다.
            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("name")
            val idx3 = cursor.getColumnIndex("age")
            val idx4 = cursor.getColumnIndex("korean")

            val idx = cursor.getInt(idx1)
            val name = cursor.getString(idx2)
            val age = cursor.getInt(idx3)
            val korean = cursor.getInt(idx4)

            val info = Info(idx, name, age, korean)
            dh.close()
            return info
        }

        fun selectAllData(context : Context) : MutableList<Info>{
            val dh = InfoDBHelper(context)
            val cursor = dh.writableDatabase.query("InfoTable",null, null, null, null,null,null)
            val infoList = mutableListOf<Info>()

            while(cursor.moveToNext()){
                // 컬럼의 이름을 지정하여 컬럼의 순서값을 가져온다.
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("name")
                val idx3 = cursor.getColumnIndex("age")
                val idx4 = cursor.getColumnIndex("korean")

                val idx = cursor.getInt(idx1)
                val name = cursor.getString(idx2)
                val age = cursor.getInt(idx3)
                val korean = cursor.getInt(idx4)

                val info = Info(idx, name, age, korean)
                infoList.add(info)
            }

            dh.close()
            return infoList
        }
    }
}