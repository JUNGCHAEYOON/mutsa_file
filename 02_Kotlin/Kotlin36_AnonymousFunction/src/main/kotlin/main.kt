fun main(){
    testFunction1()

    // 코틀린은 함수의 이름을 통해 다른 변수에 함수를 넣는 것은 불가능하다.
    // val testFunction2 = testFunction1

    testFunction2()
}

fun testFunction1(){
    println("testFunction1 입니다")
}

// 익명함수
val testFunction2 = fun(){
    println("익명 함수 입니다")
}

fun test(){


}

val test = fun(){

}