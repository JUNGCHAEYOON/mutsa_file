package utils.category.categoryChoice

import main.Category
import main.Memo
import java.util.*

class MemoMenuStateCorrect(var scanner : Scanner) {
    fun correct(category: Category): Category {
        while(true){
            try {
                print("수정할 메모의 번호를 입력해주세요(0. 이전) : ")
                val temp = scanner.next()
                val inputNumber = temp.toInt()
                if(inputNumber == 0){
                    return category
                }else{
                    if(inputNumber > category.MemoList.size || inputNumber < 0){
                        println("ERR : 잘못 입력 하였습니다")
                    }else{
                        val index = inputNumber - 1
                        val bufferMemo = category.MemoList[index]
                        
                        // 제목 수정
                        println("제목 : ${bufferMemo.title}")
                        scanner.nextLine()
                        print("메모의 새로운 제목을 입력해주세요(0 입력시 무시합니다.) : ")
                        val temp2 = scanner.next()
                        var newTitle : String
                        if(temp2 != "0"){
                            newTitle = temp2.toString()
                        }else{
                            newTitle = bufferMemo.title
                        }
                        
                        // 내용 수정
                        println("내용 : ${bufferMemo.content}")
                        scanner.nextLine()
                        print("메모의 새로운 내용을 입력해주세요(0 입력시 무시합니다.) : ")
                        val temp3 = scanner.next()
                        var newContent : String
                        if(temp3 != "0"){
                            newContent = temp3.toString()
                        }else{
                            newContent = bufferMemo.content
                        }
                        var correctedMemo = Memo(newTitle,newContent)
                        
                        category.MemoList[index] = correctedMemo
                        
                        // 종료
                        return category
                    }
                }
            }catch (e : Exception){
                println("ERR : 잘못 입력 하였습니다")
            }
        }
    }
}