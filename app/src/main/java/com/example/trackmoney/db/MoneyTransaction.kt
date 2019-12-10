package com.example.trackmoney.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "money_transaction_table")
class MoneyTransaction(
    @PrimaryKey val id: String,
    val amount: Float,
    val type: String?,
    val date: String?
)