package com.example.clean.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.R
import com.example.clean.databinding.CardFridgeItemBinding
import com.example.core.entites.FridgeItem
import kotlinx.android.synthetic.main.card_fridge_item.view.*

class FridgeItemAdapter(private var items: List<FridgeItem>, private val action: FridgeItemDetailAction) :
    RecyclerView.Adapter<FridgeItemAdapter.FridgeViewHolder>() {

    fun updateItems(newItems: List<FridgeItem>){
        items = newItems
        notifyDataSetChanged()
    }

    inner class FridgeViewHolder(private val binding: CardFridgeItemBinding): RecyclerView.ViewHolder(binding.root){

        private val name = binding.tvCardFridgeItemName
        private val amount = binding.tvCardFridgeItemAmount
        private val unit = binding.tvCardFridgeItemUnit
        private val layout = binding.root
        //private val layout = view.fridgeItemLayout

        fun bind(item: FridgeItem){
            name.text = item.name
            if(item.amount % 1.0 == 0.0)
                amount.text = item.amount.toInt().toString()
            else
                amount.text = item.amount.toString()
            unit.text = item.unit
            layout.setOnLongClickListener {
                action.onItemLongClick(item.name,item.unit)
                true
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FridgeViewHolder(
        CardFridgeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FridgeViewHolder, position: Int) {
        holder.bind(items[position])
    }
}