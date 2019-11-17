package com.example.trackmoney.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trackmoney.R
import kotlinx.android.synthetic.main.home_row.view.*

class HomeAdapter : RecyclerView.Adapter<HomeViewHolder>() {

    var home_transaction_data = listOf<String>("foo-1", "bar-1", "baz-1")

    // Custom setter
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val layout_inflater = LayoutInflater.from(parent.context)
        val cell_for_row = layout_inflater.inflate(R.layout.home_row, parent, false)
        return HomeViewHolder(cell_for_row)
    }

    override fun getItemCount(): Int {
        return home_transaction_data.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val home_transaction_data = home_transaction_data[position]
        holder.view.textView_home_row.text = home_transaction_data
    }
}

class HomeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

}