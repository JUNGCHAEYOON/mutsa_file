import java.io.DataInputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

//메뉴를 선택해주세요
//1. 오늘의 운동 기록
//2. 날짜별 운동 기록 보기
//3. 종료
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
//
//
//## 다시 이전 메뉴 반복

fun main() {
    var workout = Workout()
    workout.start()
}

class Workout{
    var scanner = Scanner(System.`in`)

    fun start(){
        while(true){
            println("##############################")
            print("메뉴를 선택해주세요\n" +
                    "1. 오늘의 운동 기록\n" +
                    "2. 날짜별 운동 기록 보기\n" +
                    "3. 종료\n" +
                    "번호 입력 : ")
            var input = scanner.nextInt()
            scanner.nextLine()
            if(input == 3 ){
                println("프로그램 종료")
                break
            }
            if(input == 1) todayRecord()
            if(input == 2) seeAll()
        }
    }

    fun todayRecord(){
        println()
        print("운동 종류 : ")
        var what = scanner.nextLine()
        print("횟수 : ")
        var rep = scanner.nextInt()
        print("세트 : ")
        var set = scanner.nextInt()

        // 현재날짜
        val date = LocalDate.now()
        var record = Record(date, what, rep, set)

        val fos = FileOutputStream("data.dat",true)
        val oos = ObjectOutputStream(fos)

        oos.writeObject(record)

        oos.flush()
        oos.close()
    }

    fun seeAll() {
        val fis = FileInputStream("data.dat")
        val ois = ObjectInputStream(fis)

        var record = ois.readObject() as Record
//        println(record)

        ois.close()
        fis.close()

//        dateList.distinct()
//        var i = 1
//        println()
//        dateList.forEach{
//            println("$i : ${it}")
//            i++
//        }

        print("날짜를 선택해주세요(0 . 이전) : ")
        var input = scanner.nextInt()

        // 0이면 종료
        if(input == 0) return

        // 0이 아니면


    }

    data class Record(val date : LocalDate,
                      val what : String,
                      val rep : Int,
                      val set : Int) : Serializable
}