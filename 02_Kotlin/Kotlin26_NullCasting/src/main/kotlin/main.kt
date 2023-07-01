fun main(){
    testMethod1("안녕하세요")
    testMethod1(null)
}

fun testMethod1(str:String?) {
    // !! 연산자는 null을 허용하지 않는 변수로 변화하는 작업을 수행한다.
    // 따라서 null이 들어 있다면 오류가 발생한다.
    // println(str!!.length)

    // ? 연산자는 참조변수에 null이 들어가 있을 경우 프로퍼티를 사용하면 null이 반환되고
    // 메서드를 호출하면 메서드 호출을 무시한다.
    println(str?.length)

    // 스마트 캐스팅 (null을 허용하지 않는 String 타입으로...)
    // ? 와 동일하게 참조변수에 null이 들어가 있는 경우 프로퍼티를 사용하면 null이 반환되고
    // 메서드를 호출하면 메서드 호출을 무시한다.
    if(str is String){
        println(str.length)
    }

    println("-------------------------------------------")

    // 참조변수에 null이 들어가 있면 if문이 거짓이기 때문에 아무것도 수행하지 않는다.
    // 허나 null이 아닌 객체의 id가 들어가 있다면 null을 허용하지 않는 변수로 스마트
    // 캐스팅이 발생한다.
    if(str != null){
        println(str.length)
    }

    // println(str.length)

}
