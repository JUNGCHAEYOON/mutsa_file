import java.io.File
import java.util.Scanner

class ShowRecordClass(val scanner: Scanner, val mainClass: MainClass) {
    fun selectRecordDay(): Int {
        while (true) {
            try {
                println()
                // 등록된 날짜 목록을 출력한다.
                getRecordFileList()

                print("날짜를 선택해주세요(0. 이전) : ")

                val temp1 = scanner.next()
                val inputNumber = temp1.toInt()
                if (inputNumber !in 0..4) {
                    println("잘못 입력하였습니다")
                } else {
                    return inputNumber
                }
            } catch (e: Exception) {
                println("잘못 입력하였습니다.")
            }
        }
    }

    // 선택한 날짜의 운동 기록을 보여주는 메서드
    fun showSelectedRecord() {
        println()
        println("2023-05-23의 운동 기록입니다")

        println()
        println("운동 타입 : 운동으하하")
        println("횟수 : 100")
        println("세트 : 500")

        println()
        println("운동 타입 : 운동으하하")
        println("횟수 : 100")
        println("세트 : 500")

        println()
        println("운동 타입 : 운동으하하")
        println("횟수 : 100")
        println("세트 : 500")
    }

    private fun getRecordFileList() {
        // 파일 목록 리스트를 초기화
        mainClass.recordFileList.clear()

        // 현재 위치의 파일 목록을 가져옴
        val dir = File(".")
        val fileList = dir.list()

        // 파일 목록에서 .record로 끝나는 것들만 담아준다.
        for (temp1 in fileList) {
            if (temp1.endsWith(".record")) {
                val temp2 = temp1.replace(".record", "")
                mainClass.recordFileList.add(temp2)
            }
        }

        // 출력한다.
        if (mainClass.recordFileList.size == 0) {
            println("기록 없음")
        } else {
            for (idx1 in 1..mainClass.recordList.size) {
                println("$idx1 : ${mainClass.recordFileList[idx1 - 1]}")
            }
        }
    }


}

enum class ShowRecordState {
    // 기록된 날짜를 선택하는 상태
    SHOW_RECORD_STATE_SELECT_DATE,

    // 선택된 날짜의 운동 기록을 보여주는 상태
    SHOW_RECORD_STATE_SHOW_RECORD
}