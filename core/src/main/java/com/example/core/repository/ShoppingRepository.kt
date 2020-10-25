package com.example.core.repository

import com.example.core.entites.ShoppingItem

class ShoppingRepository(private val iShoppingRepository: IShoppingRepository) {

    suspend fun addShoppingItem(item: ShoppingItem) = iShoppingRepository.addShoppingItem(item)

    suspend fun updateShoppingItem(item: ShoppingItem) = iShoppingRepository.updateShoppingItem(item)

    suspend fun deleteShoppingItem(item: ShoppingItem) = iShoppingRepository.deleteShoppingItem(item)

    suspend fun getShoppingItem(name: String, recipeName: String, unit: String, isChecked: Boolean) =
        iShoppingRepository.getShoppingItem(name, recipeName, unit, isChecked)

    suspend fun getShoppingItemsByNameUnitCheck(name: String,unit: String,isChecked: Boolean) =
        iShoppingRepository.getShoppingItemsByNameUnitCheck(name, unit, isChecked)

    suspend fun getCheckedShoppingItems() = iShoppingRepository.getCheckedShoppingItems()

    suspend fun getAllShoppingItems() = iShoppingRepository.getAllShoppingItems()

    suspend fun getShoppingItemsByRecipe(recipeName: String) = iShoppingRepository.getShoppingItemsByRecipe(recipeName)

}