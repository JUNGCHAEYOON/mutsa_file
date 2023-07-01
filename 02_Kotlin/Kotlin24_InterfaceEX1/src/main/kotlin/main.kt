//동물원
//
//동물사육장, 해양생물놀이터, 방목장, 밀림
//
//동물 사육장은 모든 동물들이 있는 곳
//해양생물놀이터는 돌고래와 상어가 있는 곳
//방목장은 말과 기린이 있는 곳
//밀림은 호랑이와 사자가 있는 곳
//
//동물원에 있는 모든 동물들은 동물 사육장에 넣어주면 모든 동물들이 밥을 먹는다.
//동물원에 있는 모든 동물들을 해양생물 놀이터에 넣어주면 돌고래와 상어가 헤엄을 친다.
//동물원에 있는 모든 동물들을 방목장에 넣어주면 말과 기린과 호랑이만 뛰어 다닌다.
//동물원에 있는 모든 몽물들을 밀림에 넣어주면 상어와 기린과 사자가 사냥을 한다.
//
//돌고래는 밥을 먹을 때 "냠냠냠"하고 먹는다.
//상어는 밥을 먹을 때 "얌얌얌"하고 먹는다.
//말은 밥을 먹을 때 "당근 당근"하고 먹는다.
//기린은 밥을 먹을 때 "풀풀풀" 하고 먹는다.
//호랑이가 밥을 먹을 때 "아작 아작"하고 먹는다.
//사자가 법을 먹을 때 "꿀꺽 꿀꺽" 하고 는다.
//
//돌고래는 헤엄을 칠 때 "돌~돌~"하면서 헤엄을 친다.
//상어가 헤엄을 칠 때 "슈웅 슈융" 하면서 헤엄을 친다.
//말이 뛰어 다닐 때닌 "이히히히힝~" 하면서 뛰어 다니고
//기린이 뛰어 다닐 때는 "영차~ 영차~" 하면서 뛰어 다닌다.
//호랑이가 뛰어 다닐 때는 "헥~ 헥~" 하면서 뛰어 다닌다.
//상어가 사냥을 하면 "으아아아아아!!!" 하면서 사냥을 한다.
//기린이 사냥을 하면 "가즈아~" 하고 사냥을 한다.
//사자가 사냥을 하면 "암컷아 사냥해와라~"하고 사냥을 한다.
//
//프로그램이 시작되면 다음과 같이 입력을 받는다.
//어떤 동물을 동물원에 넣어줄까요?
//1. 돌고래, 2. 상어, 3. 말, 4. 기린, 5. 호랑이, 6. 사자, 0. 그만넣어 :
//
//번호를 잘못 입력하면 잘못 입력하였습니다를 보여준다.
//0을 입력하면 입력을 중단한다.
//
//모든 동물들을 사육장에 넣어 결과를 출력한다.
//모든 동물들을 해양생물 놀이터에 넣어 결과를 출력한다.
//모든 동물들을 방목장에 넣어 결과를 출력한다.
//모든 동물들을 밀림에 넣어 결과를 출력한다.
//
//밥먹는 것, 헤엄치는 것, 뛰어 다는 것, 사냥하는 것은 모두 메서드로 만들어준다.
//
//--- 출력 ---
//
//타입 : 돌고래
//크기 : 돌고래 만큼 크다
//헤엄 속도 : 300노트
//
//타입 : 상어
//크기 : 상어 만큼 크다
//헤엄 속도 : 500노트
//
//타입 : 말
//크기 : 말 만큼 크다
//달리기 속도 : 300km/h
//
//타입 : 기린
//크기 : 기린 만큼 크다
//달리기 속도 : 500km/h
//
//타입 : 호랑이
//크기 : 호랑이 만큼 크다
//먹이량 : 500마리
//
//타입 : 사자
//크기 : 사자만큼 크다
//먹이량 : 600마리
//
//돌고래는 밥을 먹을 때 "냠냠냠"하고 먹는다.
//상어는 밥을 먹을 때 "얌얌얌"하고 먹는다.
//말은 밥을 먹을 때 "당근 당근"하고 먹는다.
//기린은 밥을 먹을 때 "풀풀풀" 하고 먹는다.
//호랑이가 밥을 먹을 때 "아작 아작"하고 먹는다.
//사자가 법을 먹을 때 "꿀꺽 꿀꺽" 하고 는다.
//
//돌고래는 헤엄을 칠 때 "돌~돌~"하면서 헤엄을 친다.
//상어가 헤엄을 칠 때 "슈웅 슈융" 하면서 헤엄을 친다.
//말이 뛰어 다닐 때닌 "이히히히힝~" 하면서 뛰어 다니고
//기린이 뛰어 다닐 때는 "영차~ 영차~" 하면서 뛰어 다닌다.
//호랑이가 뛰어 다닐 때는 "헥~ 헥~" 하면서 뛰어 다닌다.
//상어가 사냥을 하면 "으아아아아아!!!" 하면서 사냥을 한다.
//기린이 사냥을 하면 "가즈아~" 하고 사냥을 한다.
//사자가 사냥을 하면 "암컷아 사냥해와라~"하고 사냥을 한다.
//
//모든 출력은 항상 입력한 순서대로 출력한다.

import java.util.Scanner

fun main(){
    // 1. 어떠한 클래스들이 필요한지를 정리한다(한국어 주석)
    // 2. 각 클래스들의 이름을 정의한다(class 클래스명)
    // 3. 각 클래스가 가져야할 모든 변수와 모든 메서드들을 클래스 내부에 정의한다(한국어 주석)
    // 4. 한국어 주석으로 작성한 모든 변수와 모든 메서드들을 작성한다(멤버변수, 메서드는 { } 까지만)
    // 5. 각 클래스 별로 중복되는 요소들을 정리한다.
    // 6. 중복되는 요소들을 가지는 부모 클래스들을 정의한다.
    // 7. 정의된 부모 클래스들 중에 메서드로만 구성되는 것은 인터페이스로 변경한다.
    // 8. 부모 클래스들이 상속 받거나 구현해야 하는 인터페이스들을 설정해준다.
    // 8-1. 만약 자식 클래스가 구현해야 하는 메서드가 있다면 추상 메서드로 정의하고 클래스는 추상 클래스로 정의한다.
    // 9. 최종 자식 클래스에 상속과 인터페이스를 설정해준다.

    val zoo = Zoo()
    zoo.inputAnimalNumber()
    zoo.animalAction()
}

// 동물원
class Zoo{

    val scanner = Scanner(System.`in`)
    val animalList = ArrayList<Animal>()

    // 동물의 타입을 입력받는 메서드
    fun inputAnimalNumber(){
        var inputNumber = 0

        while(true){
            println("어떤 동물을 동물원에 넣어줄까요?")
            print("1. 돌고래, 2. 상어, 3. 말, 4. 기린, 5. 호랑이, 6. 사자, 0. 그만넣어 : ")
            inputNumber = scanner.nextInt()

            if(inputNumber !in 0..6){
                println("잘못 입력하였습니다")
                continue
            }

            if(inputNumber == 0){
                break
            }

            // 동물 객체를 생성한다.
            val animal = createAnimal(inputNumber)
            animalList.add(animal)
        }
    }

    // 동물 객체를 만드는 메서드
    fun createAnimal(type:Int) = when(type){
        1 -> Dolphin("돌고래", "돌고래 만큼 크다", 300)
        2 -> Shark("상어", "상어 만큼 크다", 500)
        3 -> Horse("말", "말 만큼 크다", 300)
        4 -> Giraffe("기린", "기린 만큼 크다", 500)
        5 -> Tiger("호랑이", "호랑이 만큼 크다", 500)
        6 -> Lion("사자", "사자만큼 크다", 600)
        else -> Lion("하하하", "호호호", 0)
    }

    // 각 동물들의 기능을 동작시키는 메서드
    fun animalAction(){
        val vivarium = Vivarium()
        val marineLifePlayGround = MarineLifePlayGround()
        val pasture = Pasture();
        val jungle = Jungle();

        for(animal in animalList){
            animal.printInfo()
        }

        vivarium.fee(animalList)
        marineLifePlayGround.swimming(animalList)
        pasture.run(animalList)
        jungle.hunt(animalList)
    }
}

// 동물 사육장
class Vivarium {
    // - 동물들이 밥을 먹는 기능
    fun fee(animalList:ArrayList<Animal>){
        for(animal in animalList){
            animal.eat()
        }
    }
}

// 해양 생물 놀이터
class MarineLifePlayGround{
    // - 동물들이 헤엄을 치는 기능
    fun swimming(animalList: ArrayList<Animal>){
        for(animal in animalList){
//            if(animal.type == "돌고래" || animal.type == "상어"){
//                val temp = animal as MarinAnimal
//                temp.swimming()
//            }
            // 객체에 MarinAnimal 부분이 있다면 (상속을 받았거나 인터페이스를 구현했다면...
            // true가 반환되고 객체를 MarinAnimal 타입으로 형변환까지 해준다.
            if(animal is MarinAnimal){
                animal.swimming()
            }
        }
    }
}

// 방목장
class Pasture {
    // - 동물들이 뛰어다니는 기능
    fun run(animalList: ArrayList<Animal>){
        for(animal in animalList){
            if(animal is RunAnimal2){
                animal.run()
            }
        }
    }
}

// 밀림
class Jungle {
    // - 동물들이 사냥하는 기능
    fun hunt(animalList: ArrayList<Animal>){
        for(animal in animalList){
            if(animal is HuntAnimal){
                animal.hunt()
            }
        }
    }
}

// 돌고래
// - 타입, 크기, 헤엄속도
//class  Dolphin(var type:String, var size:String, var swimSpeed:Int) {
//
//    // - 밥을 먹는 기능
//    fun eat(){
//
//    }
//    // - 헤엄치는 기능
//    fun swimming(){
//
//    }
//    // - 정보를 출력하는 기능
//    fun printInfo(){
//
//    }
//}

class Dolphin(type: String, size: String, swimSpeed: Int) : MarinAnimal(type, size, swimSpeed) {
    override fun swimming() {
        println("${type}은 돌~돌~ 하면서 헤엄칩니다")
    }

    override fun eat() {
        println("${type}은 냠냠냠 하고 먹습니다")
    }

    override fun printInfo() {
        println("타입 : ${type}")
        println("크기 : ${size}")
        println("헤엄속도 : ${swimSpeed}노트")
    }

}

// 상어
// - 타입, 크기, 헤엄속도
//class Shark (var type:String, var size:String, var swimSpeed:Int) {
//    // - 밥을 먹는 기능
//    fun eat(){
//    }
//    // - 헤엄치는 기능
//    fun swimming(){
//    }
//    // - 사냥하는 기능
//    fun hunt(){
//    }
//    // - 정보를 출력하는 기능
//    fun printInfo(){
//    }
//}

class Shark(type: String, size: String, swimSpeed: Int) : MarinAnimal(type, size, swimSpeed), HuntAnimal{
    override fun swimming() {
        println("${type}은 슈웅 슈융하며 헤엄 칩니다.")
    }

    override fun eat() {
        println("${type}은 얌얌얌하며 먹습니다")
    }

    override fun printInfo() {
        println("타입 : $type")
        println("크기 : $size")
        println("헤엄 속도 : ${swimSpeed}노트")
    }

    override fun hunt() {
        println("${type}은 으아아아아아!!! 하면서 사냥합니다")
    }

}

// 말
// - 타입, 크기, 달리기 속도
//class Horse (var type:String, var size:String, var runSpeed:Int) {
//    // - 밥을 먹는 기능
//    fun eat(){
//    }
//    // - 뛰어 다니는 기능
//    fun run(){
//    }
//    // - 정보를 출력하는 기능
//    fun printInfo(){
//    }
//}

class Horse(type: String, size: String, runSpeed: Int) : RunAnimal1(type, size, runSpeed), RunAnimal2{
    override fun eat() {
        println("${type}은 당근 당근하며 먹습니다")
    }

    override fun printInfo() {
        println("타입 : $type")
        println("크기 : $size")
        println("달리기 속도 : ${runSpeed}km/h")
    }

    override fun run() {
        println("${type}은 이히히히힝~하며 달립니다.")
    }

}


// 기린
// - 타입, 크기, 달리기 속도
//class Giraffe (var type:String, var size:String, var runSpeed:Int) {
//    // - 밥을 먹는 기능
//    fun eat(){
//    }
//    // - 뛰어 다니는 기능
//    fun run(){
//    }
//    // - 사냥하는 기능
//    fun hunt(){
//    }
//    // - 정보를 출력하는 기능
//    fun printInfo(){
//    }
//}
class Giraffe(type: String, size: String, runSpeed: Int) : RunAnimal1(type, size, runSpeed), RunAnimal2, HuntAnimal{
    override fun eat() {
        println("${type}은 풀풀풀하며 먹습니다")
    }

    override fun printInfo() {
        println("타입 : $type")
        println("크기 : $size")
        println("달리기 속도 : ${runSpeed}km/h")
    }

    override fun run() {
        println("${type}은 영차~ 영차~하며 뜁니다")
    }

    override fun hunt() {
        println("${type}은 가즈아~하며 사냥합니다")
    }

}

// 호랑이
// - 타입, 크기, 먹이량
//class Tiger (var type:String, var size:String, var feeVolume:Int){
//    // - 밥을 먹는 기능
//    fun eat(){
//    }
//    // - 뛰어 다니는 기능
//    fun run(){
//    }
//    // - 정보를 출력하는 기능
//    fun printInfo(){
//    }
//}
class Tiger(type: String, size: String, feeVolume: Int) : EatAnimal(type, size, feeVolume), RunAnimal2{
    override fun eat() {
        println("${type}은 아작 아작하며 먹습니다")
    }

    override fun printInfo() {
        println("타입 : $type")
        println("크기 : $size")
        println("먹이량 : ${feeVolume}km/h")
    }

    override fun run() {
        println("${type}은 헥~ 헥~하며 뜁니다")
    }

}


// 사자
// - 타입, 크기, 먹이량
//class Lion  (var type:String, var size:String, var feeVolume:Int){
//    // - 밥을 먹는 기능
//    fun eat(){
//    }
//    // - 사냥하는 기능
//    fun hunt(){
//    }
//    // - 정보를 출력하는 기능
//    fun printInfo(){
//    }
//}
class Lion(type: String, size: String, feeVolume: Int) : EatAnimal(type, size, feeVolume), HuntAnimal{
    override fun eat() {
        println("${type}은 꿀꺽 꿀꺽하며 먹습니다")
    }

    override fun printInfo() {
        println("타입 : $type")
        println("크기 : $size")
        println("먹이량 : ${feeVolume}km/h")
    }

    override fun hunt() {
        println("${type}은 암컷아 사냥해와라~하며 사냥합니다")
    }

}

// 모든 동물들
//open class Animal(var type:String, var size:String){
//    open fun eat(){
//
//    }
//    open fun printInfo(){
//
//    }
//}

open abstract class Animal(var type:String, var size:String){
    open abstract fun eat()

    open abstract fun printInfo()
}

// 해양 생물
//open class MarinAnimal(var swimSpeed: Int){
//    open fun swimming(){
//
//    }
//}

// 해양 생물
open abstract class MarinAnimal : Animal{

    var swimSpeed = 0
    // 매개 변수로 받은 값들 중 type과 size는 부모클래스의 생성자를 호출 할 때 전달 해준다.
    constructor(type:String, size:String, swimSpeed: Int) : super(type, size){
        this.swimSpeed = swimSpeed
    }

    open abstract fun swimming()
}

// 달리는 동물2
//open class RunAnimal2(){
//    open fun run(){
//
//    }
//}

interface RunAnimal2{
    fun run()
}

// 달리는 동물1
// open class RunAnimal1 (var runSpeed: Int)
open abstract class RunAnimal1 : Animal{

    var runSpeed = 0

    constructor(type:String, size:String, runSpeed: Int) : super(type, size){
        this.runSpeed = runSpeed
    }
}

// 먹는 동물
// open class EatAnimal(var feeVolume: Int)
open abstract class EatAnimal : Animal{

    var feeVolume = 0

    constructor(type:String, size:String, feeVolume: Int) : super(type, size){
        this.feeVolume = feeVolume
    }
}



// 사냥하는 동물
//open class HuntAnimal(){
//    open fun hunt(){
//
//    }
//}

interface HuntAnimal{
    fun hunt()
}

