package com.example.trackmoney

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add onClickListener for visit website button
        visit_website_button.setOnClickListener { visitWebsite() }
    }

    // Called when user taps the Add Income/Expense button
    fun addIncomeExpense(view: View) {
        val intent = Intent(this, AddIncomeExpenseActivity::class.java)
        startActivity(intent)
    }

    // Called when user taps "Visit Website" button
    fun visitWebsite() {
        val webIntent: Intent = Uri.parse("https://www.codingavision.com").let {
                webpage -> Intent(Intent.ACTION_VIEW, webpage)
        }
        startActivity(webIntent)
    }
}
