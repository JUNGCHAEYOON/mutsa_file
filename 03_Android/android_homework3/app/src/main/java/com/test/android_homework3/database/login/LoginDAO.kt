package com.test.android_homework3.database.login

import android.content.ContentValues
import android.content.Context
import com.test.android_homework3.Password

class LoginDAO {
    companion object{
        fun insertData(context : Context, password : Password) {
            val contentValues = ContentValues()
            contentValues.put("idx",password.idx)
            contentValues.put("password", password.password)

            val loginDBHelper = LoginDBHelper(context)
            loginDBHelper.writableDatabase.insert("LoginTable", null, contentValues)
            loginDBHelper.close()
        }

        fun selectData(context: Context) : Password{
            val loginDBHelper = LoginDBHelper(context)

            val cursor = loginDBHelper.writableDatabase.query("LoginTable",null, null, null, null, null, null)
            cursor.moveToNext()

            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("password")

            val idx = cursor.getInt(idx1)
            val password = cursor.getString(idx2)

            val passwordClass = Password(idx, password)
            loginDBHelper.close()
            return passwordClass
        }

        fun updateData(context : Context, obj : Password){
            val cv = ContentValues()
            cv.put("idx", obj.idx)
            cv.put("password",obj.password)

            val condition = "idx = ?"
            val args = arrayOf("${obj.idx}")
            val loginDBHelper = LoginDBHelper(context)

            loginDBHelper.writableDatabase.update("LoginTable",cv,condition,args)
            loginDBHelper.close()
        }

        // 비밀번호가 설정이 되어있는지 검증하는 메서드
        // 비밀번호 새로 설정시 idx = 0 으로 하여 생성한다.
        fun passwordExists(context : Context) : Boolean{
            val idx = 0
            val loginDBHelper = LoginDBHelper(context)

            val sql = "select * from LoginTable where idx = ?"
            val arg1 = arrayOf("$idx")
            val cursor = loginDBHelper.writableDatabase.rawQuery(sql,arg1)

            return cursor.count != 0
        }
    }
}