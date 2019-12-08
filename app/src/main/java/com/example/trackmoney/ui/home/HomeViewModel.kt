package com.example.trackmoney.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.trackmoney.db.MoneyTransaction


// Events that HomeFragment can send
sealed class MoneyTransactionEvent {
    object Load : MoneyTransactionEvent()
    data class AddMoneyTransaction(val moneyTransaction: MoneyTransaction) : MoneyTransactionEvent()
}

// States that HomeViewModel can have
sealed class MoneyTransactionState {
    //TODO: object Inprogress : MoneyTransactionState()
    data class Error(val error: Throwable) : MoneyTransactionState()

    data class Success(val moneyTransactions: List<MoneyTransaction>) : MoneyTransactionState()
}

class HomeViewModel : ViewModel() {

    private val moneyTransactionData = MutableLiveData<MutableList<MoneyTransaction>>()
    var state: MutableLiveData<MoneyTransactionState> = MutableLiveData()

    init {
        moneyTransactionData.value = ArrayList()
    }

    // Function to add moneyTransaction
    fun addIncomeExpenseData(moneyTransaction: MoneyTransaction) {
        val newList = moneyTransactionData
        newList.value?.add(moneyTransaction)
        moneyTransactionData.value = newList.value
    }

    // Function to send events from HomeFragment
    fun send(event: MoneyTransactionEvent) {
        when (event) {
            is MoneyTransactionEvent.Load -> loadContent()
            is MoneyTransactionEvent.AddMoneyTransaction -> {
                // Add new transaction
                addIncomeExpenseData(moneyTransaction = event.moneyTransaction)

                // Set state to success
                // TODO: Handle other states
                state.value = MoneyTransactionState.Success(moneyTransactionData.value!!.toList())
            }
        }
    }

    private fun loadContent() {
        // Set state to success
        // TODO: Handle other states
        state.value = MoneyTransactionState.Success(moneyTransactionData.value!!.toList())
    }
}