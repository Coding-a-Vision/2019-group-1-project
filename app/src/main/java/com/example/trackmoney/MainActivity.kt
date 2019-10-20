package com.example.trackmoney

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // Called when user taps the Add Income/Expense button
    fun addIncomeExpense(view: View) {
        val intent = Intent(this, AddIncomeExpenseActivity::class.java)
        startActivity(intent)
    }
}
