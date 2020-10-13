package com.example.clean.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.R
import kotlinx.android.synthetic.main.card_recipe_category.view.*

class RecipeSelectedCategoryAdapter(val actions: RecipeCategoryAction):
    RecyclerView.Adapter<RecipeSelectedCategoryAdapter.RecipeCategoryViewHolder>() {
    private var categories: List<String> = listOf()

    fun updateCategories(newCategories: List<String>){
        categories = newCategories
        notifyDataSetChanged()
    }

    inner class RecipeCategoryViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val name = view.tv_name
        private val layout = view.categoryLayout

        fun bind(category: String){
            name.text = category
            layout.setOnClickListener {
                actions.uncheckCategory(category)
            }
        } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecipeCategoryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_recipe_category, parent, false)
    )

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: RecipeCategoryViewHolder, position: Int) {

        val currentItem = categories[position]
        holder.bind(currentItem)

    }
}