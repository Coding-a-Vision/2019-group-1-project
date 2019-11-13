package com.example.trackmoney.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.trackmoney.AddIncomeExpenseActivity
import com.example.trackmoney.MainActivity
import com.example.trackmoney.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Get the ViewModel
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        // Inflate the layout to the fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateHomeText()

        // Called when user taps the Add Income/Expense button
        val fabButton = view.findViewById<FloatingActionButton>(R.id.fab)
        fabButton.setOnClickListener {
            val intent = Intent(activity, AddIncomeExpenseActivity::class.java)
            startActivityForResult(intent, 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1000 && resultCode == Activity.RESULT_OK) {
            if(data != null) {
                Log.i("ACTIVITY RESULT", data?.extras?.get("ADD_IE_RESULT").toString())
                val data_callback = data?.extras?.get("ADD_IE_RESULT").toString()
                homeViewModel.addIncomeExpenseData(data_callback)
                Toast.makeText(context, "Data added.", Toast.LENGTH_SHORT).show()
            } else {
                Log.i("ACTIVITY RESULT", "was NULL.")
                Toast.makeText(context, "Error, please try again.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Log.i("ACTIVITY RESULT", "was NULL.")
            Toast.makeText(context, "Error, please try again.", Toast.LENGTH_SHORT).show()
        }

        updateHomeText()
    }

    private fun updateHomeText() {
        // Get a reference for the textView to change in home fragment
        val home_text_view: TextView = view!!.findViewById(R.id.text_home)

        // Set home_text_view content getting the data from the ViewModel
        homeViewModel.text.observe(this, Observer {
            var dummyText: String = ""
            for(dummy_data in it) {
                dummyText += dummy_data + "\n"
            }
            home_text_view.text = dummyText
        })
    }
}