package com.example.trackmoney.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.trackmoney.db.MoneyTransaction
import com.example.trackmoney.db.MoneyTransactionDatabase
import com.example.trackmoney.db.MoneyTransactionRepository
import kotlinx.coroutines.launch


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

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MoneyTransactionRepository
    private val moneyTransactionData = MutableLiveData<List<MoneyTransaction>>()
    var state: MutableLiveData<MoneyTransactionState> = MutableLiveData()

    init {
        val dao = MoneyTransactionDatabase.getDatabase(application).moneyTransactionDao()
        repository = MoneyTransactionRepository(dao)
        updateMoneyTransactions()
    }

    private fun updateMoneyTransactions() = viewModelScope.launch {
        moneyTransactionData.postValue(repository.getMoneyTransactions())
        state.value = MoneyTransactionState.Success(repository.getMoneyTransactions())
    }

    // Function to add moneyTransaction
    private fun insert(moneyTransaction: MoneyTransaction) = viewModelScope.launch {
        repository.insert(moneyTransaction)
        updateMoneyTransactions()
    }

    // Function to send events from HomeFragment
    fun send(event: MoneyTransactionEvent) {
        when (event) {
            is MoneyTransactionEvent.Load -> loadContent()
            is MoneyTransactionEvent.AddMoneyTransaction -> {
                // Add new transaction
                insert(moneyTransaction = event.moneyTransaction)

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