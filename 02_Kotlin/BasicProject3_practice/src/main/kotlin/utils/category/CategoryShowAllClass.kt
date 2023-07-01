package utils.category

import main.Category
import main.MainClass
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.util.*

class CategoryShowAllClass(var scanner: Scanner, var mainClass: MainClass) {

    // 가장 큰 범주의 리스트
    var cl = ArrayList<Category>()

    fun showAll() : Boolean{
        while(true){
            try {
                println("==============================")
                // 카테고리가 있으면 보여주기
                // 없으면 등록한 카테고리가 없습니다.
                getCategoryListClass()

                for(category in cl){
                    println("------------------------------")
                    println(category.categoryName)
                    println("------------------------------")

                    // 등록된 메모가 없는경우
                    if(category.MemoList.isEmpty()){
                        println()
                        println("등록된 메모가 없습니다.")
                    }else{
                        for(memo in category.MemoList){
                            println()
                            println("제목 : ${memo.title}")
                            println("내용 : ${memo.content}")
                        }
                    }
                    println()
                }
                // 다보여주고 실행 종료
                return true
            }catch (e :Exception){
                println("ERR : 잘못 입력 하였습니다")
            }
        }
    }

    // 카테고리 리스트 클래스 객체를 불러오기
    private fun getCategoryListClass() {
        val file = File("카테고리리스트")
        if (file.exists()) {
            try {
                val fis = FileInputStream("카테고리리스트")
                val bis = BufferedInputStream(fis)
                val ois = ObjectInputStream(bis)

                val temp = ois.readObject()
                cl = temp as ArrayList<Category>

                ois.close()
                bis.close()
                fis.close()
            } catch (e: java.lang.Exception) {
                println("역직렬화 실패")
            }
        } else {
            println("생성된 파일이 없습니다.")
        }
    }
}