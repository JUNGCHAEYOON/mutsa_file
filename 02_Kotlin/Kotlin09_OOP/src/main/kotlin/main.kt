fun main(){
    // 클래스를 이용해 객체를 생성한다.
    val obj1:TestClass1 = TestClass1()
    println("obj1 : $obj1")

    // 참조 변수 선언시 클래스 타입을 생략해도 된다.
    val obj2 = TestClass1()
    println("obj2 : $obj2")

    // 참조 변수에는 객체의 아이디 정보가 들어 있으며 다른 변수에
    // 담을 수도 있다.
    val obj3 = obj2
    println("obj3 : $obj3")

    val obj4 = TestClass2()
    println("obj4 : $obj4")

    val obj5 = TestClass3()

    obj5.a1 = 100
    obj5.a2 = 200

    println("obj5.a1 : ${obj5.a1}")
    println("obj5.a2 : ${obj5.a2}")
    obj5.testMethod1()
    obj5.testMethod2()
}

// 클래스를 정의한다.
class TestClass1{

}

// 클래스 내부에 내용이 없다면 { }는 생략해도 된다.
class TestClass2

class TestClass3{
    // 멤버 변수
    var a1 = 0
    var a2 = 0

    // 멤버 메서드
    fun testMethod1(){
        println("testMethod1")
    }

    fun testMethod2(){
        println("testMethod2")
    }
}





