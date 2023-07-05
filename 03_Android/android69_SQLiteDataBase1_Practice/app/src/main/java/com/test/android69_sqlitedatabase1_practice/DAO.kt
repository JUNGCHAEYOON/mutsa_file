package com.test.android69_sqlitedatabase1_practice

import android.content.Context

class DAO {

    companion object{
        fun insertData(context : Context, data : UserInfo){
            val sql = """insert into UserInfoTable
                | (name, age, korean)
                | values(?,?,?)
            """.trimMargin()

            val arg1 = arrayOf(
                data.name, data.age, data.korean
            )

            val sqliteDatabase = DBHelper(context)
            sqliteDatabase.writableDatabase.execSQL(sql,arg1)
            sqliteDatabase.close()
        }

        fun selectData(context : Context, idx : Int) : UserInfo{
            val sql = "select * from UserInfoTable where idx = ?"
            val arg1 = arrayOf("$idx")
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg1)
            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("name")
            val idx3 = cursor.getColumnIndex("age")
            val idx4 = cursor.getColumnIndex("korean")

            val idx = cursor.getInt(idx1)
            val name = cursor.getString(idx2)
            val age = cursor.getInt(idx3)
            val korean = cursor.getInt(idx4)

            val userInfo = UserInfo(idx, name, age, korean)
            dbHelper.close()

            return userInfo
        }

        fun selectAllData(context : Context) : MutableList<UserInfo>{
            val sql = "select * from UserInfoTable"
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql,null)
            val userInfoList = mutableListOf<UserInfo>()
            while(cursor.moveToNext()){
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("name")
                val idx3 = cursor.getColumnIndex("age")
                val idx4 = cursor.getColumnIndex("korean")

                val idx = cursor.getInt(idx1)
                val name = cursor.getString(idx2)
                val age = cursor.getInt(idx3)
                val korean = cursor.getInt(idx4)

                val userInfo = UserInfo(idx, name, age, korean)
                userInfoList.add(userInfo)
            }
            dbHelper.close()
            return userInfoList
        }

        fun updateData(){

        }

        fun deleteData(){

        }


    }
}