package com.example.clean.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.R
import com.example.core.entites.FridgeItem
import kotlinx.android.synthetic.main.card_fridge_item.view.*

class FridgeItemAdapter(private var items: List<FridgeItem>) : RecyclerView.Adapter<FridgeItemAdapter.FridgeViewHolder>() {

    fun updateItems(newItems: List<FridgeItem>){
        items = newItems
        notifyDataSetChanged()
    }

    inner class FridgeViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val name = view.tv_name
        private val amount = view.tv_amount
        private val unit = view.tv_unit

        fun bind(item: FridgeItem){
            name.text = item.name
            amount.text = item.amount.toString()
            unit.text = item.unit
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FridgeViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_fridge_item, parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FridgeViewHolder, position: Int) {
        holder.bind(items[position])
    }
}