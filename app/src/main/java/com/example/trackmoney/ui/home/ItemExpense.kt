package com.example.trackmoney.ui.home

import com.example.trackmoney.Expense

data class ItemExpense (val text : Expense)

object MyList{

    var categoryList1 = mutableListOf(
        Expense("ciuccia"),
        Expense("ciuccia2"),
        Expense("ciuccia3"),
        Expense("ciuccia4"),
        Expense("ciuccia5")
    )
//    val data : MutableList<ItemExpense> = mutableListOf(
//        ItemExpense(Expense(""))
//    )
}