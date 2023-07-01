import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

fun main(){
    // Reflection
    // 프로그램 실행 중 객체에 대한 다양한 정보를 파악하기 위한 개념

    // 클래스 타입
    // KClass<클래스타입> : 지정된 클래스의 타입을 파악한다(코틀린 클래스)
    val a1: KClass<String> = String::class
    println("String의 코틀린에서의 타입 : $a1")
    // Class<클래스타입> : 지정된 클래스의 타입을 파악한다(자바 클래스)
    val a2:Class<String> = String::class.java
    println("String의 자바에서의 타입 : $a2")

    // 변수를 통해 접근할 수 있는 객체의 클래스 타입을 파악한다.
    val str1 = "안녕하세요"
    val a3:KClass<out String> = str1::class
    println("str1을 통해 접근할 수 있는 객체의 클래스 타입(코틀린) : $a3")

    val a4:Class<out String> = str1::class.java
    println("str1을 통해 접근할 수 있는 객체의 클래스 타입(자바) : $a4 ")

    val test1 = TestClass(100, 200, 300)

    val a7 = test1::class
    val a8 = test1::class.java
    println("test1의 클래스 타입(코틀린) : $a7")
    println("test1의 클래스 타입(자바) : $a8")

    // 클래스 정보 분석
    println("추상 클래스 인가 : ${test1::class.isAbstract}")
    println("Companion 클래스 인가 : ${test1::class.isCompanion}")
    println("Data 클래스 인가 : ${test1::class.isData}")
    println("final 클래스 인가 : ${test1::class.isFinal}")
    println("open 클래스 인가 : ${test1::class.isOpen}")
    println("중첩 클래스 인가 : ${test1::class.isInner}")
    println("Sealed 클래스 인가 : ${test1::class.isSealed}")

    // 생성자 정보
    val constructors = test1::class.constructors
    // println(constructors)

    for(con in constructors){
        println("constructor : $con")

        // 생성자의 매개 변수들을 가져와 출력해본다
        for(param in con.parameters){
            println("parameter 순서 : ${param.index}")
            println("parameter 타입 : ${param.type}")
            println("parameter 이름 : ${param.name}")
        }
    }

    // 주 생성자
    val primaryCon = test1::class.primaryConstructor
    if(primaryCon != null){
        println(primaryCon)

        for(param in primaryCon.parameters){
            println("parameter index : ${param.index}")
            println("parameter type : ${param.type}")
            println("parameter name : ${param.name}")
        }
    }

    // 프로퍼티
    val properties = test1::class.declaredMemberProperties
    for (prop in properties){
        println(prop.name)
    }

    // 메서드 정보
    val methods = test1::class.declaredMemberFunctions
    for (met in methods){
        println(met.name)
    }

}


class TestClass(var number1:Int, var number2:Int, var number3:Int){

    var number4:Int = 0
    var number5:Int = 0

    constructor(a1:Int) : this(100, 200, 300)

    constructor(a1:Int, a2:Int) : this(100, 200, 300)

    fun testMethod1(){

    }

    fun testMethod2(a1:Int, a2:Int){

    }
}








