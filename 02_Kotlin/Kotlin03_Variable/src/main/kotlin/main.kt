fun main(){
    // 정수 리터럴
    println(100)
    // 4 byte 기억공간에 담을 만큼의 값이면
    // 뒤에 L을 안붙히고 범위를 넘어가면
    // L을 붙혀준다.
    println(10000000000)
    println(938_492_934_823)

    // 실수
    println(11.11)
    println(11.11F)
    
    // 문자리터럴
    println('A')
    println('가')

    // 문자열 리터럴
    println("문자열")

    // 논리 타입
    println(true)
    println(false)

    // RawString
    // 여러 줄의 문자열을 표현할 때 사용한다.
    println("동해물과 백두산이\n마르고 닳도록\n하느님이 보우하사\n우리나라 만세")

    println("""동해물과 백두산이
        |마르고 닳도록
        |하느님이 보우하사
        |우리나라 만세
    """.trimMargin())


    // 변수
    // var/val 변수명:클래스타입
    // 변수를 선언할 때 값을 지정해야 한다.
    var a1:Int = 100
    // println("a1 : ${a1}")
    // ${ } 안에 변수를 작성할 때는 { }를 생략해도 된다.
    println("a1 : $a1")

    // 변수를 선언할 때 저장할 값을 지정해주면 클래스타입을 생략해도 된다.
    // 변수에 저장하는 값을 보고 변수의 타입을 결정한다.
    var a2 = 200
    var a3 = 11.11
    println("a2 : $a2")
    println("a3 : $a3")

    // var : 변수에 값을 계속 저장할 수 있다
    // val : 선언시 변수에 값을 저장하고 그 이후에는 값을 저장할 수 없다.
    var a4 = 100
    val a5 = 100

    println("a4 : $a4")
    println("a5 : $a5")

    a4 = 200
    println("a4 : $a4")

    // val 변수에는 값을 다시 저장할 수 없다.
    // a5 = 200
    // println("a5 : $a5")

    // ? 변수
    // Kotlin의 특징 중에 Null 안정성이라는 부분이 있다.
    // 참조 변수에 객체의 ID가 있다면 그 객체에 접근할 수 있지만
    // null이 있다면 객체의 ID가 없으므로 객체에 접근할 수 없다.
    // null이 있는 상태에서 개발자가 객체에 접근하는 코드를 작성해 실행하면
    // NullPointerException이 발생한다.
    // 이에, 코틀린에서는 변수에 아에 null을 저장하지 못하게 하여 반드시
    // 변수에 객체의 ID를 담도록 강제할 수 있다.

    // 변수 뒤에 ?를 붙히지 않으면 null값을 담을 수 없다.
    // 반드시 객체의 ID를 넣어줘야 한다.
    // var a5:Int = null
    // 변수 뒤에 ?를 붙히면 null 값을 담을 수 있다.
    var a6:Int? = null
    println("a6 : $a6")


    // null을 허용하지 않는 변수에 null을 허용하는 변수의 값을 저장한다.
    // !! : null을 허용하는 값의 타입을 null을 허용하지 않는 타입으로 변환한다.
    var a7:Int = a6!!
    println("a7 : $a7")

    if(a6 != null){
        var a8:Int = a6
        println("a8 : $a8")
    }

}
