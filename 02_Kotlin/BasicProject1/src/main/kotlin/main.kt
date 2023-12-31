import java.util.Scanner

fun main(){
    val gameBoard = GameBoard()
    while(true) {
        // 턴이 바뀔 때의 처리 메서드를 호출한다.
        gameBoard.nextTurn()
        gameBoard.printBoard()
        gameBoard.playerInput()
        gameBoard.changePlayer()
    }
}

class GameBoard{

    val scanner = Scanner(System.`in`)

    // 턴 번호
    var turnNumber = 0

    // 게임판 정보를 담을 배열
    val gameBoardData = arrayOf(
        arrayOf(" ", " ", " "),
        arrayOf(" ", " ", " "),
        arrayOf(" ", " ", " ")
    )

    // 사용자 객체 생성
    val player1 = Player("Player 1", "O")
    val player2 = Player("Player 2", "X")

    // 첫번째 사용자를 설정한다.
    var currentPlayer = player1

    // 턴이 바뀔 때 필요한 처리
    fun nextTurn(){
        turnNumber++
    }

    // 보드를 출력한다.
    fun printBoard(){
//        println()
//        println("${turnNumber}번째 턴")
//        println("  0 1 2")
//        println("0 ${gameBoardData[0][0]}|${gameBoardData[0][1]}|${gameBoardData[0][2]}")
//        println("  -+-+-")
//        println("1 ${gameBoardData[1][0]}|${gameBoardData[1][1]}|${gameBoardData[1][2]}")
//        println("  -+-+-")
//        println("2 ${gameBoardData[2][0]}|${gameBoardData[2][1]}|${gameBoardData[2][2]}")

        println()
        println("${turnNumber}번째 턴")
        println("  0 1 2")
        for(rowIdx in 0 until gameBoardData.size){
            print("$rowIdx ")
            for(colIdx in 0 until gameBoardData[rowIdx].size){
                print("${gameBoardData[rowIdx][colIdx]}")
                if(colIdx < gameBoardData[rowIdx].size - 1){
                    print("|")
                }
            }
            println()
            if(rowIdx < gameBoardData.size - 1){
                println("  -+-+-")
            }
        }
    }

    // 사용자가 보드에 자기 표시를 놓는다
    fun playerInput(){
        println()
        print("${currentPlayer.playName} turn (행,열) : ")
        val newMarkPosition = currentPlayer.setPlayerMark()
        print(newMarkPosition)
    }

    // 사용자 교채
    fun changePlayer(){
        if(currentPlayer == player1){
            currentPlayer = player2
        } else {
            currentPlayer = player1
        }
    }

    // 사용자가 놓은 수가 놓을 수 있는 수인지 검사
    fun isMarkPositionAvailabe(newMarkPostion:String):Boolean{

    }

}

data class Player(var playName:String, var playMark:String){

    val scanner = Scanner(System.`in`)

    // 사용자가 자기 수를 두는 메서드
    fun setPlayerMark() : String{
        try {
            val newMarkPosition = scanner.next()
            val temp = newMarkPosition.split(",")
            val x = temp[0].toInt()
            val y = temp[1].toInt()
            return newMarkPosition
        }catch(e:Exception){
            println("잘못 입력하였습니다")
            return "0"
        }
    }
}





