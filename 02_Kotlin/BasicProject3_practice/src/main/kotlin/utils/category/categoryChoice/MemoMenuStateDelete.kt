package utils.category.categoryChoice

import main.Category
import main.Memo
import java.util.*

class MemoMenuStateDelete(var scanner: Scanner) {
    fun delete(category: Category) : Category{
        while(true){
            try {
                print("삭제할 메모의 번호를 입력해주세요(0. 이전) : ")
                val temp = scanner.next()
                val inputNumber = temp.toInt()
                if(inputNumber == 0){
                    return category
                }else{
                    if(inputNumber > category.MemoList.size || inputNumber < 0){
                        println("ERR : 잘못 입력 하였습니다")
                    }else{
                        val index = inputNumber-1
                        category.MemoList.removeAt(index)

                        //종료
                        return category
                    }
                }
            }catch (e : Exception){
                println("ERR : 잘못 입력 하였습니다")
            }
        }
    }
}