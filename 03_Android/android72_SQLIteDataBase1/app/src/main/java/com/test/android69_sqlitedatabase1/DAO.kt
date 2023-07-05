package com.test.android69_sqlitedatabase1

import android.content.Context

class DAO {

    companion object {
        // Create : 저장
        fun insertData(context: Context, data:TestClass ){
            // autoincrement가 있는 컬럼은 제외하고 나머지만 지정한다.
            val sql = """insert into TestTable
                | (textData, intData, doubleData, dateData)
                | values (?, ?, ?, ?)
            """.trimMargin()

            // ? 에 설정할 값을 배열에 담아준다.
            val arg1 = arrayOf(
                data.textData, data.intData, data.doubleData, data.dateData
            )
            // 데이터베이스 오픈
            val sqliteDatabase = DBHelper(context)
            // 쿼리 실행 (쿼리문, ?에 셋팅할 값 배열)
            sqliteDatabase.writableDatabase.execSQL(sql, arg1)
            // 데이터 베이스를 닫아준다.
            sqliteDatabase.close()
        }

        // Read Condition : 조건에 맞는 행 하나를 가져온다.
        fun selectData(context: Context, idx:Int):TestClass{
            // 쿼리문
            val sql = "select * from TestTable where idx=?"
            // ?에 들어갈 값 (문열 배열)
            val arg1 = arrayOf("$idx")

            // 데이터베이스 오픈
            val dbHelper = DBHelper(context)
            // 쿼리실행
            val cursor = dbHelper.writableDatabase.rawQuery(sql, arg1)
            cursor.moveToNext()

            // 컬럼의 이름을 지정하여 컬럼의 순서값을 가져온다.
            val idx1 = cursor.getColumnIndex("idx")
            val idx2 = cursor.getColumnIndex("textData")
            val idx3 = cursor.getColumnIndex("intData")
            val idx4 = cursor.getColumnIndex("doubleData")
            val idx5 = cursor.getColumnIndex("dateData")

            // 데이터를 가져온다.
            val idx = cursor.getInt(idx1)
            val textData = cursor.getString(idx2)
            val intData = cursor.getInt(idx3)
            val doubleData = cursor.getDouble(idx4)
            val dateData = cursor.getString(idx5)

            val testClass = TestClass(idx, textData, intData, doubleData, dateData)

            dbHelper.close()
            return testClass
        }

        // Read All : 모든 행을 가져온다
        fun selectAllData(context: Context):MutableList<TestClass>{
            // 모든 행을 가져오는 쿼리문을 작성한다.
            val sql = "select * from TestTable"

            // 데이터베이스 오픈
            val dbHelper = DBHelper(context)
            // 쿼리 실행
            val cursor = dbHelper.writableDatabase.rawQuery(sql, null)
            // cursor 객체는 쿼리문에 맞는 행에 접근할 수 있는 객체가 된다.
            // 처음에는 아무 행도 가르치고 있지 않는다.
            // moveToNext 메서드를 호출하면 다음 행에 접근할 수 있다.
            // 이때 접근할 행이 있으면 true를 반환하고 없으면  false를 반환한다.

            val dataList = mutableListOf<TestClass>()

            while(cursor.moveToNext()){
                // 컬럼의 이름을 지정하여 컬럼의 순서값을 가져온다.
                val idx1 = cursor.getColumnIndex("idx")
                val idx2 = cursor.getColumnIndex("textData")
                val idx3 = cursor.getColumnIndex("intData")
                val idx4 = cursor.getColumnIndex("doubleData")
                val idx5 = cursor.getColumnIndex("dateData")

                // 데이터를 가져온다.
                val idx = cursor.getInt(idx1)
                val textData = cursor.getString(idx2)
                val intData = cursor.getInt(idx3)
                val doubleData = cursor.getDouble(idx4)
                val dateData = cursor.getString(idx5)

                val testClass = TestClass(idx, textData, intData, doubleData, dateData)
                dataList.add(testClass)
            }

            dbHelper.close()

            return dataList
        }
        // Update : 조건에 맞는 행의 컬럼의 값을 수정한다.
        fun updateData(context:Context, obj:TestClass){
            // 쿼리문
            // idx가 ? 인 행의 textData, intData, doubleData, dateData 컬럼의 값을 변경한다
            val sql = """update TestTable
                | set textData=?, intData=?, doubleData=?, dateData=?
                | where idx=?
            """.trimMargin()

            // ? 에 들어갈 값
            val args = arrayOf(obj.textData, obj.intData, obj.doubleData, obj.dateData, obj.idx)
            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }


        // Delete : 조건 맞는 행을 삭제한다.
        fun deleteData(context:Context, idx:Int){
            // 쿼리문
            // TestTable에서 idx가 ? 인 행을 삭제한다.
            val sql = "delete from TestTable where idx = ?"
            // ?에 들어갈 값
            val args = arrayOf(idx)
            // 쿼리 실행
            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.execSQL(sql, args)
            dbHelper.close()
        }
    }

}