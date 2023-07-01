package utils.category.categoryChoice

import main.Category
import main.Memo
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class MemoMenuStateShow(var scanner: Scanner) {

    var bufferMemoList = ArrayList<Memo>()

    fun show(category: Category) {
        while (true) {
            //try catch
            try {
                // 인자로 받은 category의 memoList 를 저장
                bufferMemoList = category.MemoList

                print("확인할 메모의 번호를 입력해주세요(0. 이전) : ")
                val temp = scanner.next()
                val inputNumber = temp.toInt()

                // 0이면 되돌아 가기
                if (inputNumber == 0) {
                    return
                } else {
                    if (inputNumber > bufferMemoList.size || inputNumber < 0) {
                        println("해당 인덱스는 없습니다.")
                        return
                    }
                    val index = inputNumber - 1
                    println()
                    println("메모 제목 : ${bufferMemoList[index].title}")
                    println("메모 내용 : ${bufferMemoList[index].content}")
                    print("이전으로 돌아가려면 0을 입력하세요 : ")
                    val temp2 = scanner.next()
                    val inputNumber2 = temp2.toInt()
                    if (inputNumber2 == 0) {
                        //0이면 돌아가기
                        return
                    } else {
                        println("ERR : 잘못 입력 하였습니다")
                    }
                }
            } catch (e: Exception) {
                println("ERR : 잘못 입력 하였습니다")
            }
        }

    }

}