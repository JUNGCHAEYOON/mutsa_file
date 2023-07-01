import java.util.Scanner

//중국집
//
//메뉴를 선택해주세요
//1. 짜장면, 2. 짬뽕, 3. 볶음밥, 4. 종료
//
//주문 총 금액 : 30000원
//짜장면 : 0개
//짬뽕 : 0개
//볶음밥 : 0개
//
//음식 : 짜장면
//가격 : 6000원
//곱배기여부 : 곱배기 입니다 or 곱배기 아닙니다
//
//음식 : 짬뽕
//가격 : 8000원
//홍합여부 : 홍합이 있습니다 or 홍합이 없습니다
//
//음식 : 볶음밥
//가격 : 10000원
//국물종류 : 짬뽕국물 or 계란국물
//
//
//짜장면, 짬봉, 볶음밥은 각각 별개의 클래스로 구성해준다.
//각 클래스를 통해 생성된 객체를 담을 ArrayList는 각각 따로 만들어준다.
//
//주문내역 출력시 짜장면들, 짬뽕들, 볶음밥들 순서대로 출력해주세요
//
//프로젝트의 이름은 Kotlin15_InheritEX1 로 해주세요

fun main(){
    val chinessRestaurant = ChinessRestaurant()
    chinessRestaurant.selectMenu()
    chinessRestaurant.printMenu()
    chinessRestaurant.printFoodList()
}

class ChinessRestaurant{
    val scanner = Scanner(System.`in`)
    // 음식들을 담을 리스트
    val jjanJangMyunList = ArrayList<JJaJangMyun>()
    val jjamPPongList = ArrayList<JJamPPong>()
    val bockUmBabList = ArrayList<BockUmBab>()

    // 메뉴를 선택하는 기능
    fun selectMenu(){
        var selectNumber = 0

        while (true){
            println("메뉴를 선택해주세요")
            print("1. 짜장면, 2. 짬뽕, 3. 볶음밥, 4. 종료 : ")
            selectNumber = scanner.nextInt()

            if(selectNumber !in 1..4){
                println("번호를 다시 입력해주세요")
                continue
            }
            if(selectNumber == 4){
                break
            }

            when(selectNumber){
                1 -> {
                    print("곱배기 인가요 ?(1. 곱배기O, 2. 곱배기X) : ")
                    val temp = scanner.nextInt()
                    var temp2 = true
                    if(temp == 2){
                        temp2 = false
                    }
                    val food = JJaJangMyun("짜장면", 6000, temp2)
                    jjanJangMyunList.add(food)
                }
                2 -> {
                    print("홍합이 있나요? (1. 있음, 2. 없음) : ")
                    val temp = scanner.nextInt()
                    var temp2 = true
                    if(temp == 2){
                        temp2 = false
                    }
                    val food = JJamPPong("짬뽕", 8000, temp2)
                    jjamPPongList.add(food)
                }
                3 -> {
                    print("국물 종류가 무엇인가요? (1. 짬뽕국물, 2.계란국물) : ")
                    val temp = scanner.nextInt()
                    var temp2 = "짬뽕국물"
                    if(temp == 2){
                        temp2 = "계란국물"
                    }
                    val food = BockUmBab("볶음밥", 10000, temp2)
                    bockUmBabList.add(food)
                }
            }
        }
    }
    // 주문 내역을 출력하는 기능
    fun printMenu(){
        // 총 주문 금액
        var totalPrice = 0
        // 짜장면 금액 누적
        for(food in jjanJangMyunList){
            totalPrice += food.price
        }
        // 짬뽕 금액 누적
        for(food in jjamPPongList){
            totalPrice += food.price
        }
        // 볶음밥 금액 누적
        for(food in bockUmBabList){
            totalPrice += food.price
        }

        println("주문 총 금액 : ${totalPrice}원")
        println("짜장면 : ${jjanJangMyunList.size}개")
        println("짬뽕 : ${jjamPPongList.size}개")
        println("볶음밥 : ${bockUmBabList.size}개")
        println()
    }
    // 주문한 음식들을 출력하는 기능
    fun printFoodList(){
        // 짜장면
        for(food in jjanJangMyunList){
            food.printFoodInfo()
        }
        // 짬뽕
        for(food in jjamPPongList){
            food.printFoodInfo()
        }
        // 볶음밥
        for(food in bockUmBabList){
            food.printFoodInfo()
        }
    }

}

class JJaJangMyun  (var name:String, var price:Int, var isDouble:Boolean){

    fun printFoodInfo(){
        println("음식 : $name")
        println("가격 : ${price}원")

        if(isDouble == true){
            println("곱배기 여부 : 곱배기 입니다")
        } else {
            println("곱배기 여부 : 곱배기 아닙니다")
        }
    }
}

class JJamPPong (var name:String, var price:Int, var hasMuseel:Boolean){

    fun printFoodInfo(){
        println("음식 : $name")
        println("가격 : ${price}원")

        if(hasMuseel == true){
            println("홍합 여부 : 홍합이 있습니다")
        } else {
            println("홍합 여부 : 홍합이 없습니다")
        }
    }
}

class BockUmBab (var name:String, var price:Int, var soupType:String){

    fun printFoodInfo(){
        println("음식 : $name")
        println("가격 : ${price}원")
        println("국물 종류 : $soupType")
    }
}






