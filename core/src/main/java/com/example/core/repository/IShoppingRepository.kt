package com.example.core.repository

import com.example.core.entites.ShoppingItem

interface IShoppingRepository {
    suspend fun addShoppingItem(item: ShoppingItem)

    suspend fun updateShoppingItem(item: ShoppingItem)

    suspend fun deleteShoppingItems(items: List<ShoppingItem>)

    suspend fun getAllShoppingItems(): List<ShoppingItem>

    suspend fun getAllCheckedItems(): List<ShoppingItem>

    suspend fun getShoppingItemByNameUnitAndRecipeName(name: String, unit: String, recipeName: String?): ShoppingItem?

    suspend fun getShoppingItemByName(name: String): List<ShoppingItem>
}