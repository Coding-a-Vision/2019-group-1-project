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
import com.example.trackmoney.Expense
import java.util.*
import kotlin.collections.ArrayList

class HomeViewModel : ViewModel() {

    private val _incomeExpenseData = MutableLiveData<MutableList<String>>()

//    private val _categoryList1 = MutableLiveData<MutableList<Expense>>()

//    var categoryList1 = mutableListOf(
//        Expense("ciuccia"),
//        Expense("ciuccia2"),
//        Expense("ciuccia3"),
//        Expense("ciuccia4"),
//        Expense("ciuccia5")
//    )

    init {
        _incomeExpenseData.value = ArrayList()

//        _categoryList1.value = ArrayList()

        addIncomeExpenseData("foo")

    }

    // Set public value equal to the list to use in HomeFragment
    val text: LiveData<MutableList<String>> = _incomeExpenseData

//    val categoryList1: LiveData<MutableList<Expense>> = _categoryList1

    // Function to add data to the list
    fun addIncomeExpenseData(income_expense: String) {

        _incomeExpenseData.value?.add(income_expense)
    }
}