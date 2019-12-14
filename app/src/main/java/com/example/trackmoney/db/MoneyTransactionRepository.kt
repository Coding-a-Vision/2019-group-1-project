package com.example.trackmoney.db

class MoneyTransactionRepository(private val moneyTransactionDao: MoneyTransactionDao) {

    suspend fun insert(moneyTransaction: MoneyTransaction) {
        moneyTransactionDao.insert(moneyTransaction)
    }

    suspend fun getMoneyTransactions() = moneyTransactionDao.getMoneyTransactions()

    suspend fun deleteAll() = moneyTransactionDao.deleteAll()

    suspend fun getExpenses(): Float = moneyTransactionDao.getExpenses()

    suspend fun getIncomes(): Float = moneyTransactionDao.getIncomes()

    suspend fun getTotalAmount(): Float = moneyTransactionDao.getTotalAmount()
}