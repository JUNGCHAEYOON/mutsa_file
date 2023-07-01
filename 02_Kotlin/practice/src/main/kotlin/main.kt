import java.math.BigInteger

//철수가 저금을 한다.
//
//첫날 100원을 저금한다.
//다음날 부터는 전날 까지 통장 잔고액의 3배를 저금한다.
//1일차 부터 30일차까지의 통장 잔고액을 모두 출력한다.
//
//1일차 : 100원
//2일차 : 400원
//3일차 : 1600원
//...
//30일차 : 000000원
fun main(){
    var total : BigInteger = 0.toBigInteger()
    for(i in 1..30){
        if(total == 0.toBigInteger()){
            total = 100.toBigInteger()
        }else{
            total += (total * 3.toBigInteger())
        }
        println("${i}일차 : ${total}원")
    }
}