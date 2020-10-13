package com.example.clean.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.R
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

    inner class CheckedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun setCheckedDetails(shoppingItem: ShoppingItem){
            itemView.cb_nameShoppingCard.text = shoppingItem.name
            itemView.tv_amountShoppingCard.text = shoppingItem.amount.toString()
            itemView.tv_unitShoppingCard.text = shoppingItem.unit
            itemView.cb_nameShoppingCard.isChecked = true
            itemView.cb_nameShoppingCard.setOnCheckedChangeListener{ _, _ ->
                action.onItemClicked(shoppingItem.name)
            }
        }
    }

    inner class UncheckedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun setUncheckedDetails(shoppingItem: ShoppingItem){
            itemView.cb_nameShoppingCard.text = shoppingItem.name
            itemView.tv_amountShoppingCard.text = shoppingItem.amount.toString()
            itemView.tv_unitShoppingCard.text = shoppingItem.unit
            itemView.cb_nameShoppingCard.isChecked = false
            itemView.cb_nameShoppingCard.setOnCheckedChangeListener{ _, _ ->
                action.onItemClicked(shoppingItem.name)
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
            view = LayoutInflater.from(parent.context).inflate(R.layout.card_shopping,parent,false)
            CheckedViewHolder(view)
        } else{
            view = LayoutInflater.from(parent.context).inflate(R.layout.card_shopping,parent,false)
            UncheckedViewHolder(view)
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