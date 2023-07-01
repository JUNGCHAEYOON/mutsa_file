import java.lang.Exception
import java.util.*

class MainMenuClass(val scanner : Scanner) {
    fun inputMainMenuNumber(): Int {
        while(true){
            try{
                println()
                println("메뉴를 선택해주세요!")
                println("1. 오늘의 운동 기록")
                println("2. 날짜별 운동 기록 보기")
                println("3. 종료")

                print("메뉴 번호를 입력해주세요 : ")
                val inputNumberTemp = scanner.next()
                val inputNumber= inputNumberTemp.toInt()

                if(inputNumber !in 1 .. 3){
                    println("잘못 입력 하셨습니다.")
                }else{
                    return inputNumber
                }

            }catch(e : Exception){
                // toInt 를 실행할 수 없는 값이 inputNumberTemp
                // 로 들어온 경우 예외 발생!
                println("잘못된 입력!")
            }
        }
    }
}

enum class MainMenuItem(val itemNumber : Int){
    // 1. 오늘의 운동기록
    MAIN_MENU_ITEM_WRITE_TODAY_RECORD(1),
    // 2. 날짜별 운동기록 보기
    MAIN_MENU_ITEM_SHOW_RECORD(2),
    // 3. 종료
    MAIN_MENU_ITEM_EXIT(3)
}