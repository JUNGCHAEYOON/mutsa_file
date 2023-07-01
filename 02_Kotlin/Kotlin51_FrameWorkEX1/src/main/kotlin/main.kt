import java.util.Scanner

fun main(){
    val mainClass = MainClass()
    mainClass.running()
}

// 메인 클래스
class MainClass{

    val scanner = Scanner(System.`in`)
    val studentList = mutableListOf<StudentClass>()

    // 초기 상태를 학생 정보를 입력받는 상태로 설정한다.
    var programState = ProgramState.PROGRAM_STATE_INPUT_STUDENT_INFO

    // 프로그램 상태 전체를 관리하며 운영하는 메서드
    fun running(){
        while(true) {
            // 프로그램 상태에 따른 분기
            when (programState) {
                // 학생 정보를 입력받는 상태
                ProgramState.PROGRAM_STATE_INPUT_STUDENT_INFO -> {
                    inputStudentInfoState()
                    // 상태를 변경한다
                    programState = ProgramState.PROGRAM_STATE_PRINT_STUDENT_INFO
                }
                // 학생 정보를 출력하는 상태
                ProgramState.PROGRAM_STATE_PRINT_STUDENT_INFO -> {
                    printStudentInfo()
                    break
                }
            }
        }
    }

    // 학생 정보를 입력받는 함수
    fun inputStudentInfo(){
        println()
        print("이름 : ")
        val name = scanner.next()
        print("국어 : ")
        val korean = scanner.nextInt()

        val studentClass = StudentClass(name, korean)
        studentList.add(studentClass)
    }

    // 학생 정보 입력 상태 처리 메서드
    fun inputStudentInfoState(){
        while(true){
            println()
            print("학생 정보를 입력 하시 겠습니까? (1. 입력, 2. 입력종료) : ")
            val inputNumber = scanner.nextInt()
            when(inputNumber){
                1 -> {
                    inputStudentInfo()
                }
                2 -> {
                    break
                }
            }
        }
    }

    // 학생 전체의 정보를 출력하는 함수
    fun printStudentInfo(){
        // 학생의 수 만큼 반복한다.
        for(student in studentList){
            println()
            student.printInfo()
        }
    }
}

// 프로그램 상태를 나타내는 enum
enum class ProgramState{
    // 학생 정보를 입력받는 상태
    PROGRAM_STATE_INPUT_STUDENT_INFO,
    // 학생 정보를 출력하는 상태
    PROGRAM_STATE_PRINT_STUDENT_INFO
}

// 학생 정보를 관리하는 클래스
data class StudentClass(var name:String, var korean:Int){

    fun printInfo(){
        println()
        println("이름 : $name")
        println("국어 : $korean")
    }
}




