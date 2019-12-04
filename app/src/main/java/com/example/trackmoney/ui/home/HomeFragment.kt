package com.example.trackmoney.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
=======
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trackmoney.AddIncomeExpenseActivity
>>>>>>> 0e41048c5817a15dd0313701e84672fdf1d12f77
import com.example.trackmoney.R
import com.example.trackmoney.model.MoneyTransaction
import com.google.android.material.floatingactionbutton.FloatingActionButton
<<<<<<< HEAD

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
=======
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.random.Random

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter
>>>>>>> 0e41048c5817a15dd0313701e84672fdf1d12f77

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
=======
        return inflater.inflate(R.layout.fragment_home, container, false)
>>>>>>> 0e41048c5817a15dd0313701e84672fdf1d12f77
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

<<<<<<< HEAD
        // Called when user taps the Add Income/Expense button
        val fabButton = view.findViewById<FloatingActionButton>(R.id.fab)
        fabButton.setOnClickListener {
            findNavController().navigate(R.id.addIncomeExpense)
        }
    }
}
=======
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

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
                // Send AddMoneyTransaction Event
                homeViewModel.send(
                    MoneyTransactionEvent.AddMoneyTransaction(
                        MoneyTransaction(
                            id = Random.nextInt().toString(),
                            amount = data.extras!!.get("ADD_MONEY_TRANSACTION_RESULT_AMOUNT").toString(),
                            type = "None." // TODO: Get it from data.extras
                        )
                    )
                )
                Toast.makeText(context, "Transaction added.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error, no data.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Error, please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        // Observer on homeViewModel state
        homeViewModel.state.observe(this, Observer { state ->
            when (state) {
                is MoneyTransactionState.Error -> showError(state.error)
                is MoneyTransactionState.Success -> showMoneyTransactions(state.moneyTransactions)
            }
        })
    }

    private fun showError(error: Throwable) {
        Log.i("SHOW ERROR", "Error: ", error)
        Toast.makeText(context, "Error!", Toast.LENGTH_SHORT).show()
    }

    private fun showMoneyTransactions(moneyTransactions: List<MoneyTransaction>) {
        homeAdapter.submitList(moneyTransactions)
    }
}
>>>>>>> 0e41048c5817a15dd0313701e84672fdf1d12f77
