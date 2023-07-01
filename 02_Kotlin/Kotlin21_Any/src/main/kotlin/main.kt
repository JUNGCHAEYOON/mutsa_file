fun main(){
    // Any
    // 코틀린에서 모든 클래스가 직접 혹은 간접적으로 상속받는 클래스
    // 클래스를 정의할 때 상속에 대한 코드를 작성하지 않으면
    // Any 클래스를 상속받는다.
    // Any 안에는 클래스가 갖춰여야할 기본적인 기능이 들어 있다.
    // 모든 클래스는 Any를 상속 받은 상태이기 때문에
    // 모든 객체들은 Any타입 변수에 담을 수 있다.

    // Any 타입 변수에 담아준다.
    val a1:Any = TestClass1()
    val a2:Any = TestClass2()

    println(a1)
    println(a2)

    val t3 = TestClass3(100, 200)
    // val str1 = t3.toString()
    // println("str1 : $str1")
    println(t3)

    val t4 = TestClass3(1000, 2000)
    val t5 = TestClass3(100, 200)
    
    if(t3 == t4){
        println("t3과 t4는 같습니다")
    } else {
        println("t3과 t4는 다릅니다")
    }
    
    if(t3 == t5){
        println("t3과 t5는 같습니다")
    } else {
        println("t3과 t5는 다릅니다")
    }
}

class TestClass1
class TestClass2

// Any 클래스가 제공하는 메서드 overriding
class TestClass3 (var a1:Int, var a2:Int){
    // toString
    // 객체의 정보 문자열을 반환하기 위해 만드는 메서드
    // println 과 같은 출력 문으로 출력하면
    // toString 메서드를 호출하고 이 메서가 반환하는 문자열을
    // 이용해 출력하게 된다.
    // override fun toString(): String {
    //    return "TestClass3을 통해 만든 객체"
    // }

    // 제가 사용하는 방식
    // 저는 객체의 변수값이나 필요한 정보를 구해 출력하는 용도로 사용한다.
    override fun toString(): String {
        println("a1 : $a1")
        println("a2 : $a2")

        return super.toString()
    }

    // == 연산자를 사용하면 자동으로 호출되는 메서드
    // == 연산자 기준으로 좌측의 있는 변수를 통해 메서드를 호출하고
    // 우측에 있는 객체가 매개변수로 들어온다.
    // 이 메서드가 반환하는 결과가 == 연산의 결과가 된다.
    override fun equals(other: Any?): Boolean {
        if(other != null){
            // 형변환
            val temp = other as TestClass3

            if(this.a1 == temp.a1 && this.a2 == temp.a2){
                return true
            } else {
                return false
            }
        }
        return false
    }
}





