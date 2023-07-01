fun main(){
    // 객체를 생성하여 자기 타입 변수에 담아준다.
    val obj1 = SubClass1()
    // val obj1:SubClass1 = SubClass1()
    // 자식 클래스 타입이므로 자기 것과 부모 것 모두 사용 가능하다.
    println("obj1.subA1 : ${obj1.subA1}")
    println("obj1.superA1 : ${obj1.superA1}")
    obj1.subMethod1()
    obj1.superMethod1()

    println("---------------------------------------")
    // 객체를 생성하여 부모 클래스 타입에 담아준다.
    var obj2:SuperClass1 = SubClass1()
    // 부모클래스 타입이기 때문에 부모 것만 사용이 가능하다.
    // println("obj2.subA1 : ${obj2.subA1}")
    println("obj2.superA1 : ${obj2.superA1}")
    // obj2.subMethod1()
    obj2.superMethod1()

    println("-----------------------------------------")
    val obj3 = SubClass2()
    obj3.superMethod2()

    // 부모 클래스 타입 변수를 통해 Overriding한 메서드를 호출할 경우
    // 부모의 것이 아닌 자식의 것이 호출된다.
    val obj4:SuperClass2 = SubClass2()
    obj4.superMethod2()
}

open class SuperClass1{
    var superA1 = 100
    
    fun superMethod1(){
        println("SuperClass1의 superMethod1 입니다")
    }
}

class SubClass1 : SuperClass1(){
    var subA1 = 200
    
    fun subMethod1(){
        println("SubClass1의 subMethod1 입니다")
    }
}

open class SuperClass2{
    var superA2 = 300

    // 메서드는 final  메서드로 정의된다.
    // 이에 overriding이 가능하게 하기 위해서 open 키워드를 사용한다.
    // open 키워드를 사용하면 메서드는 final 메서드가 아닌 메서드로 변환된다.
    open fun superMethod2(){
        println("SuperClass2의 superMethod2 입니다")
    }
}

class SubClass2 : SuperClass2(){

    // Overriding하는 메서드는 overrdide라는 키워드를 붙혀주도록 강제하고 있다.
    // 이는, 이 메서드는 Overriding한 메서드라는 것을 명시하기 위함이다.
    override fun superMethod2(){
        // 부모의 메서드 호출
        super.superMethod2()
        println("SubClass2의 superMethod2 입니다")
    }
}







