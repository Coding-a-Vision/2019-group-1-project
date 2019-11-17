package com.example.trackmoney

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MyAdapter :
    ListAdapter<Expense, CategoryViewHolder>(CategoryDiffCallback()) {

    /*In this method we do the inflate of the view listitem*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)
        return CategoryViewHolder(view)
    }

    /*In this method we do the iteration of the list. We take the element in the list
    * and do the setText(holder.categoryName.text = it.name) and the operation in the single element*/

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        category.let {
            holder.categoryName.text = it.money
//            holder.categoryName.setOnClickListener {
//                Toast.makeText(
//                    holder.itemView.context,
//                    "clicked category: ${holder.categoryName.text}",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
        }
    }
}

/*Hold the referencies of the view of single element
* If we have two element in listitem.xml, we can 2 findViewById, 1 for each element*/

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val categoryName = itemView.findViewById<View>(R.id.category_name) as TextView

}

class CategoryDiffCallback : DiffUtil.ItemCallback<Expense>() {
    override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem.money == newItem.money
    }

    override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
        return oldItem == newItem
    }
}