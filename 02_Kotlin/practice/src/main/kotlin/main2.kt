fun main(){
    val bankingClass = BankingClass()
    println(bankingClass.getBankingMoney())
    bankingClass.setBankingMoney("1500")
}

class BankingClass{

    val bankingList = mutableListOf<Int>()

    fun setBankingMoney(money:String){
// 3개씩 나눌 때 몇 덩어리인지 구한다.
        var count = money.length / 3
        if(count % 3 > 0){
            count++
        }
        println(count)
    }

    fun addBankingMoney(addMoney:String){

    }

    fun minusBankingMoney(minusMoney:String){

    }

    fun getBankingMoney(): String {
// 저장된 정수값이 없으면 0을 반환한다.
        if(bankingList.size == 0){
            return "0"
        } else {
            var bankingMoney = ""

            for (a1 in 0..bankingList.size - 1) {
                if (a1 == 0) {
                    bankingMoney = bankingMoney + bankingList[0]
                } else {
                    bankingMoney = "%s%08d".format(bankingMoney, bankingList[a1])
                }
            }
            return bankingMoney
        }
    }
}