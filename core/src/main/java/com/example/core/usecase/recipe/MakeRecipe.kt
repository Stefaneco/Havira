package com.example.core.usecase.recipe

import com.example.core.entites.Recipe
import com.example.core.repository.ItemRepository
import com.example.core.repository.RecipeRepository

class MakeRecipe(private val recipeRepository: RecipeRepository,
                 private val fridgeRepository: ItemRepository) {
    suspend operator fun invoke(recipeName: String, date: String){
        val recipe = recipeRepository.getRecipeByName(recipeName)!!
        if (checkIfRecipeCanBeDone(recipe)){
            updateFridgeAndRecipeItems(recipe)
            recipe.lastMade = date
            recipeRepository.addRecipe(recipe)
        }
    }

    private fun checkIfRecipeCanBeDone(recipe: Recipe): Boolean{
        for (recipeItem in recipe.items){
            if (recipeItem.missing != 0.0f)
                return false
        }
        return true
    }

    private suspend fun updateFridgeAndRecipeItems(recipe: Recipe){
        for (recipeItem in recipe.items){
            fridgeRepository.getInFridgeItemByNameAndUnit(recipeItem.name, recipeItem.unit)?.let { fridgeItem ->
                fridgeItem.amount -= recipeItem.amount
                recipeItem.missing = maxOf(0.0f, recipeItem.amount - fridgeItem.amount)
                if(recipeItem.missing != 0.0f){
                    recipe.missingItems += 1
                }
                recipeRepository.upsertRecipeItems(listOf(recipeItem))
                fridgeRepository.add(fridgeItem)
            }
        }
    }

}
