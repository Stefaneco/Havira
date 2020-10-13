package com.example.clean.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.R
import com.example.core.entites.Recipe
import kotlinx.android.synthetic.main.card_recipe.view.*
import kotlinx.android.synthetic.main.card_recipe_green.view.*

class RecipeAdapter(private var recipes: List<Recipe>, private val action: ItemDetailAction):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val GREEN = 1
    private val RED = 2

    fun updateItems(newRecipes: List<Recipe>){
        recipes = newRecipes
        notifyDataSetChanged()
    }

    inner class GreenViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         fun setGreenDetails(recipe: Recipe){
            itemView.tv_recipeNameGreen.text = recipe.name
            itemView.tv_recipeRatingGreen.text = recipe.rating.toString()
            itemView.tv_recipeServingsGreen.text = recipe.servings.toString()
            itemView.tv_recipeCookTimeGreen.text = recipe.cookTime.toString()
             itemView.setOnClickListener {
                 action.onItemClick(recipe.name)
             }
        }
    }

    inner class RedViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         fun setRedDetails(recipe: Recipe){
            itemView.tv_name.text = recipe.name
            itemView.tv_rating.text = recipe.rating.toString()
            itemView.tv_servings.text = recipe.servings.toString()
            itemView.tv_time.text = recipe.cookTime.toString()
             itemView.setOnClickListener {
                 action.onItemClick(recipe.name)
             }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if(viewType == GREEN){
            view = LayoutInflater.from(parent.context).inflate(R.layout.card_recipe_green,
                parent, false)
            GreenViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.card_recipe,
                parent, false)
            RedViewHolder(view)
        }

    }

    override fun getItemCount() = recipes.size

    override fun getItemViewType(position: Int): Int {
        if (recipes[position].missingItems == 0){
            return GREEN
        }
        return RED
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == GREEN){
            val holderGreen = holder as GreenViewHolder
            holderGreen.setGreenDetails(recipes[position])
        }
        else {
            val holderRed = holder as RedViewHolder
            holderRed.setRedDetails(recipes[position])
        }
    }
}