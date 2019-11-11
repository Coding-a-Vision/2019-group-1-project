package com.example.trackmoney.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val incomeExpenseData = MutableLiveData<MutableList<String>>()

    init {
        incomeExpenseData.value = ArrayList()

        addIncomeExpenseData("foo")
        addIncomeExpenseData("bar")
        addIncomeExpenseData("baz")
    }

    // Set public value equal to the list to use in HomeFragment
    val text: LiveData<MutableList<String>> = incomeExpenseData

    // Function to add data to the list
    fun addIncomeExpenseData(income_expense: String) {
        incomeExpenseData.value?.add(income_expense)
    }
}