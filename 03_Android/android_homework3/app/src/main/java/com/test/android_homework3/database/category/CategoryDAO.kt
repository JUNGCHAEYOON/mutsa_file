package com.test.android_homework3.database.category

import android.content.ContentValues
import android.content.Context
import com.test.android_homework3.Category
import com.test.android_homework3.Memo

class CategoryDAO {
    companion object{
        // 삽입
        // 한행을 삽입함
        fun insertData(context : Context, categoryName : String){
            val cv = ContentValues()
            cv.put("categoryName", categoryName)
            val dbHelper = CategoryDBHelper(context)
            dbHelper.writableDatabase.insert("CategoryTable", null, cv)
            dbHelper.close()
        }

        // 전체행을 다불러옴
        fun selectAllCategory(context : Context) : MutableList<Category>{
            val dh = CategoryDBHelper(context)
            val cursor = dh.writableDatabase.query("CategoryTable",null,null,null,null,null,null,null)
            val categoryList = mutableListOf<Category>()

            while(cursor.moveToNext()){
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("categoryName")

                val idx = cursor.getInt(idx1)
                val categoryName = cursor.getString(idx2)

                val memoList = mutableListOf<Memo>()
                val category = Category(idx, categoryName,  memoList)

                categoryList.add(category)
            }

            dh.close()
            return categoryList
        }

        // 카테고리명 수정
        fun updateCategoryName(context: Context, category : Category){
            val cv = ContentValues()
            cv.put("categoryName",category.categoryName)
            val condition = "idx = ?"
            val args = arrayOf("${category.idx}")
            val dh = CategoryDBHelper(context)
            dh.writableDatabase.update("CategoryTable",cv,condition,args)
            dh.close()
        }


        // idx 기준으로 삭제
        fun deleteCategoryName(context : Context, idx : Int){
            val sql = "delete from CategoryTable where idx = ?"
            val args = arrayOf(idx)
            val dh = CategoryDBHelper(context)
            dh.writableDatabase.execSQL(sql,args)
            dh.close()
        }
    }
}