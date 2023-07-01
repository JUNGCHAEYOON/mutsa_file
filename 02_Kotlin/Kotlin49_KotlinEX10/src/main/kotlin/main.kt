fun main(){
//    철수가 저금을 한다.
//
//    첫날 100원을 저금한다.
//    다음날 부터는 전날 까지 통장 잔고액의 3배를 저금한다.
//    1일차 부터 30일차까지의 통장 잔고액을 모두 출력한다.
//
//    1일차 : 100원
//    2일차 : 400원
//    3일차 : 1600원
//    ...
//    30일차 : 000000원
    val bankingClass = BankingClass()
    
    // 1일차는 무조건 100원으로 한다.
    bankingClass.setBankingMoney("100")
    println("%2d일차 : %30s원".format(1, bankingClass.getBankingMoney()))

    // 2일차부터 30일차까지 반복한다.
    for(now in 2..30){
        // 현재의 잔고를 가져온다.
        val nowMoney = bankingClass.getBankingMoney()
        // 잔고액을 3번 저금한다.
        bankingClass.addBankingMoney(nowMoney)
        bankingClass.addBankingMoney(nowMoney)
        bankingClass.addBankingMoney(nowMoney)
        // 출력한다.
        println("%2d일차 : %30s원".format(now, bankingClass.getBankingMoney()))
    }
    
    // 5억 4732만 2347원을 인출하여 잔고를 출력해보세요
}

class BankingClass{

    val bankingList = mutableListOf<Int>()

    fun setBankingMoney(money:String){
        // 3개씩 나눌 때 몇 덩어리인지 구한다.
        var count = money.length / 3
        if(money.length % 3 > 0){
            count++
        }

        // 리스트를 초기화
        bankingList.clear()

        // 제일 처음 덩어리를 추출해 담아준다.
        var temp = money.length % 3
        if(temp == 0){
            temp = 3
        }
        var temp2 = money.substring(0, temp)
        // println(temp2)
        bankingList.add(temp2.toInt())

        for(temp3 in 1..count - 1){
            //println(money.substring(temp, temp + 3))
            temp2 = money.substring(temp, temp + 3)
            bankingList.add(temp2.toInt())
            temp += 3
        }

    }

    fun addBankingMoney(addMoney:String){

        // , 제거
        var addMoney = addMoney.replace(",", "")

        // 저금할 돈을 3자리마 나눠서 담을 리스트
        val addMoneyList = mutableListOf<Int>()

        // 3개씩 나눌 때 몇 덩어리인지 구한다.
        var count = addMoney.length / 3
        if(addMoney.length % 3 > 0){
            count++
        }

        // 제일 처음 덩어리를 추출해 담아준다.
        var temp = addMoney.length % 3
        if(temp == 0){
            temp = 3
        }
        var temp2 = addMoney.substring(0, temp)
        // println(temp2)
        addMoneyList.add(temp2.toInt())

        for(temp3 in 1..count - 1){
            //println(money.substring(temp, temp + 3))
            temp2 = addMoney.substring(temp, temp + 3)
            addMoneyList.add(temp2.toInt())
            temp += 3
        }

        // 통장 잔고 덩어리 개수와 저금할 돈 덩어리 개수를 똑같이 맞춰준다.
        if(bankingList.size > addMoneyList.size){
            val temp = bankingList.size - addMoneyList.size
            val addArray = Array(temp){0}
            addMoneyList.addAll(0,addArray.toList() )
        } else if(bankingList.size < addMoneyList.size){
            val temp = addMoneyList.size - bankingList.size
            val addArray = Array(temp){0}
            bankingList.addAll(0, addArray.toList())
        }

        // 각 덩어리를 더한다.
        for(idx in 0..bankingList.size - 1){
            bankingList[idx] = bankingList[idx] + addMoneyList[idx]
        }

        // 자리 올림 처리를 해준다.
        for(idx in bankingList.size - 1 downTo  1 ){
            if(bankingList[idx] > 999){
                val temp100 = bankingList[idx] / 1000
                bankingList[idx - 1] += temp100
                bankingList[idx] = bankingList[idx] % 1000
            }
        }

        if(bankingList[0] > 999){
            bankingList.add(0, bankingList[0] / 1000)
            bankingList[1] = bankingList[1] % 1000
        }


    }

    fun minusBankingMoney(minusMoney:String){

    }

    fun getBankingMoney() : String {
        // 저장된 정수값이 없으면 0을 반환한다.
        if(bankingList.size == 0){
            return "0"
        } else {
            var bankingMoney = ""

            for (a1 in 0..bankingList.size - 1) {
                if (a1 == 0) {
                    bankingMoney = bankingMoney + bankingList[0]
                } else {
                    bankingMoney = "%s,%03d".format(bankingMoney, bankingList[a1])
                }
            }
            return bankingMoney
        }
    }
}









