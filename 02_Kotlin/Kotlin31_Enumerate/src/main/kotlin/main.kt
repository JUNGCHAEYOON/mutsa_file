fun main(){
    // 열거형
    // 프로그램 개발 시 특정 그룹안의 구성 요소를 정의하는 값이 필요하고자 할 때 사용한다.
    // 월을 나타내는 단어들, 혈액형을 나타내는 단어들, 방향, 성별 등등
    
    printDirection(Direction.NORTH)
    printDirection(Direction.WEST)

    printDirection2(Direction2.NORTH)
    printDirection2(Direction2.WEST)
    
    printNumber(Number.TWO)
}

// 열거형 정의
enum class Direction{
    NORTH, SOUTH, WEST, EAST
}

// companion
class Direction2{
    companion object{
        val NORTH = 0
        val SOUTH = 1
        val WEST = 2
        val EAST = 3
    }
}

fun printDirection(a1:Direction){
    when(a1){
        Direction.NORTH -> println("북쪽입니다")
        Direction.SOUTH -> println("남쪽입니다")
        Direction.WEST -> println("서쪽입니다")
        Direction.EAST -> println("동쪽입니다")
    }
}

fun printDirection2(a1:Int){
    when(a1){
        Direction2.NORTH -> println("북쪽입니다")
        Direction2.SOUTH -> println("남쪽입니다")
        Direction2.WEST -> println("서쪽입니다")
        Direction2.EAST -> println("동쪽입니다")
    }
}

// 열거형을 정의할 때 값도 설정할 수 있다.
// 주 생성자는 열거형 하나를 만드는 양식을 의미한다.
// 변수의 개수는 무제한이다
enum class Number(val num:Int, val str:String){
    ONE(1, "일"), TWO(2, "이"), THREE(3, "삼")
}

fun printNumber(a1:Number){
    
    when(a1){
        Number.ONE -> println("ONE 입니다")
        Number.TWO -> println("TWO 입니다")
        Number.THREE -> println("THREE 입니다")
    }
    
    when(a1.num){
        1 -> println("1입니다")
        2 -> println("2입니다")
        3 -> println("3입니다")
    }
    
    when(a1.str){
        "일" -> println("일입니다")
        "이" -> println("이입니다")
        "삼" -> println("삼입니다")
    }
    
}










