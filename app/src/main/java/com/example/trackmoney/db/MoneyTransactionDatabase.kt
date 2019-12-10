package com.example.trackmoney.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import android.icu.lang.UCharacter.GraphemeClusterBreak.V


@Database(entities = arrayOf(MoneyTransaction::class), version = 2, exportSchema = false)
public abstract class MoneyTransactionDatabase : RoomDatabase() {

    abstract fun moneyTransactionDao(): MoneyTransactionDao

    companion object {
        @Volatile
        private var INSTANCE: MoneyTransactionDatabase? = null

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                CREATE TABLE new_money_transaction_table (
                    id TEXT PRIMARY KEY NOT NULL,
                    amount REAL NOT NULL,
                    type TEXT,
                    date TEXT
                )
                """.trimIndent()
                )
                database.execSQL(
                    """
                INSERT INTO new_money_transaction_table (id, amount, type)
                SELECT id, amount, type FROM money_transaction_table
                """.trimIndent()
                )
                database.execSQL("DROP TABLE money_transaction_table")
                database.execSQL("ALTER TABLE new_money_transaction_table RENAME TO money_transaction_table")
            }

        }

        fun getDatabase(context: Context): MoneyTransactionDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoneyTransactionDatabase::class.java,
                    "money_transaction_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}