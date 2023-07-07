package com.test.android_homework3

data class Category(
    var idx : Int,
    var categoryName: String,
    var memoList : MutableList<Memo>
)

data class Memo(
    var memoTitle : String,
    var memoContent : String
)

