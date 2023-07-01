import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception
import java.util.*

class InputRecordClass (val scanner : Scanner, val mainClass: MainClass){
    // 1. 오늘의 운동 기록
    fun inputTodayRecord(){
        while(true){
            try {
                println()
                scanner.nextLine()
                print("운동 종류 : ")
                val type = scanner.nextLine()

                print("횟수 : ")
                val temp1 = scanner.next()
                val count = temp1.toInt()

                print("세트 : ")
                val temp2 = scanner.next()
                val set = temp2.toInt()

                //오늘 날짜의 운동 기록을 가져온다.
                //파일 이름을 불러온다.
                val todayRecordFileName = makeTodayFileName()

                // 메인 클래스의 recordList 초기화
                mainClass.recordList.clear()

                val file = File(todayRecordFileName)
                if(file.exists() == true){
                    // 오늘의 운동 기록 데이터를 읽어온다.
                    getRecordData(todayRecordFileName)
                }
                writeRecord(todayRecordFileName)

                break
            }catch(e : Exception){
                println("잘못 입력하였습니다")
            }
        }
    }

    private fun writeRecord(fileName: String) {
        val fos = FileOutputStream(fileName)
        val oos = ObjectOutputStream(fos)

        for(recordClass in mainClass.recordList){
            oos.writeObject(recordClass)
        }

        oos.flush()
        oos.close()
        fos.close()
    }

    private fun getRecordData(fileName : String) {
        // 리스트를 비운다.
        mainClass.recordList.clear()

        // 파일에 기록된 데이터를 모두 불러온다.
        val fis = FileInputStream(fileName)
        val ois = ObjectInputStream(fis)

        try {
            while(true){
                val recordClass = ois.readObject() as MainClass.RecordClass
                mainClass.recordList.add(recordClass)
            }

        }catch (e:Exception){
            ois.close()
            fis.close()
        }
    }

    // 오늘 날짜를 통해 파일 이름을 만들어 준다.
    fun makeTodayFileName(): String {
        // 오늘 날짜를 가져온다.
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH) + 1
        val date = calender.get(Calendar.DAY_OF_MONTH)

        return "%d-%02d-%02d.record".format(year,month,date)
    }
}