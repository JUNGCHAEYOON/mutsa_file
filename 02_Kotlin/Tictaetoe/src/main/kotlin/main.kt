import java.util.*

fun main() {
    var tictactoe = Tictactoe()
    tictactoe.play()
}

class Tictactoe {
    var scanner = Scanner(System.`in`)

    var board = arrayOf(arrayOf(" "," "," "),
        arrayOf(" "," "," "),
        arrayOf(" "," "," "))

    var turn = 1

    fun play() {
        while (true) {
            printBoard()
            print("플레이어 $turn 차례 입니다 : ")
            var input = scanner.nextLine()

            // 입력받아 자르기,
            var buff = input.split(',')
            var X = buff[0].toInt()
            var Y = buff[1].toInt()

            // 판 바꾸기
            if(board[X][Y] != " "){
                println("다른데두세요")
                continue
            }
            changeBoard(X, Y)

            // 승패검사
            if(checkWinner() == 1){
                printBoard()
                println("플레이어1 승리!")
                break
            }else if(checkWinner() == 2){
                printBoard()
                println("플레이어2 승리!")
                break
            }

            // 턴 바꾸기
            turn = if (turn == 1) 2 else 1
        }
    }

    fun changeBoard(_X: Int, _Y: Int) {
        when(turn){
            1->{
                board[_X][_Y] = "O"
            }
            2->{
                board[_X][_Y] = "X"
            }
        }
    }

    fun printBoard(){
        println("-------")
        for(i in 0 .. 2){
            for(j in 0 .. 2){
                print("|")
                print(board[i][j])
            }
            print("|\n")
            println("-------")
        }
    }

    // 승패 결정 x 이면 0
    // 플레이어 1 승리시 1
    // 플에이어 2 승리시 2
    fun checkWinner() : Int{
        for(i in 0 .. 2){
            //가로
            if (board[i][0] == "O" && board[i][1] == "O" && board[i][2] == "O") return 1
            if (board[i][0] == "X" && board[i][1] == "X" && board[i][2] == "X") return 2

            // 세로
            if (board[0][i] == "O" && board[1][i] == "O" && board[2][i] == "O") return 1
            if (board[0][i] == "X" && board[1][i] == "X" && board[2][i] == "X") return 2
        }

        // 대각
        if (board[0][0] =="O" && board[1][1] == "O" && board[2][2] == "O") return 1
        if (board[0][2] == "O" && board[1][1] == "O" && board[2][0] == "O") return 1
        if (board[0][0] =="X" && board[1][1] == "X" && board[2][2] == "X") return 2
        if (board[0][2] == "X" && board[1][1] == "X" && board[2][0] == "X") return 2


        // 아무도 못이기면
        return 0
    }
}


