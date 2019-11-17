package com.example.trackmoney.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.trackmoney.AddIncomeExpenseFragment
import com.example.trackmoney.R
//import com.example.trackmoney.ui.home.HomeFragmentArgs

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(this, Observer {
            textView.text = it
        })

//        val args: HomeFragmentArgs =
//           HomeFragmentArgs.fromBundle(arguments!!)
//          Toast.makeText(context, "NumCorrect: ${args.value}", Toast.LENGTH_LONG).show()
        return root
    }
}
