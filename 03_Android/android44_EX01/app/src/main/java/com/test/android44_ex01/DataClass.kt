package com.test.android44_ex01

class DataClass {

    companion object{
        // 과일 정보를 담을 클래스
        val fluitList = mutableListOf<FluitClass>()
    }
}

data class FluitClass(var type:String, var volume:Int, var region:String)