//package com.example.trackmoney.ui.home
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//
//class HomeViewModel : ViewModel() {
//
//    private val _text = MutableLiveData<String>().apply {
//        value = ""
//    }
//    val text: LiveData<String> = _text
//}

package com.example.trackmoney.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _incomeExpenseData = MutableLiveData<MutableList<String>>()

    init {
        _incomeExpenseData.value = ArrayList()

        addIncomeExpenseData("foo")

    }

    // Set public value equal to the list to use in HomeFragment
    val text: LiveData<MutableList<String>> = _incomeExpenseData

    // Function to add data to the list
    fun addIncomeExpenseData(income_expense: String) {

        _incomeExpenseData.value?.add(income_expense)
    }
}