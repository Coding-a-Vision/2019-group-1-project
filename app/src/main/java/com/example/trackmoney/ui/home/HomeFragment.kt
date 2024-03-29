package com.example.trackmoney.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackmoney.ui.add_money_transaction.AddIncomeExpenseActivity
import com.example.trackmoney.R
import com.example.trackmoney.db.MoneyTransaction
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.random.Random

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // LayoutManager and Adapter
        homeAdapter = HomeAdapter()
        recyclerView_home.adapter = homeAdapter
        recyclerView_home.layoutManager = LinearLayoutManager(this.context)

        observeViewModel()

        setupViews()
    }

    private fun setupViews() {
        val addMoneyTransactionButton: FloatingActionButton =
            requireActivity().findViewById(R.id.fab)

        addMoneyTransactionButton.setOnClickListener {
            val intent = Intent(activity, AddIncomeExpenseActivity::class.java)
            startActivityForResult(intent, 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            if (data != null) {

                // Check if amount exists
                if (data.hasExtra("ADD_MONEY_TRANSACTION_RESULT_AMOUNT")) {

                    // Send AddMoneyTransaction Event
                    homeViewModel.send(
                        MoneyTransactionEvent.AddMoneyTransaction(
                            MoneyTransaction(
                                id = Random.nextInt().toString(),
                                amount = data.extras!!.getFloat("ADD_MONEY_TRANSACTION_RESULT_AMOUNT"),
                                date = data.extras!!.getString("ADD_MONEY_TRANSACTION_RESULT_DATE").toString(),
                                type = data.extras!!.getString(
                                    "ADD_MONEY_TRANSACTION_RESULT_TYPE",
                                    "expense"
                                ).toString(),
                                category = data.extras!!.getString("ADD_MONEY_TRANSACTION_RESULT_CATEGORY").toString()
                            )
                        )
                    )
                    Toast.makeText(context, "Transaction added.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Error, no data.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Error, please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        // Observer on homeViewModel state
        homeViewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is MoneyTransactionState.Error -> showError(state.error)
                is MoneyTransactionState.Success -> showMoneyTransactions(state.moneyTransactions)
            }
        })
    }

    private fun showError(error: Throwable) {
        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
        Log.i("Error", "Error in Home", error)
    }

    private fun showMoneyTransactions(moneyTransactions: List<MoneyTransaction>) {
        homeAdapter.submitList(moneyTransactions)

        moneyTransactions.forEach {
            var temp = it.id.plus(", ")
                .plus(it.amount).plus(", ")
                .plus(it.type).plus(", ")
                .plus(it.date).plus(", ")
                .plus(it.category)
            Log.i("ITEM: ", temp)
        }
    }
}