package com.example.trackmoney.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MoneyTransactionDao {

    @Query("SELECT * from money_transaction_table ORDER BY amount ASC")
    suspend fun getMoneyTransactions(): List<MoneyTransaction>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(moneyTransaction: MoneyTransaction)

    @Query("DELETE FROM money_transaction_table")
    suspend fun deleteAll()
}