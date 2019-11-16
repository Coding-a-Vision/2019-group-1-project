package com.example.trackmoney.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trackmoney.Expense
import com.example.trackmoney.MainActivity
import com.example.trackmoney.MyAdapter
import com.example.trackmoney.R
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Called when user taps the Add Income/Expense button

        val fabButton = view.findViewById<FloatingActionButton>(R.id.fab)
        fabButton.setOnClickListener {
            findNavController().navigate(R.id.addIncomeExpense)
        }
//        val fabButton = view.findViewById<FloatingActionButton>(R.id.fab)
//        fabButton.setOnClickListener {
//            val action  = HomeFragmentDirections.actionNavigationHomeToAddIncomeExpense2()
//            action.setId("this is the message...")
//            findNavController().navigate(action)
//        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.categories_list)
        recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        val adapter = MyAdapter()
        recyclerView.adapter = adapter


        /*Take the argument from the AddIncomeExpenseFragment*/
        if (arguments != null){
            val args = HomeFragmentArgs.fromBundle(arguments!!)
            val money = Expense(args.string)

            // categoryList1 = categoryList1.plus(money).toMutableList() // così funziona

            // Create reference to ViewModel created inside the MainActivity
            // so when the fragment is recreated, the homeViewModel (essendo)inside the
            // the MainActivity isn't recreated. In this manner we can add element in
            // the categoryList1 in the HomeViewModel without the ricreation of the List

            val viewModel = (activity as MainActivity).homeViewModel

            viewModel.categoryList1.add(money)

            //submitlist send to adapter a list to display

            adapter.submitList(viewModel.categoryList1)



//            Log.i("DEBUG","Current list is ${adapter.currentList} and element count is ${adapter
//                .currentList.size} and array size is ${categoryList1.size} and first element is ${categoryList1[0]}")

//            Toast.makeText(context, " ${args.string}", Toast.LENGTH_LONG).show()
//            var dummyText: String = ""
//            for(dummy_data in it) {
//                dummyText += dummy_data + "\n"
//            }
//            textView.text = dummyText
//            textHome.text = Arrays.toString(args.string)
//            Toast.makeText(context, " ${args.string}", Toast.LENGTH_LONG).show()

        }
    }
}

//package com.example.trackmoney.ui.home
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProviders
//import androidx.navigation.fragment.findNavController
//import com.example.trackmoney.R
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//
//class HomeFragment : Fragment() {
//
//    private lateinit var homeViewModel: HomeViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        // Initialize the ViewModel
//        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
//
//        // Find the root
//        val root = inflater.inflate(R.layout.fragment_home, container, false)
//
//        // Get a reference for the textView in home fragment
//        val textView: TextView = root.findViewById(R.id.money_track)
//
//        // Change textView if HomeViewModel text changes
//        homeViewModel.text.observe(this, Observer {
//            var dummyText: String = ""
//            for(dummy_data in it) {
//                dummyText += dummy_data + "\n"
//            }
//            textView.text = dummyText
//        })
//
//        return root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        // Called when user taps the Add Income/Expense button
//        val fabButton = view.findViewById<FloatingActionButton>(R.id.fab)
//        fabButton.setOnClickListener {
//            findNavController().navigate(R.id.addIncomeExpense)
//        }
//    }
//}
//
