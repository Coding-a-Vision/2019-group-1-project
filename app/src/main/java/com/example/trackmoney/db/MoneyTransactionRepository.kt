package com.example.trackmoney.db

class MoneyTransactionRepository(private val moneyTransactionDao: MoneyTransactionDao) {

    suspend fun insert(moneyTransaction: MoneyTransaction) {
        moneyTransactionDao.insert(moneyTransaction)
    }

    suspend fun getMoneyTransactions() = moneyTransactionDao.getMoneyTransactions()
}