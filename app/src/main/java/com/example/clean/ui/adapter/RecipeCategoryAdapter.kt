package com.example.clean.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.R
import com.example.clean.databinding.CardRecipeCategoryBinding
import kotlinx.android.synthetic.main.card_recipe_category.view.*

class RecipeCategoryAdapter(val actions: RecipeCategoryAction):
    RecyclerView.Adapter<RecipeCategoryAdapter.RecipeCategoryViewHolder>() {
    private var categories: List<String> = listOf()

    fun updateCategories(newCategories: List<String>){
        categories = newCategories
        notifyDataSetChanged()
    }

    inner class RecipeCategoryViewHolder(private val binding: CardRecipeCategoryBinding): RecyclerView.ViewHolder(binding.root){
        private val name = binding.tvCardRecipeCategoryName
        private val layout = binding.cslCardRecipeCategoryLayout

        fun bind(category: String){
            name.text = category
            layout.setOnClickListener {
                    actions.checkCategory(category)
        }
    } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipeCategoryViewHolder(
        CardRecipeCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: RecipeCategoryViewHolder, position: Int) {

        val currentItem = categories[position]
        holder.bind(currentItem)

    }
}