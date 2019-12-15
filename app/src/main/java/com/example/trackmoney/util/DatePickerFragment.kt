package com.example.trackmoney.util

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.trackmoney.ui.add_money_transaction.AddIncomeExpenseActivity
import java.util.*

class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var today: String = "Pick Date"

    init {
        setToday(Calendar.getInstance())
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(activity!!, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Do something with the date chosen by the user
        val date_picked = "".plus(day).plus("/").plus(month).plus("/").plus(year)
        Log.i("DATE PICKED", date_picked)

        // Pass the picked date using activity method
        (activity as AddIncomeExpenseActivity).updateDate(date_picked)
    }

    private fun setToday(calendar: Calendar) {
        today = calendar.get(Calendar.DATE).toString().plus("/")
            .plus(calendar.get(Calendar.MONTH).toString()).plus("/")
            .plus(calendar.get(Calendar.YEAR).toString())

        Log.i("TODAY ", today)
    }

    fun getToday(): String {
        return today
    }
}
