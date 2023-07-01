fun main(){
    // DataClass
    // 객체의 멤버를 보다 쉽게 관리할 수 있는 기능이 추가된다.
    // abstract, open, sealed, inner 클래스로 정의할 수 없다.

    // 일반 클래스로 객체를 생성한다.
    var obj1 = TestClass1(100, 200)
    var obj2 = TestClass2(100, 200)

    // 멤버 사용
    println("obj1.a1 : ${obj1.a1}")
    println("obj1.a2 : ${obj1.a2}")

    println("obj2.a1 : ${obj2.a1}")
    println("obj2.a2 : ${obj2.a2}")

    obj1.testMethod1()
    obj2.testMethod2()

    println("------------------------------------------")

    // 부생성자를 이용한 객체 생성
    var obj3 = TestClass1(100, 200, 300)
    var obj4 = TestClass2(100, 200, 200)

    println("obj3.a1 : ${obj3.a1}")
    println("obj3.a2 : ${obj3.a2}")
    println("obj3.a3 : ${obj3.a3}")

    println("obj4.a1 : ${obj4.a1}")
    println("obj4.a2 : ${obj4.a2}")
    println("obj4.a3 : ${obj4.a3}")

    println("------------------------------------------")

    var obj5 = TestClass1(100, 200, 300)
    var obj6 = TestClass1(100, 200, 300)

    // 일반 클래스를 통해 만들어진 객체들을 객체의 ID가 같은지를 비교한다.
    if(obj5 == obj6){
        println("obj5와 obj6은 같은 객체 입니다")
    } else {
        println("obj5와 obj6은 다른 객체 입니다")
    }

    var obj7 = TestClass2(100, 200, 300)
    var obj8 = TestClass2(100, 200, 300)

    // data 클래스는 주 생성자를 통해 정의된 멤버 변수의 값이 같은지를 비교한다.
    if(obj7 == obj8){
        println("obj7과 obj8은 같은 객체 입니다")
    } else {
        println("obj7과 obj8은 다른 객체 입니다")
    }

    println("------------------------------------")

    // copy : 객체를 복제하여 새로운 객체를 만든다. (data class에 있는 메서드)
    val obj9 = obj7.copy()
    println("obj7.a1 : ${obj7.a1}")
    println("obj9.a1 : ${obj9.a1}")

    // obj9.a1의 값을 변경한다.
    obj9.a1 = 1000
    println("obj7.a1 : ${obj7.a1}")
    println("obj9.a1 : ${obj9.a1}")

    println("------------------------------------")

    // data class를 통해 만든 객체는 주 생성자에 정의한 멤버 변수를
    // componentN 메서드로 값을 받아올 수 있다.
    val num1 = obj7.component1()
    val num2 = obj7.component2()

    println("num1 : $num1")
    println("num2 : $num2")

    println("-------------------------------------------")

    // 객체 분해 : 주 생성자를 통해 정의된 맴버 변수의 값을 하나씩 추출하여
    // 좌측에 작성한 변수들에 순서대로 담아준다.
    // 이 때, componentN 메서드들을 호출하여 값을 전달 한다.
    val (num10, num11) = obj7
    println("num10 : $num10")
    println("num11 : $num11")
}

// 일반 클래스
class TestClass1(var a1:Int, var a2:Int){
    
    var a3:Int = 0
    
    init{
        println("TestClass1의 init")
    }
    
    constructor(a1:Int, a2:Int, a3:Int) : this(a1, a2){
        this.a3 = a3
    }
    
    fun testMethod1(){
        println("TestClass1의 testMethod1입니다")
    }
}

// DataClass
// 반드시 주 생성자(클래스 이름 옆에 작성하는 생성자)를 작성해줘야 한다.
// 주 생성자 작성을 강제하는 이유는 멤버 변수를 무조건 갖게 하기 위함이다.
data class TestClass2(var a1:Int, var a2:Int){
    var a3:Int = 0
    
    init{
        println("TestClass2의 init")
    }
    
    constructor(a1:Int, a2:Int, a3:Int) : this(a1, a2){
        this.a3 = a3
    }
    
    fun testMethod2(){
        println("TestClass2의 testMethod2 입니다")
    }
}











