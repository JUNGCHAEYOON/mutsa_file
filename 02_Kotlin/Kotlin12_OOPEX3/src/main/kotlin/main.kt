import java.util.Scanner

//장난감 공장
//
//생산할 장난감의 종류를 선택해주세요
//1. 로보트 장난감, 2. 레고, 3. BB탄 총, 4. 잠만보인형, 0. 생산끝
//
//잘못된 번호를 입력했을 경우 잘못 입력하였습니다를 출력한다.
//
//모든 입력이 완료되면 다음과 같이 출력한다.
//
//총 : 00개
//로보트 장난감 : 00개
//레고 : 00개
//BB탄 총 : 00개
//잠만보인형 : 00개
//
//로보트 장난감
//가격 : 5000원
//크기 : 로보트만큼 크다
//
//레고
//가격 : 50000원
//크기 : 레고만큼 크다
//
//BB탄 총
//가격 : 10000원
//크기 : BB탄 총 만큼 크다
//
//잠만보인형
//가격 : 20000원
//크기 : 잠만보 만큼 크다
//
//생산된 장난감 총 가격 : 0000000원
//생산된 장난감 평균 가격 : 000000원

fun main(){
    val toyFactory = ToyFactory()
    // 생산할 장난감 번호를 입력 받는다.
    var selectNumber = 0
    // 0 을 입력할 때 까지 반복한다.
    do {
        selectNumber = toyFactory.selectToyType()
        // print(selectNumber)
        // 장난감을 생산한다.
        if(selectNumber != 0){
            val toy = toyFactory.createToy(selectNumber)
            // println(toy.name)
            // 장난감을 저장한다.
            toyFactory.addToy(toy)
        }

    }while (selectNumber != 0)

    // 생산이 완료되면 생산된 장난감 개수를 출력한다.
    toyFactory.printToyCount()

    // 장난감들의 정보를 출력한다.
    toyFactory.printToyInfo()

    // 장난감 가격의 총합과 평균을 출력한다.
    toyFactory.printToyTotalAvgPrice()
}

// 장난감 공장 클래스
class ToyFactory{

    private val scanner = Scanner(System.`in`)
    val toyList = ArrayList<Toy>()

    // 생산할 장난감의 종류를 선택하는 기능
    fun selectToyType() : Int{

        var selectNumber = 0

        do {
            println("생산할 장난감의 종류를 선택해주세요")
            println("1. 로보트 장난감, 2. 레고, 3. BB탄 총, 4. 잠만보인형, 0. 생산끝")
            print("번호 입력 : ")
            selectNumber = scanner.nextInt()

            if(selectNumber !in 0..4){
                println("잘못 입력하였습니다")
            }
        }while(selectNumber !in 0..4)

        return selectNumber
    }
    // 생산한 장난감의 수를 구해 출력하는 기능
    fun printToyCount(){

        var robotCount = 0
        var legoCount = 0
        var bbGunCount = 0
        var jammanboCount = 0

        println("총 : ${toyList.size} 개")

        // 각 장난감의 개수를 계산한다.
        for(tempToy in toyList){
            when(tempToy.name){
                "로보트 장난감" -> robotCount++
                "레고" -> legoCount++
                "BB탄 총" -> bbGunCount++
                "잠만보인형" -> jammanboCount++
            }
        }

//        for(tempToy in toyList){
//            if(tempToy.name == "로보트 장난감"){
//                robotCount++
//            } else if(tempToy.name == "레고"){
//                legoCount++
//            } else if(tempToy.name == "BB탄 총"){
//                bbGunCount++
//            } else if(tempToy.name == "잠만보인형"){
//                jammanboCount++
//            }
//        }

        println("로보트 장난감 : $robotCount 개")
        println("레고 : $legoCount 개")
        println("BB탄 총 : $bbGunCount 개")
        println("잠만보인형 : $jammanboCount 개")
    }
    // 생산한 장난감들의 정보를 출력하는 기능
    fun printToyInfo(){
        // 장난감의 수 만큼 반복한다.
        for(tempToy in toyList){
            tempToy.printToyInfo()
        }
    }
    // 생산한 장난감들의 총 가격과 평균 가격을 구해 출력하는 기능
    fun printToyTotalAvgPrice(){
        // 장난감들의 가격의 총합을 담을 변수
        var priceTotal = 0
        // 장난감의 수 만큼 반복한다.
        for(tempToy in toyList){
            priceTotal += tempToy.price
        }
        // 평균을 구한다.
        val priceAvg = priceTotal / toyList.size

        println("생산된 장난감 총 가격 : ${priceTotal}원")
        println("생산된 장난감 평균 가격 : ${priceAvg}원")

    }
    // 장난감을 생산하여 반환하는 메서드
    fun createToy(toyType:Int) = when(toyType){
        1 -> Toy("로보트 장난감", 5000, "로보트만큼 크다")
        2 -> Toy("레고", 50000, "레고만큼 크다")
        3 -> Toy("BB탄 총", 10000, "BB탄 총 만큼 크다")
        4 -> Toy("잠만보인형", 20000, "잠만보 만큼 크다")
        else -> Toy("아무장난감", 0, "아무장난감")
    }
    // 생산된 장난감을 저장한다.
    fun addToy(toy:Toy){
        toyList.add(toy)
    }

}

// 장난감 클래스
class Toy (var name:String, var price:Int, var size:String){

    fun printToyInfo(){
        println(name)
        println("가격 : $price")
        println("크기 : $size")
    }
}






