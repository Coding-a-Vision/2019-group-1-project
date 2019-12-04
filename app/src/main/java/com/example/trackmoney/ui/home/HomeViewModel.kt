package com.example.trackmoney.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
<<<<<<< HEAD
=======
import com.example.trackmoney.model.MoneyTransaction
>>>>>>> 0e41048c5817a15dd0313701e84672fdf1d12f77


<<<<<<< HEAD
    private val _text = MutableLiveData<String>().apply {
        value = ""
=======
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
>>>>>>> 0e41048c5817a15dd0313701e84672fdf1d12f77
    }
    val text: LiveData<String> = _text
}