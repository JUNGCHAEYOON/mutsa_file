package com.test.mvvm_practice_sql.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.mvvm_practice_sql.MainActivity
import com.test.mvvm_practice_sql.repository.Test1Repository

class ViewModelTest2 : ViewModel() {
    var dataList = MutableLiveData<MutableList<TestData>>()

    init{
        getAll()
    }

    fun getAll(){
        dataList.value = Test1Repository.getAll(MainActivity.mainActivity)
    }
}

data class TestData(var idx:Int, var data1 : String, var data2 : String)