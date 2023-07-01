package com.test.android44_ex

data class Category(var categoryName : String, var memoList : ArrayList<Memo>)

data class Memo(var memoTitle : String, var memoContent : String)