package com.example.trackmoney.ui.add_money_transaction

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.trackmoney.R
import com.example.trackmoney.util.DatePickerFragment

class AddIncomeExpenseActivity : AppCompatActivity() {

    lateinit var date_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income_expense)

        date_button = findViewById(R.id.income_expense_date)
    }

    // Called when user taps the Add Income/Expense button
    fun turnToMain(view: View) {

        val intent = Intent()
        val amount = findViewById<EditText>(R.id.income_expense_amount).text.toString()

        intent.putExtra("ADD_MONEY_TRANSACTION_RESULT_AMOUNT", amount)

        if (amount.isEmpty()) {
            setResult((Activity.RESULT_CANCELED))
        } else {
            setResult(Activity.RESULT_OK, intent)
        }

        finish()
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun updateDate(updated_date: String) {
        this.date_button.text = updated_date
    }
}