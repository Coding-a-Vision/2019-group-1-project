package com.example.trackmoney.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.trackmoney.R

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private lateinit var totalIncome: TextView
    private lateinit var totalExpense: TextView
    private lateinit var totalAmount: TextView

    private lateinit var deleteButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        observeViewModel()
        setupViews()
    }


    private fun setupViews() {
        totalIncome = requireActivity().findViewById(R.id.totalIncome)
        totalExpense = requireActivity().findViewById(R.id.totalExpense)
        totalAmount = requireActivity().findViewById(R.id.totalAmount)

        deleteButton = requireActivity().findViewById(R.id.deleteButton)

        deleteButton.setOnClickListener {
            dashboardViewModel.send(DashboardEvent.DeleteTransactions)
            Toast.makeText(context, "Deleted all transactions!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        dashboardViewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is DashboardState.Error -> showError(state.error)
                is DashboardState.Success -> showDashboardData(state.amounts)
            }
        })
    }

    private fun showDashboardData(dashboardData: List<Float>) {
        try {
            totalExpense.text = dashboardData[0].toString()
        } catch (exception: Throwable) {
            totalExpense.text = "No data yet."
            Log.i("DASHBOARD DATA ERROR", "No EXPENSE to show")
        }

        try {
            totalIncome.text = dashboardData[1].toString()
        } catch (exception: Throwable) {
            totalIncome.text = "No data yet."
            Log.i("DASHBOARD DATA ERROR", "No INCOME to show")
        }

        try {
            totalAmount.text = dashboardData[2].toString()
        } catch (exception: Throwable) {
            Log.i("DASHBOARD DATA ERROR", "No TOTAL AMOUNT to show")
            totalAmount.text = "No data yet."
        }
    }

    private fun showError(error: Throwable) {
        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
        Log.i("Error", "Error in Dashboard", error)
    }
}
