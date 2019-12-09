package com.example.trackmoney.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trackmoney.R
import com.example.trackmoney.db.MoneyTransaction


class HomeAdapter :
    ListAdapter<MoneyTransaction, MoneyTransactionViewHolder>(MoneyTransactionDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyTransactionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.home_row, parent, false)
        return MoneyTransactionViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: MoneyTransactionViewHolder, position: Int) {
        val moneyTransaction = getItem(position)
        holder.moneyTransaction.text = moneyTransaction.amount.toString()

        // Onclick listener to show the details of a specific MoneyTransaction
        holder.moneyTransaction.setOnClickListener {
            // TODO
//            MoneyTransactionDetailFragment.openMoneyTransactionDetailActivity(
//                holder.moneyTransaction.context as Activity, moneyTransaction.id
//            )
        }
    }
}

class MoneyTransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val moneyTransaction = view.findViewById<TextView>(R.id.textView_home_row)
}

class MoneyTransactionDiffUtil : DiffUtil.ItemCallback<MoneyTransaction>() {
    override fun areItemsTheSame(oldItem: MoneyTransaction, newItem: MoneyTransaction): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MoneyTransaction, newItem: MoneyTransaction): Boolean {
        return oldItem == newItem
    }

}