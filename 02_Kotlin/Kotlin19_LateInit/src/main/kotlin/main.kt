fun main(){
    val t1 = TestClass1()
    t1.testMethod1()

    println("-----------------------------------------")

    // a4 프로퍼티에 값 저장
    t1.a4 = "문자열입니다"
    t1.testMethod1()
}

class TestClass1{
    // 프로퍼티 정의
    // Kotlin은 프로퍼티를 정의할 때 반드시 값을 넣어줘야 한다.
    var a1:Int = 100
    var a2 = 200
    var a3:Int

    // lateinit
    // var 프로퍼티에만 사용이 가능하며 변수를 정의할 때 값을 저장하지 않아도 된다.
    // lateinit은 기본 자료형(Int, Double 등)에서는 사용할 수 없다.
    lateinit var a4:String

    // lazy
    // val 프로퍼티에서 사용하는 키워드 이다.
    // val 프로퍼티에 저장할 값을 어떠한 처리를 통해서 구해야 한다면 lazy를 사용한다.
    // lazy 코드 블록의 제일 마지막에 작성한 값이나 변수의 값, 수식의 값을 프로퍼티에 저장한다.
    val a5:Int by lazy{
        val temp = 1 + 2 + 3 + 4 + 5
        temp
    }

    // 만약 init 블럭에서 프로퍼티에 값을 저장한다면 프로퍼티를 정의할 때
    // 값을 저장하지 않아도 된다.
    init{
        a3 = 300
    }

    fun testMethod1(){
        println("a1 : $a1")
        println("a2 : $a2")
        println("a3 : $a3")
        // lateinit 프로퍼티는 사용 전에 반드시
        // 값을 저장한 적이 있는지를 확인해야 한다.
        if(::a4.isInitialized) {
            println("a4 : $a4")
        }
        println("a5 : $a5")
    }

}