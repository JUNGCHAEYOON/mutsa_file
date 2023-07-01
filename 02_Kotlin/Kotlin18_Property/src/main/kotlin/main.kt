// Property : 문법적으로 변수를 사용하는 것 처럼 작성하지만
// 실제로는 setter/getter 메서드를 사용하는 방식을 의미한다.
fun main(){
    val t1 = TestClass1(100, 200)

    //  a1, a2 변수에 직접 접근하는 것이 아닌
    // getter 메서드를 호출해서 반환 받는 값을 사용한다.
    println("t1.a1 : ${t1.a1}")
    println("t1.a2 : ${t1.a2}")

    // a1 변수에 직접 접근하여 값을 저장하는 것이 아닌
    // setter 메서드를 호출하는 것을 사용한다.
    t1.a1 = 1000
    println("t1.a1 : ${t1.a1}")

    val t2 = TestClass2()
    // setter가 호출된다.
    t2.v1 = 100
    t2.v3 = 5
    // getter가 호출된다.
    println(t2.v1)
    println(t2.v2)
    println(t2.v3)
    println(t2.v4)

    t2.v3 = 100
    println(t2.v3)
}

// 생성자의 매개변수로 선언된 변수들은 private 맴버로
// 정의된다.
// var 의 경우 setter와 getter가 모두 만들어지며
// val 의 경우 final 변수로 정의 되고 getter만 제공된다.
class TestClass1(var a1:Int, val a2:Int)

class TestClass2{
    // 클래스에 정의한 모든 변수는 private로 정의된다.
    // val 변수는 final 변수로 정의된다.
    // 변수만 정의하면 var의 경우 setter/getter가 자동으로 만들어지고
    // val의 경우 getter만 자동으로 만들어준다.
    var v1 = 0
    val v2 = 0
    // 만약 getter나 setter를 원하는데로 만들어지게 하겠다면 아래와 같이
    // getter/setter를 작성해준다. 여기에서 field는 변수를 의미한다.
    var v3 = 0
        get() {
            println("getter 호출")
            return field
        }
        set(value){
            println("setter 호출")
            if(value in 1..10){
                field = value
            }
        }

    val v4 = 0
        get(){
            println("getter 호출 v4")
            return field
        }
}









