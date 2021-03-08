package com.example.clean.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.R
import com.example.clean.databinding.CardShoppingBinding
import com.example.core.entites.ShoppingItem
import kotlinx.android.synthetic.main.card_shopping.view.*

class ShoppingAdapter(private var items: List<ShoppingItem>, private val action: ShoppingCheckAction):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val CHECKED = 1
    private val UNCHECKED = 2

    fun updateItems(newItems: List<ShoppingItem>){
        items = newItems
        notifyDataSetChanged()
    }

    inner class CheckedViewHolder(private val binding: CardShoppingBinding): RecyclerView.ViewHolder(binding.root){
        fun setCheckedDetails(shoppingItem: ShoppingItem){
            binding.cbCardShoppingName.text = shoppingItem.name
            if(shoppingItem.amount % 1.0 == 0.0)
                binding.tvCardShoppingAmount.text = shoppingItem.amount.toInt().toString()
            else
                binding.tvCardShoppingAmount.text = shoppingItem.amount.toString()
            binding.tvCardShoppingUnit.text = shoppingItem.unit
            binding.cbCardShoppingName.isChecked = true
            binding.cbCardShoppingName.setOnCheckedChangeListener{ _, _ ->
                action.onItemClicked(shoppingItem.name,shoppingItem.unit,shoppingItem.isChecked)
            }
        }
    }

    inner class UncheckedViewHolder(private val binding: CardShoppingBinding): RecyclerView.ViewHolder(binding.root){
        fun setUncheckedDetails(shoppingItem: ShoppingItem){
            binding.cbCardShoppingName.text = shoppingItem.name
            if(shoppingItem.amount % 1.0 == 0.0)
                binding.tvCardShoppingAmount.text = shoppingItem.amount.toInt().toString()
            else
                binding.tvCardShoppingAmount.text = shoppingItem.amount.toString()
            binding.tvCardShoppingUnit.text = shoppingItem.unit
            binding.cbCardShoppingName.isChecked = false
            binding.cbCardShoppingName.setOnCheckedChangeListener{ _, _ ->
                action.onItemClicked(shoppingItem.name,shoppingItem.unit,shoppingItem.isChecked)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isChecked) CHECKED
        else UNCHECKED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if (viewType == CHECKED){
            val binding = CardShoppingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CheckedViewHolder(binding)
        } else{
            val binding = CardShoppingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            UncheckedViewHolder(binding)
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == CHECKED){
            val checkedHolder = holder as CheckedViewHolder
            checkedHolder.setCheckedDetails(items[position])
        }
        else{
            val uncheckedHolder = holder as UncheckedViewHolder
            uncheckedHolder.setUncheckedDetails(items[position])
        }
    }


}