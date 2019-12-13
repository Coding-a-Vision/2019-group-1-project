package com.example.trackmoney.ui.dashboard

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.trackmoney.db.MoneyTransaction
import com.example.trackmoney.db.MoneyTransactionDatabase
import com.example.trackmoney.db.MoneyTransactionRepository
import kotlinx.coroutines.launch

sealed class DashboardEvent {
    object Load : DashboardEvent()
    data class DeleteTransaction(val error: Throwable): DashboardEvent()
}

sealed class DashboardState {
    data class Error(val error: Throwable) : DashboardState()
    data class Success(val dashboard: List<MoneyTransaction>) : DashboardState()
}


class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MoneyTransactionRepository
    private val dashboardData = MutableLiveData<List<MoneyTransaction>>()
    var state: MutableLiveData<DashboardState> = MutableLiveData()

    init {
        val dao = MoneyTransactionDatabase.getDatabase(application).moneyTransactionDao()
        repository = MoneyTransactionRepository(dao)
    }

    fun send(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.Load -> Log.d("DashboardViewModel","State Load")
            is DashboardEvent.DeleteTransaction ->  Log.d("DashboardViewModel","Delete Load")
//            is DashboardEvent.Load -> loadContent()

        }
    }

//    private fun updateMoneyTransactions() = viewModelScope.launch {
//        dashboardData.postValue(repository.getTotalAmount())
//        state.value = DashboardState.Success(repository.getTotalAmount())
//    }


    private fun loadContent() {
        // Set state to success
        // TODO: Handle other states
        state.value = DashboardState.Success(dashboardData.value!!.toList())
    }
}