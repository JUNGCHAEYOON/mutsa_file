import java.util.Scanner
import kotlin.math.max

//자동차공장
//
//생산할 자동차를 선택해주세요
//1. 붕붕, 2. 승용차, 3. 버스, 4. 트럭, 0. 생산종료
//
//입력이 완료되면 다음과 같이 출력한다.
//총 생산 자동차 수 : 000대
//붕붕 : 00대
//승용차 : 00대
//버스 : 00대
//트럭 : 00대
//
//종류 : 붕붕
//최대속도 : 300km/h
//연료 : 꽃향기
//탑승인원수 : 1명
//
//종류 : 승용차
//최대속도 : 200km/h
//연로 : 휘발유
//탑승인원수 : 4명
//
//종류 : 버스
//최대속도 : 150km/h
//연료 : 가스
//탑승인원수 : 50명
//
//종류 : 트럭
//최대속도 : 100km/h
//연료 : 가스
//탑승인원수 : 3명
//
//생산된 자동차들의 평균 속도 : 000km/h
//생산된 자동차들의 총 탑승인원수 : 0000명
//연료가 꽃향기인 자동차의 수 : 00대
//연료가 휘발유인 자동차의 수 : 00대
//연료가 가스인 자동차의 수 : 00대

fun main(){
    val carFactory = CarFactory()
    carFactory.selectCarType()
    carFactory.printCarCount()
    carFactory.printCarInfo()
    carFactory.printData()
}

class CarFactory{

    val scanner = Scanner(System.`in`)
    val carList = ArrayList<Car>()

    // 생산할 자동차 종류를 선택하는 기능
    fun selectCarType(){
        // 선택한 자동차 번호
        var carNumber = 0

        while(true){
            // 자동차 종류 번호를 입력받는다.
            println("생산할 자동차를 선택해주세요")
            print("1. 붕붕, 2. 승용차, 3. 버스, 4. 트럭, 0. 생산종료 : ")
            carNumber = scanner.nextInt()

            if(carNumber !in 0..4){
                println("잘못 입력하였습니다")
                continue
            }
            if(carNumber == 0){
                break
            }

            // 자동차 객체를 생성한다
            val car = createCar(carNumber)
            carList.add(car)

        }
    }
    // 생산한 자동차 수를 출력하는 기능
    fun printCarCount(){
        var boongboongCount = 0
        var basicCarCount = 0
        var busCount = 0
        var truckCount = 0

        println("총 생산 자동차 수 : ${carList.size}")
        
        // 자동차의 수 만큼 반복한다.
        for(car in carList){
            when(car.type){
                "붕붕" -> boongboongCount++
                "승용차" -> basicCarCount++
                "버스" -> busCount++
                "트럭" -> truckCount++
            }
        }
        
        println("붕붕 : ${boongboongCount}대")
        println("승용차 : ${basicCarCount}대")
        println("버스 : ${busCount}대")
        println("트럭 : ${truckCount}대")
        
    }
    // 자동차 종류를 출력하는 기능
    fun printCarInfo(){
        // 자동차의 수 만큼 반복한다.
        for(car in carList){
            car.printCarInfo()
            println()
        }
    }
    // 다양한 값을 구해 출력하는 기능
    fun printData(){
        var speedAvg = 0
        var totalPCount = 0
        var fuelFlowerCount = 0
        var gasolineCount = 0
        var gasCount = 0

        var speedTotal = 0
        // 자동차의 수 만큼 반복한다.
        for(car in carList){
            speedTotal += car.maxSpeed
            totalPCount += car.pCount

            when(car.fuel){
                "꽃향기" -> fuelFlowerCount++
                "휘발유" -> gasolineCount++
                "가스" -> gasCount++
            }
        }
        // 평균 속도를 구한다.
        speedAvg = speedTotal / carList.size

        println("생산된 자동차들의 평균 속도 : ${speedAvg}km/h")
        println("생산된 자동차들의 총 탑승 인원 수 : ${totalPCount}명")
        println("연료가 꽃향기인 자동차의 수 : ${fuelFlowerCount}대")
        println("연료가 휘발유인 자동차의 수 : ${gasolineCount}대")
        println("연료가 가스인 자동차의 수 : ${gasCount}대")
    }
    // 자동차 객체를 생성하는 함수
    fun createCar(type:Int) = when(type){
        1 -> Car("붕붕", 300, "꽃향기", 1)
        2 -> Car("승용차", 200, "휘발유", 4)
        3 -> Car("버스", 150, "가스", 50)
        4 -> Car("트럭", 100, "가스", 3)
        else -> Car("111", 0, "222", 0)
    }
}

class Car (var type:String, var maxSpeed:Int, var fuel:String, var pCount:Int){

    // 자동차 정보 출력
    fun printCarInfo(){
        println("종류 : $type")
        println("최대 속도 : ${maxSpeed}km/h")
        println("연료 : $fuel")
        println("탑승 인원 수 : ${pCount}명")
    }
}







