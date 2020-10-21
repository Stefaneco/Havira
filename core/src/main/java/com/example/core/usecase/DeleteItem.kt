package com.example.core.usecase

import com.example.core.entites.FridgeItem
import com.example.core.repository.IItemRepository
import com.example.core.repository.IRecipeRepository
import com.example.core.repository.ItemRepository
import com.example.core.repository.RecipeRepository

class DeleteItem(private val itemRepository: ItemRepository, private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(item: FridgeItem) {

        recipeRepository.getRecipeItemsByName(item.name).let {
            for (recipeItem in it){
                val recipe = recipeRepository.getRecipeByName(recipeItem.recipeName)
                if (item.unit == recipeItem.unit){
                    if (recipeItem.missing == 0.0f){
                        recipe!!.missingItems += 1
                    }
                    recipeItem.missing = recipeItem.amount
                }
                else if (recipeItem.inOtherUnit &&
                        itemRepository.getInFridgeItemByName(recipeItem.name)!!.size == 1){
                    recipeItem.inOtherUnit = false
                }
            }
            recipeRepository.upsertRecipeItems(it)
        }

        itemRepository.delete(item)
    }
}