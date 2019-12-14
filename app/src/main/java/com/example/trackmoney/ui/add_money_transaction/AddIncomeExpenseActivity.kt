package com.example.trackmoney.ui.add_money_transaction

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.trackmoney.R
import com.example.trackmoney.util.DatePickerFragment

class AddIncomeExpenseActivity : AppCompatActivity() {

    lateinit var date_button: Button

    lateinit var save_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_income_expense)
        date_button = findViewById(R.id.income_expense_date)
        save_button = findViewById(R.id.save_income_expense_button)

        date_button.text = DatePickerFragment().getToday()

        save_button.setOnClickListener {

            val intent = Intent()

            // Check if data exists
            try {
                val amount =
                    findViewById<EditText>(R.id.income_expense_amount).text.toString().toFloat()
                val date = findViewById<Button>(R.id.income_expense_date).text.toString()
                val category =
                    findViewById<Spinner>(R.id.income_expense_category).selectedItem.toString()
                val checkedRadioButton =
                    findViewById<RadioGroup>(R.id.radio_group_income_expense).checkedRadioButtonId
                val checked = findViewById<RadioButton>(checkedRadioButton).text.toString()

                intent.putExtra("ADD_MONEY_TRANSACTION_RESULT_AMOUNT", amount)
                intent.putExtra("ADD_MONEY_TRANSACTION_RESULT_DATE", date)
                intent.putExtra("ADD_MONEY_TRANSACTION_RESULT_CATEGORY", category)
                intent.putExtra("ADD_MONEY_TRANSACTION_RESULT_TYPE", checked)
            } catch (error: Throwable) {
                setResult(Activity.RESULT_CANCELED)
            }

            setResult(Activity.RESULT_OK, intent)

            finish()
        }
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun updateDate(updated_date: String) {
        this.date_button.text = updated_date
    }
}
