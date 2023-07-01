fun main(){

    // null을 허용하는 변수 선언
    var a1:String? = null
    // null을 허용하지 않는 변수 선언
    // var a2:String = null
    
    testFun1("안녕하세요")
    // testFun1(null)

    testFun2("안녕하세요")
    testFun2(null)

    testFun3("안녕하세요")
    testFun3(null)

    val t100 = TestClass1(100, "문자열1")
    testFunction1(t100)

    // testFunction1(null)

    testFunction200(t100)

    println("--------------------------------")

    testFunction200(null)

    println("--------------------------------")

    testFuntion300(t100)

    println("--------------------------------")

    testFuntion300(null)
}

fun testFun1(str1:String?){
    // !! 연산자
    // 널을 허용하는 타입의 변수 값을 널을 허용하지 않는 타입으로 변환하여
    // 널을 허용하지 않는 타입의 변수에 담을 수 있도록 한다.
    val value1:String = str1!!
    println("value1 : $value1")
}

fun testFun2(str1:String?){
    // str1 에 null 아닌 객체의 ID가 들어있으면 그 ID를 value1 변수에 담아주고
    // null이 있으면 지정한 값인 "기본문자열" 객체가 value1 변수에 담긴다.
    val value1:String = str1 ?: "기본문자열"
    println("value1 : $value1")
}

fun testFun3(str1:String?){
    // 만약 변수의 값이 null인 경우 코드가 동작하지 않도록 처리해주면
    // null 안정성을 확보할 수 있다.
    // 이때, null을 허용하는 변수를 null을 허용하지 않는 변수처럼 자유롭게
    // 사용할 수 있다.
    if(str1 != null){
        val value1:String = str1
        println("value1 : $value1")
    }
}

class TestClass1(var str1:Int, var str2:String){

    fun testMethod1(){
        System.out.println("TestClass1의 testMethod1")
    }
}

fun testFunction1(t1:TestClass1?){
    // ? 변수를 통해 객체의 멤버에 접근한다.
    // null 안정성을 확보하지 않고 멤버에 접근하겠다면 !!를 붙혀준다.

    // !! 연산자는 null을 허용하는 변수에 담긴 값을 추출하여 null을 허용하지 않는
    // 타입으로 변환하는 연산자이다. 이에 null 값이 들어있으면 오류가 발생한다.
    println("t1.str1 : ${t1!!.str1}")
    println("t1.str2 : ${t1!!.str2}")
    t1!!.testMethod1()
}

fun testFunction200(t1:TestClass1?){
    // ? 연산자
    // 참조변수?.멤버변수 : 참조변수에 null값 들어 있다면 null이 반환된다.
    // 참조변수?.멤버 메서드 : 참조변수에 null값 들어 있다면 메서드를 호출하지 않는다.
    println("t1.str1 : ${t1?.str1}")
    println("t1.str2 : ${t1?.str2}")
    t1?.testMethod1()
}

fun testFuntion300(t1:TestClass1?){
    // null이 저장되어 있는지 여부를 확인한다.
    // if 문 내부에서는 null을 아무런 연산자도 붙히지 않고 멤버 접근이 가능하다.
    if(t1 != null){
        println("t1.str1 : ${t1.str1}")
        println("t1.str2 : ${t1.str2}")
        t1.testMethod1()
    }
}







