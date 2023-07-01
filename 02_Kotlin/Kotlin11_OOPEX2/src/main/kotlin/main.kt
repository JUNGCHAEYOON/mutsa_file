import java.util.*

//자판기를 구현한다.
//
//음료수를 고르세요
//1. 콜라(1000원), 2. 사이다(1000원), 3.커피(1500원), 4.복숭아아이스트(2000원) : 1
//
//만약 그외의 번호를 입력하면.... 다시 입력해주세요 라고 출력하고 위의 메뉴가 다시 나오게 한다.
//
//현재 0원/부족 1000원
//동전을 넣어주세요 : 500
//현재 500원/부족 500원
//동전을 넣어주세요 : 1000
//현재 1500원/부족 0원
//
//콜라가 나왔습니다.
//잔액은 500원 입니다.
//
//콜라는 1000원이고
//양은 300ml 입니다
//회사는 코카콜라 입니다.
//
//사이다는 1000원이고
//양은 300ml 입니다
//회사는 칠성입니다.
//
//커피는 1500원이고
//양은 500ml 입니다
//회사는 별다방입니다.
//
//복숭아아스티는 2000원이고
//양은 700ml 입니다
//회사는 빽다방입니다

fun main(){
    // 자판기 객체 생성
    val vendingMachine = VendingMachine()
    // 번호를 입력받는다.
    val inputNumber = vendingMachine.inputItemNumber()
    // print(inputNumber)
    // 음료수 객체를 생성한다
    val vendingItem = vendingMachine.getItem(inputNumber)
    // 동전을 입력받는다
    val coinCount = vendingMachine.insertCoin(vendingItem)
    // 음료수가 나온다.
    vendingMachine.exitItem(vendingItem, coinCount)
}

// 자판기 클래스
class VendingMachine{
    val scanner = Scanner(System.`in`)
    // 음료수 번호를 입력받는 기능
    fun inputItemNumber() : Int{
        var choiceNumber = 0

        do{
            println("음료수를 고르세요")
            print("1. 콜라(1000원), 2. 사이다(1000원), 3.커피(1500원), 4.복숭아아이스트(2000원) : ")
            choiceNumber = scanner.nextInt()
            // 입력한 번호가 1 ~ 4가 아니라면 ..
            if(choiceNumber !in 1..4){
                println("다시 입력해주세요")
            }
        } while(choiceNumber !in 1..4)

        return choiceNumber
    }
    // 동전 입력 기능
    fun insertCoin(vendingItem: VendingItem) : Int{
        // 입력한 동전
        var coin = 0

        while(coin < vendingItem.price){
            println("현재 : ${coin}원 / 부족 : ${vendingItem.price - coin}원")
            print("동전을 넣어주세요 : ")
            val inputCoin = scanner.nextInt()
            coin += inputCoin
        }
        println("현재 : ${coin}원 / 부족 : 0원")

        return coin
    }
    // 음료수가 나오는 기능
    fun exitItem(vendingItem: VendingItem, coinCount:Int){
        // 입력한 동전에서 상품가격을 빼준다.
        val overCoinCount = coinCount - vendingItem.price

        println("${vendingItem.name}이(가) 나왔습니다")
        println("잔액은 ${overCoinCount}원 입니다")

        vendingItem.printItemInfo()
    }

    // 번호에 따라 음료수 객체를 생성하여 반환하는 함수
    fun getItem(itemNumber:Int) = when(itemNumber){
        1 -> VendingItem("콜라", 1000, 300, "코카콜라")
        2 -> VendingItem("사이다", 1000, 300, "칠성")
        3 -> VendingItem("커피", 1500, 500, "별다방")
        4 -> VendingItem("복숭아아이스티", 2000, 700, "빽다방")
        else -> VendingItem("아무거나", 3000, 100, "아무거나")
    }
}
// 음료수 클래스
class VendingItem (var name:String, var price:Int, var volume:Int, var company:String){

    // 음료수 정보를 출력하는 함수
    fun printItemInfo(){
        println("${name}은(는) ${price}이고")
        println("양은 ${volume}ml 입니다")
        println("회사는 ${company}입니다")
    }
}




