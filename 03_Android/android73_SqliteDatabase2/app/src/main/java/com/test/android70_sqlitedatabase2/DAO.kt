package com.test.android70_sqlitedatabase2


import android.content.ContentValues
import android.content.Context

class DAO {

    companion object {
        // Create : 저장
        fun insertData(context: Context, data:TestClass ){
            // 컬럼이름과 데이터를 설정하는 객체
            val contentValues = ContentValues()
            // 컬럼 이름, 값을 지정한다.
            contentValues.put("textData", data.textData)
            contentValues.put("intData", data.intData)
            contentValues.put("doubleData", data.doubleData)
            contentValues.put("dateData", data.dateData)

            val dbHelper = DBHelper(context)
            // 데이터를 저장할 테이블의 이름, null값을 어떻게 처리할 것인가(그냥 null 넣어주세요),
            // 저장할 데이터를 가지고 있는 객체
            dbHelper.writableDatabase.insert("TestTable", null, contentValues)
            dbHelper.close()
        }

        // Read Condition : 조건에 맞는 행 하나를 가져온다.
        fun selectData(context: Context, idx:Int):TestClass{

            val dbHelper = DBHelper(context)
            // 첫 번째 : 테이블명
            // 두 번째 : 가지고 오고자 하는 컬럼 이름 목록. null을 넣어주면 모두 가져온다.
            // 세 번째 : 특정 행을 선택하기 위한 조건절
            // 네 번째 : 세 번째에 들어가는 조건절의 ? 에 셋팅될 값 배열
            // 다섯 번째 : Group by의 기준 컬럼
            // 여섯 번째 : Having절에 들어갈 조건절
            // 일곱 번째 : Having절의 ?에 셋팅될 값 배열
            val selection = "idx = ?"
            val args = arrayOf("$idx")
            val cursor = dbHelper.writableDatabase.query("TestTable", null, selection, args, null, null, null)

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

            val dbHelper = DBHelper(context)
            // 첫 번째 : 테이블명
            // 두 번째 : 가지고 오고자 하는 컬럼 이름 목록. null을 넣어주면 모두 가져온다.
            // 세 번째 : 특정 행을 선택하기 위한 조건절
            // 네 번째 : 세 번째에 들어가는 조건절의 ? 에 셋팅될 값 배열
            // 다섯 번째 : Group by의 기준 컬럼
            // 여섯 번째 : Having절에 들어갈 조건절
            // 일곱 번째 : Having절의 ?에 셋팅될 값 배열
            val cursor = dbHelper.writableDatabase.query("TestTable", null, null, null, null, null, null)


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
            // 컬럼과 값을 지정하는 ContentValues 생성한다.
            val cv = ContentValues()
            cv.put("textData", obj.textData)
            cv.put("intData", obj.intData)
            cv.put("doubleData", obj.doubleData)
            cv.put("dateData", obj.dateData)
            // 조건절
            val condition = "idx = ?"
            // ?에 들어갈 값
            val args = arrayOf("${obj.idx}")
            // 수정한다.
            val dbHelper = DBHelper(context)
            // 테이블명, content values, 조건절, ?에 들어갈 값
            dbHelper.writableDatabase.update("TestTable", cv, condition, args)
            dbHelper.close()
        }


        // Delete : 조건 맞는 행을 삭제한다.
        fun deleteData(context:Context, idx:Int){
            // 조건절
            val condition = "idx = ?"
            // ?에 들어갈 값
            val args = arrayOf("$idx")

            val dbHelper = DBHelper(context)
            dbHelper.writableDatabase.delete("TestTable", condition, args)
            dbHelper.close()
        }
    }

}