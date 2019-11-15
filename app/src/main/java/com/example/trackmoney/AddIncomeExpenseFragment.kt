package com.example.trackmoney


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
//import com.example.trackmoney.ui.home.HomeFragmentArgs

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

        if (arguments != null){
            val args = AddIncomeExpenseFragmentArgs.fromBundle(arguments!!)
            Toast.makeText(context, "NumCorrect: ${args.id}", Toast.LENGTH_LONG).show()
        }

        /*take the editView*/

        val edit = view.findViewById<EditText>(R.id.income_expense_amount)


        /*pass data from AddIncomeExpenseFragment to HomeFragment*/
        val saveButton = view.findViewById<Button>(R.id.save_income_expense_button)
        saveButton.setOnClickListener {

            val action = AddIncomeExpenseFragmentDirections.actionAddIncomeExpenseToNavigationHome2()
            action.setString(edit.text.toString())
            findNavController().navigate(action)
        }


//        val saveButton = view.findViewById<Button>(R.id.save_income_expense_button)
//        saveButton.setOnClickListener {
//            findNavController().navigate(R.id.action_addIncomeExpense_to_navigation_home)
//        }

    }
}
