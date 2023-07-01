fun main(){
    val mainClass = MainClass()
    mainClass.running()
}

// 프로그램 상태를 나타내는 enum
enum class ProgramState{
    // 상태를 나타내는 값들을 정의한다.
    PROGRAM_STATE_TEST1,
    PROGRAM_STATE_TEST2
}

// 메인 클래스
class MainClass{
    // 프로그램 상태를 담는 변수에 초기 상태를 설정한다.
    var programState = ProgramState.PROGRAM_STATE_TEST1

    // 프로그램 상태 전체를 관리하며 운영하는 메서드
    fun running(){
        while(true) {
            // 프로그램 상태에 따른 분기
            when (programState) {
                ProgramState.PROGRAM_STATE_TEST1 -> {
                    // PROGRAM_STATE_TEST1 상태의 코드를 작성해주세요...
                    break
                }

                ProgramState.PROGRAM_STATE_TEST2 -> {
                    // PROGRAM_STATE_TEST2 상태의 코드를 작서해주세요...
                    break
                }
            }
        }
    }
}