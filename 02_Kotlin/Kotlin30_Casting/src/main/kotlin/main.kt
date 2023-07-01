fun main(){
    // 객체 생성
    val obj1:SubClass1 = SubClass1()
    val obj2:SubClass2 = SubClass2()

    println("obj1 : $obj1")
    println("obj2 : $obj2")

    // 부모 클래스나 구현한 인터페이스 타입 변수에 담아준다
    // 이건 원래 자바에서 되는 것
    val obj3:SuperClass1 = obj1
    val obj4:Inter1 = obj2

    println("obj3 : $obj3")
    println("obj4 : $obj4")

    // SubClass1, SubClass2 를 통해 객체를 생성했지만
    // 객체에 접근 할때 부모클래스 혹은 구현한 인터페이스 타입으로 접근하면
    // 자식 클래스에 있는 메서드 중 overriding한 것이 아닌 것은 호출할 수 없다.
    // obj3.subMethod1()
    // obj4.subMethod2()

    println("-------------------------------------------------")
    // 부모클래스 타입형 변수에 담긴 객체를 자식 클래스 형으로 변환한다.
    // as : 변수의 타입을 다른 클래스 타입으로 변환하는 연산자
    // 상속 관계에 있거나 구현한 인터페이스 관계가 있을 경우에만 성공한다.
    obj3 as SubClass1
    obj3.subMethod1()

    obj4 as SubClass2
    obj4.subMethod2()

    // SubClass1 은 SuperClass1 을 상속 받았기 때문에
    // 아래의 코드는 문법적으로 오류가 없다.
    // 하지만 SuperClass1을 가지고 만든 객체에는 SubClass1 부분이 없기 때문에
    // (SuperClass1은 SubClass1의 부모 클래스이므로...)
    // 실행중에 오류가 발생한다
    // ClassCastingException
    // val temp1:SuperClass1 = SuperClass1()
    // temp1 as SubClass1

    // 상속관계나 인터페이스 구현 관계가 아닌 객체의 형변환
    // 전혀 관계가 없다고 하더라도 문법적으로 오류가 발생하지는 않는다.
    // 실행 중에 오류가 발생한다.
    // ClassCastingException
    // val temp2:SubClass3 = SubClass3()

    // temp2 as SuperClass1
    // temp2 as Inter1

    println("------------------------------------------------")

    val obj5:SubClass1 = SubClass1()

    // 객체를 담을 변수에 타입을 설정하면 형변환이 자동으로 이루어진다.
    val temp3:SuperClass1 = obj5
    // 객체를 담을 변수에 타입을 설정하지 않으면 변수의 타입은 객체의 클래스 타입으로
    // 결정된다. 이럴 때는 as 이용해 형변환해주면 형변환된 클래스타입 변수로 정의된다.
    val temp4 = obj5 as SuperClass1
    println("temp3 : $temp3")
    println("temp4 : $temp4")

    // 참조변수를 통해 접근할 수 있는 객체에 해당 클래스 영역이 있는지 검사
    val chk1 = temp4 is SubClass1
    val chk2 = temp4 is SuperClass1
    val chk3 = temp4 is Inter1

    println("chk1 : $chk1")
    println("chk2 : $chk2")
    println("chk3 : $chk3")

    println("------------------------------------------------------")

    // 스마트 캐스팅
    // 참조변수를 통해 접근할 수 있는 객체에 원하는 클래스의 영역이 있는 경우
    // 자동으로 형변환을 해준다.
    val super3:SuperClass1 = SubClass1()
    if(super3 is SubClass1){
        super3.subMethod1()
    }
    // if 문 밖으로 나가면 형변환 되지 않은 상태가 된다.
    // super3.suMethod1()

    println("----------------------------------")

    val obj10 = SubClass1()
    val obj11 = SubClass2()
    val obj12 = 100
    val obj13 = "문자열"

    anyMethod(obj10)
    anyMethod(obj11)
    anyMethod(obj12)
    anyMethod(obj13)

    println("-------------------------------------------")

    val number1:Int = 100
    // 더 큰 자료형에 값을 저정해본다.
    val number2:Long = number1.toLong()
    println("number2 : $number2")

    val str1:String = "100"
    val number3:Int = str1.toInt()
    println("number3 : $number3")

    // 문자열이 숫자로만 구성된 것이 아니기 때문에
    // 숫자로 변환시 오류가 발생한다.
    val str2:String = "안녕하세요"
    val number4:Int = str2.toInt()
    println("number4 : $number4")
}

open class SuperClass1
interface Inter1

class SubClass1 : SuperClass1(){
    fun subMethod1(){
        println("SubClass1의 subMethod1 입니다")
    }
}
class SubClass2 : Inter1{
    fun subMethod2(){
        println("SubClass2의 subMethod2입니다")
    }
}

class SubClass3

// 스마트 캐스팅과 Any를 이용하면 하나의 메서드로 여러 클래스타입의 객체에 대한
// 작업을 수행할 수 있다.
fun anyMethod(obj:Any){
    if(obj is SubClass1){
        obj.subMethod1()
    }
    if(obj is SubClass2){
        obj.subMethod2()
    }
    if(obj is Int){
        println("정수입니다")
    }
    if(obj is String){
        println("문자열 입니다")
    }
}
