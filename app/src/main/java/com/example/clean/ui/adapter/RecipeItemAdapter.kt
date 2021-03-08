package com.example.clean.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.R
import com.example.clean.databinding.CardRecipeItemBinding
import com.example.core.entites.RecipeItem
import kotlinx.android.synthetic.main.card_recipe_item.view.*

class RecipeItemAdapter(private var items: List<RecipeItem>): RecyclerView.Adapter<RecipeItemAdapter.RecipeItemViewHolder>() {

    fun updateItems(newItems: List<RecipeItem>){
        items = newItems
        notifyDataSetChanged()
    }

    inner class RecipeItemViewHolder(private val binding: CardRecipeItemBinding): RecyclerView.ViewHolder(binding.root){

        private val name = binding.tvCardRecipeItemName
        private val amount = binding.tvCardRecipeItemAmount
        private val unit = binding.tvCardRecipeItemUnit

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
        CardRecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecipeItemViewHolder, position: Int) {
        holder.bind(items[position])
    }
}