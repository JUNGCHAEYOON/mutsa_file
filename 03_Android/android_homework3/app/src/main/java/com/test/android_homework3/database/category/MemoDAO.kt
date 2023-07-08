package com.test.android_homework3.database.category

import android.content.ContentValues
import android.content.Context
import com.test.android_homework3.Memo

class MemoDAO {
    companion object{
        fun insertData(context : Context, memo : Memo){
            val cv = ContentValues()
            cv.put("cidx",memo.cidx)
            cv.put("memoTitle", memo.memoTitle)
            cv.put("memoContent",memo.memoContent)
            val dh = MemoDBHelper(context)
            dh.writableDatabase.insert("MemoTable",null,cv)
            dh.close()
        }

        fun selectMemo(context : Context, idx : Int) : Memo{
            val dh = MemoDBHelper(context)
            val selection = "idx = ?"
            val args = arrayOf("$idx")
            val cursor = dh.writableDatabase.query("MemoTable",null,selection,args,null,null,null)
            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("cidx")
            val idx3 = cursor.getColumnIndex("memoTitle")
            val idx4 = cursor.getColumnIndex("memoContent")

            val idx = cursor.getInt(idx1)
            val cidx = cursor.getInt(idx2)
            val memoTitle = cursor.getString(idx3)
            val memoContent = cursor.getString(idx4)

            val memo = Memo(idx, cidx, memoTitle, memoContent)
            dh.close()

            return memo
        }

        fun selectAllMemo(context : Context, cidx : Int) : MutableList<Memo>{
            val dh = MemoDBHelper(context)
            val selection = "cidx = ?"
            val args = arrayOf("$cidx")
            val cursor = dh.writableDatabase.query("MemoTable",null,selection,args,null,null,null)
            val memoList = mutableListOf<Memo>()

            while(cursor.moveToNext()){
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("cidx")
                val idx3 = cursor.getColumnIndex("memoTitle")
                val idx4 = cursor.getColumnIndex("memoContent")

                val idx = cursor.getInt(idx1)
                val cidx = cursor.getInt(idx2)
                val memoTitle = cursor.getString(idx3)
                val memoContent = cursor.getString(idx4)

                val memo = Memo(idx, cidx, memoTitle, memoContent)
                memoList.add(memo)
            }

            dh.close()
            return memoList
        }

        fun updateMemo(context : Context, memo : Memo){
            val cv = ContentValues()
            cv.put("memoTitle", memo.memoTitle)
            cv.put("memoContent", memo.memoContent)
            val condition = "idx = ?"
            val args = arrayOf("${memo.idx}")
            val dh = MemoDBHelper(context)
            dh.writableDatabase.update("MemoTable",cv,condition,args)
            dh.close()
        }

        fun deleteMemo(context: Context, idx : Int){
            val sql = "delete from MemoTable where idx = ?"
            val args = arrayOf(idx)
            val dh = MemoDBHelper(context)
            dh.writableDatabase.execSQL(sql,args)
            dh.close()
        }
    }
}