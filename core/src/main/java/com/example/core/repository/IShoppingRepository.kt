package com.example.core.repository

import com.example.core.entites.ShoppingItem

interface IShoppingRepository {
    suspend fun addShoppingItem(item: ShoppingItem)

    suspend fun updateShoppingItem(item: ShoppingItem)

    suspend fun deleteShoppingItem(item: ShoppingItem)

    suspend fun getShoppingItem(name: String, recipeName: String, unit: String, isChecked: Boolean): ShoppingItem?

    suspend fun getShoppingItemsByNameUnitCheck(name: String, unit: String, isChecked: Boolean): List<ShoppingItem>

    suspend fun getCheckedShoppingItems(): List<ShoppingItem>

    suspend fun getAllShoppingItems(): List<ShoppingItem>

    suspend fun getShoppingItemsByRecipe(recipeName: String): List<ShoppingItem>

}