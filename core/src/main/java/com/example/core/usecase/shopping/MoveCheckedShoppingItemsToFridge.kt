package com.example.core.usecase.shopping

import com.example.core.entites.FridgeItem
import com.example.core.repository.ItemRepository
import com.example.core.repository.RecipeRepository
import com.example.core.repository.ShoppingRepository
import com.example.core.usecase.fridge.AddItem

class MoveCheckedShoppingItemsToFridge(private val shoppingRepository: ShoppingRepository,
private val fridgeRepository: ItemRepository, private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(){
        shoppingRepository.getCheckedShoppingItems().sortedBy { it.recipeName }.let { shoppingItems ->
            for (shoppingItem in shoppingItems){
                shoppingRepository.deleteShoppingItem(shoppingItem)
                AddItem(fridgeRepository,recipeRepository).invoke(FridgeItem(shoppingItem.name,shoppingItem.amount,shoppingItem.unit,categories = listOf()))
            }
        }
    }
}