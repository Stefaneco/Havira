package com.example.core.usecase.recipe

import com.example.core.entites.Recipe
import com.example.core.entites.RecipeBase
import com.example.core.repository.ItemRepository
import com.example.core.repository.RecipeRepository

class AddRecipe(private val recipeRepository: RecipeRepository, private val fridgeRepository: ItemRepository) {
    suspend operator fun invoke(recipe: Recipe) {
        for (recipeItem in recipe.items){
            fridgeRepository.getInFridgeItemByName(recipeItem.name).let {
                for (fridgeItem in it){
                    if (fridgeItem.unit != recipeItem.unit){
                        recipeItem.inOtherUnit = true
                    }
                    else {
                        recipeItem.missing = maxOf(0.0f, recipeItem.amount - fridgeItem.amount)
                    }
                }
            }
            if (recipeItem.missing != 0f) recipe.missingItems +=1
        }
        recipeRepository.addRecipe(recipe)
    }

    suspend operator fun invoke(recipe: RecipeBase){
        for (recipeItem in recipe.items){
            fridgeRepository.getInFridgeItemByName(recipeItem.name).let {
                for (fridgeItem in it){
                    if (fridgeItem.unit != recipeItem.unit){
                        recipeItem.inOtherUnit = true
                    } else {
                        recipeItem.missing = maxOf(0.0f, recipeItem.amount - fridgeItem.amount)
                    }
                }
            }
            if (recipeItem.missing != 0f) recipe.missingItems +=1
        }
        recipeRepository.addRecipeBase(recipe)
    }
}