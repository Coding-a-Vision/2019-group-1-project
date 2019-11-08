package com.example.trackmoney


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass.
 */
class AddIncomeExpenseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_income_expense, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saveButton = view.findViewById<Button>(R.id.save_income_expense_button)
        saveButton.setOnClickListener {
            findNavController().navigate(R.id.action_addIncomeExpense_to_navigation_home)
        }

    }
}
