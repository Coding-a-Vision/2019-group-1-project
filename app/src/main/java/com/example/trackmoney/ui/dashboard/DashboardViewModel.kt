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
    object DeleteTransactions: DashboardEvent()
}

sealed class DashboardState {
    data class Error(val error: Throwable) : DashboardState()
    data class Success(val amounts: List<Float>) : DashboardState()
}

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MoneyTransactionRepository
    private val dashboardData = MutableLiveData<List<Float>>()
    var state: MutableLiveData<DashboardState> = MutableLiveData()

    init {
        val dao = MoneyTransactionDatabase.getDatabase(application).moneyTransactionDao()
        repository = MoneyTransactionRepository(dao)
        updateDashboardData()
    }

    fun send(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.Load -> loadContent()
            is DashboardEvent.DeleteTransactions -> deleteAll()
        }
    }

    private fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    private fun updateDashboardData() = viewModelScope.launch {
        val tempList = ArrayList<Float>()

        try {
            tempList.add(0, repository.getExpenses())
            tempList.add(1, repository.getIncomes())
            tempList.add(2, repository.getTotalAmount())
        } catch (exception: Throwable) {
            tempList.add(0, 0.00f)
            tempList.add(1, 0.00f)
            tempList.add(2, 0.00f)
        }


        dashboardData.postValue(tempList)
        state.value = DashboardState.Success(tempList)
    }

    private fun loadContent() {
        // Set state to success
        // TODO: Handle other states
        state.value = DashboardState.Success(dashboardData.value!!)
    }
}