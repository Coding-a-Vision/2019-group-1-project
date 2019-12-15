package com.example.trackmoney.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

data class Sum(var total:Float)

@Dao
interface MoneyTransactionDao {

    @Query("SELECT * from money_transaction_table ORDER BY date DESC")
    suspend fun getMoneyTransactions(): List<MoneyTransaction>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(moneyTransaction: MoneyTransaction)

    @Query("DELETE FROM money_transaction_table")
    suspend fun deleteAll()

    @Query("SELECT SUM(amount) from money_transaction_table WHERE type='Income'")
    suspend fun getIncomes(): Float

    @Query("SELECT SUM(amount) from money_transaction_table WHERE type='Expense'")
    suspend fun getExpenses(): Float

    @Query("SELECT SUM(amount) from money_transaction_table")
    suspend fun getTotalAmount(): Float

}