import java.io.Serializable
import java.util.Scanner
import kotlin.system.exitProcess

fun main(){
     val mainClass = MainClass()
     mainClass.running()
}

//메뉴를 선택해주세요
//오늘의 운동 기록
//날짜별 운동 기록 보기
//종료
//번호 입력 :
//
//1번)
//운동 종류 : 숨쉬기
//횟수 : 5000000
//세트 : 3
//
//1번)
//운동 종류 : 걷기
//횟수 : 5000000
//세트 : 10
//
//2번)
//1 : 2023-05-22
//2 : 2023-05-23
//3 : 2023-05-24
//날짜를 선택해주세요(0 . 이전) : 1
//
//운동 종류 : 숨쉬기
//횟수 : 5000000
//세트 : 3
//
//운동 종류 : 걷기
//횟수 : 5000000
//세트 : 10

// 메인 클래스
class MainClass{

    val scanner = Scanner(System.`in`)

    // 각 상태별 객체를 생성한다.
    val mainMenuClass = MainMenuClass(scanner)
    val inputRecordClass = InputRecordClass(scanner, this)
    val showRecordClass = ShowRecordClass(scanner, this)

    // 프로그램 상태를 담는 변수에 초기 상태를 설정한다.
    var programState = ProgramState.PROGRAM_STATE_SHOW_MENU

    // 기록된 운동을 보는 상태 변수
    lateinit var showRecordState:ShowRecordState

    // 운동 기록 정보를 담을 클래스
    data class RecordClass(var type:String, var count:Int, var set:Int) :Serializable

    // 운동 기록을 담을 리스트
    val recordList = mutableListOf<RecordClass>()
    // 파일 목록을 담을 리스트
    val recordFileList = mutableListOf<String>()


    // 프로그램 상태 전체를 관리하며 운영하는 메서드
    fun running(){
        while(true) {
            // 프로그램 상태에 따른 분기
            when (programState) {
                // 메인 메뉴를 보여주는 상태
                ProgramState.PROGRAM_STATE_SHOW_MENU ->{
                    // 메인 메뉴를 보여준다.
                    val inputMainMenuNumber = mainMenuClass.inputMainMenuNumber()
                    // println(inputMainMenuNumber)
                    // exitProcess(0)

                    when(inputMainMenuNumber){
                        // 오늘의 운동 기록 메뉴
                        MainMenuItem.MAIN_MENU_ITEM_WRITE_TODAY_RECORD.itemNumber -> {
                            // 운동 기록 상태로 바꾼다.
                            programState = ProgramState.PROGRAM_STATE_WRITE_TODAY_RECORD
                        }
                        // 운동 기록 보기 메뉴
                        MainMenuItem.MAIN_MENU_ITEM_SHOW_RECORD.itemNumber -> {
                            // 운동 기록 보기 상태로 바꾼다.
                            programState = ProgramState.PROGRAM_STATE_SHOW_RECORD
                            // 날짜별 운동 기록을 보는 상태의 세부 상태를 설정한다.
                            showRecordState = ShowRecordState.SHOW_RECORD_STATE_SELECT_DATE
                        }
                        // 종료 메뉴
                        MainMenuItem.MAIN_MENU_ITEM_EXIT.itemNumber -> {
                            // 종료 상태로 바꾼다.
                            programState = ProgramState.PROGRAM_STATE_EXIT
                        }
                    }
                }
                // 오늘의 운동을 기록하는 상태
                ProgramState.PROGRAM_STATE_WRITE_TODAY_RECORD -> {
                    // 오늘의 운동을 기록하는 메서드를 호출한다
                    inputRecordClass.inputTodayRecord()
                    // 메인 메뉴 상태로 바꾼다.
                    programState = ProgramState.PROGRAM_STATE_SHOW_MENU
                }
                // 날짜별 운동 기록을 보는 상태
                ProgramState.PROGRAM_STATE_SHOW_RECORD -> {

                    // 날짜별 운동 기록 상태의 세부 상태로 분기한다.
                    when(showRecordState){
                        // 기록한 날짜 목록을 보여주고 선택한다.
                        ShowRecordState.SHOW_RECORD_STATE_SELECT_DATE -> {
                            val inputNumber = showRecordClass.selectRecordDay()
                            if(inputNumber == 0){
                                programState = ProgramState.PROGRAM_STATE_SHOW_MENU
                            } else {
                                showRecordState = ShowRecordState.SHOW_RECORD_STATE_SHOW_RECORD
                            }
                        }
                        // 선택한 날짜의 운동 기록을 보여준다.
                        ShowRecordState.SHOW_RECORD_STATE_SHOW_RECORD -> {
                            // 선택한 날짜의 운동 기록을 보여준다.
                            showRecordClass.showSelectedRecord()
                            // 날짜 선택 상태로 바꾼다.
                            showRecordState = ShowRecordState.SHOW_RECORD_STATE_SELECT_DATE
                        }
                    }
                }
                // 종료
                ProgramState.PROGRAM_STATE_EXIT -> {
                    // 프로그램을 강제 종료시킨다
                    // 0 : 정상 종료를 나타내는 코드
                    println("프로그램을 종료합니다")
                    exitProcess(0)
                }
            }
        }
    }
}

// 프로그램 상태를 나타내는 enum
enum class ProgramState{
    // 메인 메뉴를 보여주는 상태
    PROGRAM_STATE_SHOW_MENU,
    // 오늘의 운동을 기록하는 상태
    PROGRAM_STATE_WRITE_TODAY_RECORD,
    // 날짜별 운동 기록을 보는 상태
    PROGRAM_STATE_SHOW_RECORD,
    // 종료
    PROGRAM_STATE_EXIT
}