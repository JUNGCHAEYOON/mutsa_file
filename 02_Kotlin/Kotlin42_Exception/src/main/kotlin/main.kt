fun main() {
    try {
//        val a1 = 10 / 0

//        val str:String? = null
//        println(str!!.length)

        val str2 = "안녕하세요"
        val a2:Int = str2.toInt()
        println("a2 : $a2")
    }catch(e:ArithmeticException){
        println("수학적 오류가 발생했습니다")
    }catch(e:NullPointerException){
        println("Null 오류가 발생했습니다")
    }catch(e:Exception){
        println("그 외의 오류가 발생했습니다")
        e.printStackTrace()
    }
}