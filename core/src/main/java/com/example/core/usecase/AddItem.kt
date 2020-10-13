package com.example.core.usecase

import com.example.core.entites.FridgeItem
import com.example.core.repository.ItemRepository
import com.example.core.repository.RecipeRepository
import sun.rmi.runtime.Log

class AddItem(private val itemRepository: ItemRepository, private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(item: FridgeItem) {

        itemRepository.getInFridgeItemByNameAndUnit(item.name, item.unit)?.let {
                item.amount += it.amount
                item.categories = it.categories
        }

        recipeRepository.getRecipeItemsByName(item.name).let {
            for (recipeItem in it){
                val recipe = recipeRepository.getRecipeByName(recipeItem.recipeName)
                if (item.unit != recipeItem.unit){
                    recipeItem.inOtherUnit = true
                }
                else {
                    if (recipeItem.missing != 0f) {
                      recipeItem.missing = maxOf(0.0f, recipeItem.amount - item.amount)
                        if (recipeItem.missing == 0f) {
                            recipe!!.missingItems -= 1
                            recipeRepository.addRecipe(recipe!!)
                        }

                    }
                }
            }
            recipeRepository.upsertRecipeItems(it)
        }
        itemRepository.add(item)
    }
}