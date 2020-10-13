package com.example.core.repository

import com.example.core.entites.ShoppingItem

class ShoppingRepository(private val iShoppingRepository: IShoppingRepository) {

    suspend fun addShoppingItem(item: ShoppingItem) = iShoppingRepository.addShoppingItem(item)

    suspend fun updateShoppingItem(item: ShoppingItem) = iShoppingRepository.updateShoppingItem(item)

    suspend fun deleteShoppingItems(items: List<ShoppingItem>) = iShoppingRepository.deleteShoppingItems(items)

    suspend fun getAllShoppingItems() = iShoppingRepository.getAllShoppingItems()

    suspend fun getAllCheckedItems() = iShoppingRepository.getAllCheckedItems()

    suspend fun getShoppingItemByNameUnitAndRecipeName(name: String, unit: String, recipeName: String?) =
        iShoppingRepository.getShoppingItemByNameUnitAndRecipeName(name,unit, recipeName)

    suspend fun getShoppingItemByName(name: String) = iShoppingRepository.getShoppingItemByName(name)

}