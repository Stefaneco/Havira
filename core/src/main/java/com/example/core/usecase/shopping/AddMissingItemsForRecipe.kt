package com.example.core.usecase.shopping

import com.example.core.entites.Recipe
import com.example.core.entites.ShoppingItem
import com.example.core.repository.ShoppingRepository

class AddMissingItemsForRecipe(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke(recipe: Recipe){
        shoppingRepository.getShoppingItemsByRecipe(recipe.name).let { shoppingItems ->
            for (recipeItem in recipe.items){
                if(recipeItem.missing > 0.0f){
                    shoppingItems.filter { recipeItem.name == it.name && recipeItem.unit == it.unit }.let { filteredShoppingItems ->
                        for (item in filteredShoppingItems){
                            recipeItem.missing = maxOf(0.0f, recipeItem.missing - item.amount)
                        }
                    }
                    if (recipeItem.missing == 0.0f){
                        recipe.missingItems -= 1
                    }
                }
            }
        }

        if (recipe.missingItems == 0){
            for (item in recipe.items){
                AddShoppingItem(shoppingRepository).invoke(ShoppingItem(item.name,recipe.name,item.amount,item.unit))
            }
        }
        else {
            recipe.items.filter { it.missing != 0.0f}.forEach {
                AddShoppingItem(shoppingRepository).invoke(ShoppingItem(it.name,recipe.name,it.missing,it.unit))
            }
        }
    }
}