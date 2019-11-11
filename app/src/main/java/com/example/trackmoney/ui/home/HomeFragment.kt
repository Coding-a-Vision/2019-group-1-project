package com.example.trackmoney.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.trackmoney.AddIncomeExpenseActivity
import com.example.trackmoney.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initialize the ViewModel
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        // Find the root
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // Get a reference for the textView in home fragment
        val textView: TextView = root.findViewById(R.id.text_home)

        homeViewModel.addIncomeExpenseData("fourth")

        // Change textView if HomeViewModel text changes
        homeViewModel.text.observe(this, Observer {
            var dummyText: String = ""
            for(dummy_data in it) {
                dummyText += dummy_data + "\n"
            }
            textView.text = dummyText
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Called when user taps the Add Income/Expense button
        val fabButton = view.findViewById<FloatingActionButton>(R.id.fab)
        fabButton.setOnClickListener {
            val intent = Intent(activity, AddIncomeExpenseActivity::class.java)
            startActivity(intent)
        }
    }
}