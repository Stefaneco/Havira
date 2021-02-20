package com.example.clean.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.R
import com.example.core.entites.RecipeItem
import kotlinx.android.synthetic.main.card_recipe_item.view.*

class RecipeItemAdapter(private var items: List<RecipeItem>): RecyclerView.Adapter<RecipeItemAdapter.RecipeItemViewHolder>() {

    fun updateItems(newItems: List<RecipeItem>){
        items = newItems
        notifyDataSetChanged()
    }

    inner class RecipeItemViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val name = view.tv_name
        private val amount = view.tv_amount
        private val unit = view.tv_unit

        fun bind(item: RecipeItem){
            name.text = item.name
            if (item.amount % 1.0 == 0.0)
                amount.text = item.amount.toInt().toString()
            else
                amount.text = item.amount.toString()
            unit.text = item.unit
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipeItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_fridge_item,parent,false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}