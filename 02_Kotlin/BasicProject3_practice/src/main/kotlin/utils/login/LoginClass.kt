package utils.login

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

class LoginClass(var scanner : Scanner) {

    // 로그인 한적 있으면 ALREADY, 없으면 FIRST
    var loginState = if(isAlreadyLogined()) LoginState.ALREADY else LoginState.FIRST

    // 루프 실행
    fun login(): Boolean {
        while(true){
            try{
                when(loginState){
                    // 최초 로그인
                    LoginState.FIRST ->{
                        print("설정할 비밀번호를 입력해주세요 : ")
                        val inputTemp1 = scanner.next()
                        val inputNumber = inputTemp1.toInt()

                        print("한번 더 입력해주세요 : ")
                        val inputTemp2 = scanner.next()
                        val inputNumberRe = inputTemp2.toInt()

                        // 두개가 다를 경우
                        if(inputNumber != inputNumberRe){
                            println("다시 입력 해주세요")
                            return FinishedState.NOTFINISHED.bool
                            break
                        }
                        // 두개가 같은 경우 해당 비밀번호를 텍스트로 저장
                        // 이후 이미 비밀번호가 설정된 상태로 변경
                        else{
                            // 비밀번호 text 로 저장하는 코드
                            writePassword(inputNumber)
                            loginState = LoginState.ALREADY
                            return FinishedState.NOTFINISHED.bool
                            break
                        }

                    }
                    // 이미 한번 비밀번호 설정
                    LoginState.ALREADY ->{
                        print("로그인 하시려면 비밀 번호를 입력하세요 : ")
                        val inputTemp3 = scanner.next()
                        val inputNumberLogin = inputTemp3.toInt()
                        
                        // 텍스트 파일로 부터 비밀번호 불러오기
                        // inputNumberLogin과 비교
                        // 다른경우 다시 입력해주세요, break
                        // 같은 경우 mainState 변경
                        if(inputNumberLogin != getPassword().toInt()){
                            println("틀렸습니다 다시 입력 하세요")
                            return FinishedState.NOTFINISHED.bool
                            break
                        }else{
                            return FinishedState.FINISHED.bool
                            break
                        }
                    }
                }
            }catch(e : Exception){
                println("ERR : 잘못 입력 하였습니다")
            }
        }
    }

    // 이전에 로그인 한적 있는지 검사
    private fun isAlreadyLogined(): Boolean {
        val file = File("password/data")
        if(file.exists()){
            return true
        }else{
            return false
        }
    }

    // 비밀번호 불러오기
    private fun getPassword(): String {
        // 불러오기
        val file = File("password/data")
        val reader = FileReader(file)
        val buffer = BufferedReader(reader)
        var temp = ""

        // result 에 저장
        val result = StringBuffer()
        temp = buffer.readLine()
        result.append(temp)

        // 버퍼 닫기
        buffer.close()

        // 리턴
        return result.toString()
        return "1234"
    }

    // 텍스트 파일로 비밀번호 저장
    private fun writePassword(inputNumber: Int) {
        // 디렉토리명, 파일명, 파일내용
        var directory = "password"
        var filename = "data"
        var content = inputNumber.toString()

        //
        var dir = File(directory)
        if(!dir.exists()){
            dir.mkdirs()
        }

        val writer = FileWriter(directory + "/" + filename)
        val buffer = BufferedWriter(writer)

        buffer.write(content)
        buffer.close()
    }
}

// 로그인이 완료되었는지 여부
enum class FinishedState(val bool : Boolean){
    FINISHED(true),
    NOTFINISHED(false)
}

enum class LoginState{
    // 최초 로그인
    FIRST,
    // 이미 한번 비밀번호 설정
    ALREADY
}