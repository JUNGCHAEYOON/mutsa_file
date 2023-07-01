package main

import java.io.Serializable

data class Memo(var title : String, var content : String) : Serializable

data class Category(var categoryName : String) : Serializable{
    var MemoList = ArrayList<Memo>()
}





