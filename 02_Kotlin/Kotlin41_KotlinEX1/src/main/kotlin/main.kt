import java.util.Scanner

fun main(){
//    학교 클래스
//
//    학생은 이름, 국어, 영어, 수학, 총점, 평균
//
//    프로그램이 시작하면 다음과 같이 입력을 받는다.
//    학생 정보를 입력해주세요
//    이름 : 홍길동
//    국어 : 100
//    영어 : 100
//    수학 : 100
//    계속 입력하시겠습니까?(1. 예, 2. 아니오) :
//
//    각 학생들의 정보를 모두 출력한다.
//    이름 : 홍길동
//    국어 : 100
//    영어 : 100
//    수학 : 100
//    총점 : 300
//    평균 : 100
//
//    ....
//
//    국어 전체 총점 : 000점
//    영어 전체 총점 : 000점
//    수학 전체 총점 : 000점
//    국어 전체 평균 : 000점
//    영어 전체 평균 : 000점
//    수학 전체 평균 : 000점
//
//    조건 : let, run, apply, with, also 중에 사용하고 싶은 것을
//    적극적으로 활용해주세요.
//    그 밖에 오늘 배운 것을 써먹을 곳이 있다면 써먹어 주세요

//    val school1 = SchoolClass1()
//    school1.inputStudentInfo()
//    school1.printStudentInfo()
//    school1.printAgg()

    val school2 = SchoolClass2()
    school2.inputStudentInfo()
    school2.printStudentInfo()
    school2.printAgg()
}

// 학교 클래스 - data class 사용
class SchoolClass1{

    val scanner = Scanner(System.`in`)
    val studentList = ArrayList<StudentClass1>()

    // 학생 정보 입력
    fun inputStudentInfo(){
        while(true){
            println("학생 정보를 입력해주세요")
            print("이름 : ")
            val name = scanner.next()
            print("국어 : ")
            val korean = scanner.nextInt()
            print("영어 : ")
            val english = scanner.nextInt()
            print("수학 : ")
            val math = scanner.nextInt()

            val student = StudentClass1(name,  korean, english, math).apply {
                totalPoint = korean + english + math
                avgPoint = totalPoint / 3
            }

            // println(student)
            // println(student.totalPoint)
            // println(student.avgPoint)

            studentList.add(student)

            print("계속 입력하시겠습니까?(1. 예, 2. 아니오) : ")
            val inputNumber = scanner.nextInt()

            if(inputNumber == 2){
                break
            }

//    계속 입력하시겠습니까?(1. 예, 2. 아니오) :
        }
    }
    
    // 학생들의 정보를 출력
    fun printStudentInfo(){
        // 학생의 수 만큼 반복한다.
        for(student in studentList){
            // 객체 분해를 통해 값을 가져온다.
            val (name, korean, english, math) = student

            println("이름 : $name")
            println("국어 : $korean")
            println("영어 : $english")
            println("수학 : $math")
            println("총점 : ${student.totalPoint}")
            println("평균 : ${student.avgPoint}")
            println()
        }
    }
    
    // 과목별 총점과 평균을 구한다
    fun printAgg(){
        var koreanTotal = 0
        var englishTotal = 0
        var mathTotal = 0
        var koreanAvg = 0
        var englishAvg = 0
        var mathAvg = 0

        // 각 과목별 총점을 구한다.
        for(student in studentList){
            koreanTotal += student.korean
            englishTotal += student.english
            mathTotal += student.math
        }
        // 각 과목별 평균을 구한다
        koreanAvg = koreanTotal / studentList.size
        englishAvg = englishTotal / studentList.size
        mathAvg = mathTotal / studentList.size

        println("국어 전체 총점 : $koreanTotal")
        println("영어 전체 총점 : $englishTotal")
        println("수학 전체 총점 : $mathTotal")
        println("국어 전체 평균 : $koreanAvg")
        println("영어 전체 평균 : $englishAvg")
        println("수학 전체 평균 : $mathAvg")
    }
}

// 학생 클래스
data class StudentClass1(var name:String, var korean:Int, var english:Int, var math:Int){
    // 학생은 이름, 국어, 영어, 수학, 총점, 평균
    var totalPoint:Int = 0
    var avgPoint:Int = 0
}

// 학교 클래스 - 중첩 클래스 사용
class SchoolClass2 {

    val scanner = Scanner(System.`in`)
    val studentList = ArrayList<StudentClass2>()

    // 학생 정보 입력
    fun inputStudentInfo(){
        while(true){
            val student = StudentClass2()
            student.inputStudentInfo()

            studentList.add(student)

            print("계속 입력하시겠습니까?(1. 예, 2. 아니오) : ")
            val inputNumber = scanner.nextInt()

            if(inputNumber == 2){
                break
            }
        }
    }

    // 학생들의 정보를 출력
    fun printStudentInfo(){
        // 학생의 수 만큼 반복한다.
        for(student in studentList){

            student.printStudentInfo()

            println()
        }
    }

    // 과목별 총점과 평균을 구한다
    fun printAgg(){
        var koreanTotal = 0
        var englishTotal = 0
        var mathTotal = 0
        var koreanAvg = 0
        var englishAvg = 0
        var mathAvg = 0

        // 각 과목별 총점을 구한다.
        for(student in studentList){
            koreanTotal += student.korean
            englishTotal += student.english
            mathTotal += student.math
        }
        // 각 과목별 평균을 구한다
        koreanAvg = koreanTotal / studentList.size
        englishAvg = englishTotal / studentList.size
        mathAvg = mathTotal / studentList.size

        println("국어 전체 총점 : $koreanTotal")
        println("영어 전체 총점 : $englishTotal")
        println("수학 전체 총점 : $mathTotal")
        println("국어 전체 평균 : $koreanAvg")
        println("영어 전체 평균 : $englishAvg")
        println("수학 전체 평균 : $mathAvg")
    }

    inner class StudentClass2{
        lateinit var name:String
        var korean = 0
        var english = 0
        var math = 0
        var totalPoint = 0
        var avgPoint = 0

        fun inputStudentInfo(){
            println("학생 정보를 입력해주세요")
            print("이름 : ")
            name = scanner.next()
            print("국어 : ")
            korean = scanner.nextInt()
            print("영어 : ")
            english = scanner.nextInt()
            print("수학 : ")
            math = scanner.nextInt()

            totalPoint = korean + english + math
            avgPoint = totalPoint / 3
        }

        fun printStudentInfo(){
            println("이름 : $name")
            println("국어 : $korean")
            println("영어 : $english")
            println("수학 : $math")
            println("총점 : $totalPoint")
            println("평균 : $avgPoint")
        }

    }
}






