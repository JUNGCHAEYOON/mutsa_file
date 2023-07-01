import java.io.Serializable
import java.util.*
import kotlin.system.exitProcess

fun main(){
    val mainClass = MainClass()
    mainClass.running()
}

class MainClass {
    // 스캐너
    val scanner = Scanner(System.`in`)

    // 프로그램 상태
    // 시작은 메인 메뉴 보여주기로 설정
    var programState = ProgramState.PROGRAM_STATE_SHOW_MENU

    // 각 상태별 객체 생성
    val mainMainClass = MainMenuClass(scanner)
    val inputRecordClass = InputRecordClass(scanner,this)
    val showRecordClass = ShowRecordClass(scanner, this)

    // 운동 기록을 담을 리스트
    data class RecordClass(var type : String, var count : Int, var set : Int) : Serializable
    val recordList = mutableListOf<RecordClass>()
    // 파일 목록을 담을 리스트
    val recordFileList = mutableListOf<String>()

    // 기록된 운동을 보는 상태 변수
    lateinit var showRecordState: ShowRecordState

    fun running(){
        while(true){
            when(programState){
                // 0. 메인 메뉴 보여주기(시작)
                ProgramState.PROGRAM_STATE_SHOW_MENU->{
                    //0.0 메인 메뉴 보여주기
                    val inputMainMenuNumber = mainMainClass.inputMainMenuNumber()

                    //0.1 받아온 값을 이용해 1~3 으로 이동
                    when(inputMainMenuNumber){
                        MainMenuItem.MAIN_MENU_ITEM_WRITE_TODAY_RECORD.itemNumber->{
                            //1번 입력
                            programState = ProgramState.PROGRAM_STATE_WRITE_TODAY_RECORD
                        }
                        MainMenuItem.MAIN_MENU_ITEM_SHOW_RECORD.itemNumber->{
                            //2번 입력
                            programState = ProgramState.PROGRAM_STATE_SHOW_RECORD
                        }
                        MainMenuItem.MAIN_MENU_ITEM_EXIT.itemNumber->{
                            //3번 입력
                            programState = ProgramState.PROGRAM_STATE_EXIT
                        }
                    }
                }
                // 1. 오늘의 운동기록
                ProgramState.PROGRAM_STATE_WRITE_TODAY_RECORD->{
                    inputRecordClass.inputTodayRecord()
                    programState = ProgramState.PROGRAM_STATE_SHOW_MENU
                }
                // 2. 날짜별 운동기록 조회
                ProgramState.PROGRAM_STATE_SHOW_RECORD->{
                    when(showRecordState){
                        ShowRecordState.SHOW_RECORD_STATE_SELECT_DATE->{
                            val inputNumber = showRecordClass.selectRecordDay()
                            if(inputNumber == 0){
                                programState = ProgramState.PROGRAM_STATE_SHOW_MENU
                            } else {
                                showRecordState = ShowRecordState.SHOW_RECORD_STATE_SHOW_RECORD
                            }
                        }
                        ShowRecordState.SHOW_RECORD_STATE_SHOW_RECORD->{
                            // 선택한 날짜의 운동 기록을 보여준다.
                            showRecordClass.showSelectedRecord()
                            // 날짜 선택 상태로 바꾼다.
                            showRecordState = ShowRecordState.SHOW_RECORD_STATE_SELECT_DATE
                        }
                    }
                }
                // 3. 종료
                ProgramState.PROGRAM_STATE_EXIT->{
                    println("프로그램 종료!")
                    exitProcess(0)
                }
            }
        }
    }
}

enum class ProgramState{
    // 메인 메뉴 보여주기(시작)
    PROGRAM_STATE_SHOW_MENU,

    // 1. 오늘의 운동기록
    PROGRAM_STATE_WRITE_TODAY_RECORD,

    // 2. 날짜별 운동기록 조회
    PROGRAM_STATE_SHOW_RECORD,

    // 3. 종료
    PROGRAM_STATE_EXIT
}
