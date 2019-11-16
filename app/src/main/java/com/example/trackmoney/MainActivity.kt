package com.example.trackmoney

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.trackmoney.ui.home.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {

    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        //By calling this method, the title in the action bar will automatically be updated when
        // the destination changes
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    
    // Called when user taps the Add Income/Expense button
//    fun addIncomeExpense(view: View) {
//        val intent = Intent(this, AddIncomeExpenseActivity::class.java)
//        startActivity(intent)
//    }
}
