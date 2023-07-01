import java.util.*

class MainMenuClass(var scanner: Scanner){

    // 메인 메뉴를 보여주고 메뉴 번호를 입력받는다.
    fun inputMainMenuNumber() : Int{
        while(true) {
            try {
                println()
                println("메뉴를 선택해주세요")
                println("1. 오늘의 운동 기록")
                println("2. 날짜별 운동 기록 보기")
                println("3. 종료")
                print("메뉴 번호를 입력해주세요 : ")
                val inputNumberTemp = scanner.next()
                val inputNumber = inputNumberTemp.toInt()


                if (inputNumber !in 1..3) {
                    println("잘못 입력 하였습니다")
                } else {
                    return inputNumber
                }
            } catch(e:Exception){
                println("잘못 입력 하였습니다")
            }

        }
    }
}

// 메인 메뉴 항목
public enum class MainMenuItem(val itemNumber:Int){
    // 오늘의 운동 기록
    MAIN_MENU_ITEM_WRITE_TODAY_RECORD(1),
    // 날짜별 운동 기록 보기
    MAIN_MENU_ITEM_SHOW_RECORD(2),
    // 종료
    MAIN_MENU_ITEM_EXIT(3)
}



