package com.example.trackmoney.db

import android.app.Application

class DatabaseManager(application: Application) {

    private val application = application

    private fun getDatabase(): MoneyTransactionDatabase {
        return MoneyTransactionDatabase.getDatabase(application)
    }

    fun getDao(): MoneyTransactionDao {
        return getDatabase().moneyTransactionDao()
    }

    suspend fun getMoneyTransactionList(): List<MoneyTransaction> {
        return getDao().getMoneyTransactions()
    }
}