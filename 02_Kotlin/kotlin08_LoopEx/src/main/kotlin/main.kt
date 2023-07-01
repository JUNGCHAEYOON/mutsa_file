import java.util.Scanner

//학생수를 입력 받는다.
//입력받은 학생의 수 만큼 반복하며
//학생의 이름, 학년, 국어, 영어, 수학 점수를 받는다.
//입력이 완료되면
//각 학생들의 정보를 출력하고
//각 과목별 총점과 평균을 출력한다.
//학생의 정보는 각각을 ArrayList를 만들어서 각각 담아준다.

// 점수를 담을 ArrayList
val nameList = ArrayList<String>()
val gradeList = ArrayList<Int>()
val koreanList = ArrayList<Int>()
val englishList = ArrayList<Int>()
val mathList = ArrayList<Int>()

val Scanner = Scanner(System.`in`)

fun main(){
    // 학생 수를 입력받는다.
    val studentCount = inputStudentCount()
    // println(studentCount)
    // 학생 정보를 입력받는다.
    inputStudentInfo(studentCount)
    // 학생 정보를 출력한다.
    printStudentInfo(studentCount)
    // 과목별 총점과 평균을 출력한다.
    printTotalAvg(studentCount)
}

// 학생 수를 입력받는 함수
fun inputStudentCount(): Int {
    print("학생 수를 입력해주세요 : ")
    return Scanner.nextInt()
}
// 학생의 정보를 입력받는 함수
fun inputStudentInfo(studentCount:Int){
    for (a1 in 1..studentCount){
        print("이름 : ")
        val name = Scanner.next()
        print("학년 : ")
        val grade = Scanner.nextInt()
        print("국어 : ")
        val korean = Scanner.nextInt()
        print("영어 : ")
        val english = Scanner.nextInt()
        print("수학 : ")
        val math = Scanner.nextInt()

        nameList.add(name)
        gradeList.add(grade)
        koreanList.add(korean)
        englishList.add(english)
        mathList.add(math)
    }
}
// 학생들의 정보를 출력하는 함수
fun printStudentInfo(studentCount: Int){
    for(a1 in 1..studentCount){
        println("이름 : ${nameList[a1 - 1]}")
        println("학년 : ${gradeList[a1 - 1]}")
        println("국어 : ${koreanList[a1 - 1]}")
        println("영어 : ${englishList[a1 - 1]}")
        println("수학 : ${mathList[a1 - 1]}")
    }
}
// 각 과목별 총점과 평균을 출력하는 함수
fun printTotalAvg(studentCount: Int){
    // 과목별 점수를 담을 변수
    var koreanTotal = 0
    var englishTotal = 0
    var mathTotal = 0

    for(a1 in 1..studentCount){
        koreanTotal += koreanList[a1 - 1]
        englishTotal += englishList[a1 - 1]
        mathTotal += mathList[a1 - 1]
    }

    // 과목별 평균을 구한다.
    val koreanAvg = koreanTotal / studentCount
    val englishAvg = englishTotal / studentCount
    val mathAvg = mathTotal / studentCount

    // 출력한다
    println("국어 총점 : $koreanTotal")
    println("영어 총점 : $englishTotal")
    println("수학 총점 : $mathTotal")
    println("국어 평균 : $koreanAvg")
    println("영어 평균 : $englishAvg")
    println("수학 평균 : $mathAvg")
}
