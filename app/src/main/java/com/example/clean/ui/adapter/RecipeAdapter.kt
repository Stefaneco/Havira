package com.example.clean.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.clean.R
import com.example.clean.databinding.CardRecipeBinding
import com.example.core.entites.Recipe
import kotlinx.android.synthetic.main.card_recipe.view.*

class RecipeAdapter(private var recipes: List<Recipe>, private val action: ItemDetailAction):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val GREEN = 1
    private val RED = 2

    fun updateItems(newRecipes: List<Recipe>){
        recipes = newRecipes
        notifyDataSetChanged()
    }

    inner class GreenViewHolder(private val binding: CardRecipeBinding): RecyclerView.ViewHolder(binding.root){
         fun setGreenDetails(recipe: Recipe){
             binding.root.setBackgroundColor(itemView.context.resources.getColor(R.color.colorOk))
             binding.tvCardRecipeName.text = recipe.name
             binding.tvCardRecipeRating.text = recipe.rating.toString()
             binding.tvCardRecipeServings.text = recipe.servings.toString()
             binding.tvCardRecipeTime.text = recipe.cookTime.toString()
             itemView.setOnClickListener {
                 action.onItemClick(recipe.name)
             }
        }
    }

    inner class RedViewHolder(private val binding: CardRecipeBinding): RecyclerView.ViewHolder(binding.root){
         fun setRedDetails(recipe: Recipe){
             binding.tvCardRecipeName.text = recipe.name
             binding.tvCardRecipeRating.text = recipe.rating.toString()
             binding.tvCardRecipeServings.text = recipe.servings.toString()
             binding.tvCardRecipeTime.text = recipe.cookTime.toString()
             itemView.setOnClickListener {
                 action.onItemClick(recipe.name)
             }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return if(viewType == GREEN){
            val binding = CardRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            GreenViewHolder(binding)
        } else {
            val binding = CardRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            RedViewHolder(binding)
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