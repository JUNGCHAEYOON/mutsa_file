package com.test.android_homework2_memoapp.database

import android.content.Context
import com.test.android_homework2_memoapp.Memo

class DAO {
    companion object{
        fun insertData(context : Context, data : Memo){
            val sql = """insert into MemoTable
                | (title, date, content)
                | values(?,?,?)
            """.trimMargin()

            val arg1 = arrayOf(
                data.title, data.date, data.content
            )

            val sqliteDatabase = DBHelper(context)
            sqliteDatabase.writableDatabase.execSQL(sql,arg1)
            sqliteDatabase.close()
        }

        fun selectData(context : Context, idx:Int) : Memo{
            val sql = "select * from MemoTable where idx=?"
            val arg1 = arrayOf("$idx")
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg1)
            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("title")
            val idx3 = cursor.getColumnIndex("date")
            val idx4 = cursor.getColumnIndex("content")

            val idx = cursor.getInt(idx1)
            val title = cursor.getString(idx2)
            val date = cursor.getString(idx3)
            val content = cursor.getString(idx4)

            val memo = Memo(idx, title, date, content)

            dbHelper.close()
            return memo
        }

        fun selectAllData(context : Context) : MutableList<Memo>{
            val sql = "select * from MemoTable"
            val dbHelper = DBHelper(context)
            val cursor = dbHelper.writableDatabase.rawQuery(sql,null)

            val memoList = mutableListOf<Memo>()

            while(cursor.moveToNext()){
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("title")
                val idx3 = cursor.getColumnIndex("date")
                val idx4 = cursor.getColumnIndex("content")

                val idx = cursor.getInt(idx1)
                val title = cursor.getString(idx2)
                val date = cursor.getString(idx3)
                val content = cursor.getString(idx4)

                val memo = Memo(idx, title, date, content)
                memoList.add(memo)
            }

            dbHelper.close()
            return memoList
        }

        fun updateData(context : Context, obj : Memo){
            val sql = """update MemoTable
                | set title=?, date=?, content=?
                | where idx=?
            """.trimMargin()

            val args = arrayOf(obj.title, obj.date, obj.content)
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }

        fun deleteData(context : Context, idx : Int){
            val sql = "delete from MemoTable where idx = ?"
            val args = arrayOf(idx)
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }
    }
}