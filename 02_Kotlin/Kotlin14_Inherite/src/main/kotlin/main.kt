fun main(){
    val s1 = SubClass1()
    println("s1.subMember1 : ${s1.subMember1}")
    s1.subMethod1()

    println("s1.superMember1 : ${s1.superMember1}")
    s1.superMethod1()
}

// 코틀린에서 class를 정의하면 final 클래스가 된다.
// 클래스를 정의할 때 open 키워드를 붙혀주면 일반 클래스고 정의되고
// 상속이 가능해진다.
open class SuperClass{
    var superMember1 = 100
    
    constructor(){
        println("SuperClass의 기본 생성자")
    }
    
    fun superMethod1(){
        println("SuperClass1의 메서드 입니다")
    }
}

// 자식 클래스
class SubClass1 : SuperClass(){
    val subMember1 = 200
    
    fun subMethod1(){
        println("SubClass1의 메서드 입니다")
    }
}

// Kotlin은 자바 코드로 변경되어 자바 코드가 실행된다.
// 따라서 자바와 동일하게 클래스의 객체를 생성하면 부모 클래스의 기본 생성자(매개변수가 없는)
// 가 자동으로 호출된다.
// 만약 부모 클래스에 기본생성자가 없다면 자식 클래스에서 명시적으로 호출해줘야 한다.

open class SuperClass2(var a1:Int)

class SubClass2 : SuperClass2{
    // 부모의 생성자를 호출한다.
    constructor() : super(100){
        // 필요한 코드를 작성해주세요
    }
}

// 만약 생성자에 작성할 코드가 없다면 ..
class SubClass3 : SuperClass2(100)









