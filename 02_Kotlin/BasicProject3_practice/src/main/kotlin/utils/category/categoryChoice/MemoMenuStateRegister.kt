package utils.category.categoryChoice

import main.Category
import main.Memo
import java.util.*

class MemoMenuStateRegister(var scanner : Scanner) {
    fun register(category : Category) : Category {
        while(true){
            try {
                scanner.nextLine()
                print("메모 제목 : ")
                var title = scanner.nextLine()

                print("메모 내용 : ")
                var content = scanner.nextLine()

                var memo = Memo(title, content)

                category.MemoList.add(memo)
                return category
            }catch (e : Exception){
                println("ERR : 잘못 입력 하였습니다")
            }
        }
    }
}