package utils

import java.lang.Exception
import java.util.*

class MenuClass(var scanner : Scanner) {
    fun menu(): Int {
        while(true){
            try {
                println("==============================")
                println("1. 메모 카테고리 관리")
                println("2. 메모 카테고리 선택")
                println("3. 메모 내용 전체 보기")
                println("4. 종료")
                print("메뉴를 선택해 주세요 : ")
                var inputTemp1 = scanner.next()
                var inputNumber = inputTemp1.toInt()

                if(inputNumber !in 1..4) {
                    println("ERR : 잘못 입력 하였습니다")
                }else{
                    return inputNumber
                }

            }catch (e:Exception){
                println("ERR : 잘못 입력 하였습니다")
            }
        }
    }
}

public enum class MenuState(var num : Int){
    // 1. 메모 카테고리 관리
    MENU_STATE_CATEGORY_MANAGEMENT(1),
    // 2. 메모 카테고리 선택
    MENU_STATE_CATEGORY_CHOICE(2),
    // 3. 메모 내용 전체 보기
    MENU_STATE_SHOWALL(3),
    // 4. 종료
    MENU_STATE_EXIT(4)
}