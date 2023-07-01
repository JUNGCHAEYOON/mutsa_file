fun main(){
    // enum 은 무언가를 의미하는 값들을 모아서 관리하는 개념이라면
    // sealedClass는 클래스들을 모아 관리하는 개념이다.

    checkNumber1(Number1.TWO)

    println(Number1.TWO)
    println(Number1.TWO.num)
    println(Number1.TWO.str)
    
    // Number1.TWO.num = 100
    // Number1.TWO.str = "안녕하세요"
    // checkNumber1(Number1.TWO)

    // Sealed 클래스 내부에 정의한 클래스의 객체를 생성한다.
    val s1 = Number2.OneClass(100, 200)
    val s2 = Number2.TwoClass(300)
    val s3 = Number2.ThreeClass(100, 11.11)
    val s4 = Number2.TwoClass(400)

    checkNumber2(s1)
    checkNumber2(s2)
    checkNumber2(s3)
    checkNumber2(s4)
}

enum class Number1(var num:Int, var str:String){
    ONE(1, "일"), TWO(2, "이"), THREE(3, "삼")
}

fun checkNumber1(a1:Number1){
    when(a1){
        Number1.ONE -> println("ONE 입니다")
        Number1.TWO -> println("TWO 입니다")
        Number1.THREE -> println("THREE 입니다")
    }
    when(a1.num){
        1 -> println("1 입니다")
        2 -> println("2 입니다")
        3 -> println("3 입니다")
    }
    when(a1.str){
        "일" -> println("일 입니다")
        "이" -> println("이 입니다")
        "삼" -> println("삼 입니다")
    }
}

// Sealed 클래스는 자기를 상속받은 클래스들을 모아 관리하는 개념
sealed class Number2{
    
    open fun number2Method(){
        println("number2 메서드 입니다")
    }
    
    class OneClass(var a1:Int, var a2:Int) : Number2()
    class TwoClass(var a1:Int) : Number2(){
        fun twoMethod(){
            println("TwoClass의 메서드 입니다")
        }

        override fun number2Method(){
            println("overriding된 number2메서드")
        }
    }
    class ThreeClass(var a1:Int, var a2:Double) : Number2()
}

// 매개변수의 타입은 sealed 클래스 타입으로 넣어준다.
fun checkNumber2(obj1:Number2){
    // when으로 분기할 때 is를 통해 어떤 클래스를 통해 만든 객체인지 검사한다.
    when(obj1){
        is Number2.OneClass -> {
            // 스마트 캐스팅도 이루어진다.
            println("OneClass 입니다")
            println(obj1.a1)
            println(obj1.a2)
            obj1.number2Method()
        }
        is Number2.TwoClass -> {
            println("TwoClass 입니다")
            println(obj1.a1)
            obj1.twoMethod()
            obj1.number2Method()
        }
        is Number2.ThreeClass -> {
            println("ThreeClass 입니다")
            println(obj1.a1)
            println(obj1.a2)
            obj1.number2Method()
        }
    }
}





