package com.example.trackmoney.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(MoneyTransaction::class), version = 1, exportSchema = false)
public abstract class MoneyTransactionDatabase : RoomDatabase() {

    abstract fun moneyTransactionDao(): MoneyTransactionDao

    companion object {
        @Volatile
        private var INSTANCE: MoneyTransactionDatabase? = null

        fun getDatabase(context: Context): MoneyTransactionDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoneyTransactionDatabase::class.java,
                    "money_transaction_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}