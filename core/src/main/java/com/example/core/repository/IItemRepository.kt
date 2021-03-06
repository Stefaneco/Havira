package com.example.core.repository

import com.example.core.entites.FridgeItem

interface IItemRepository {
    suspend fun add(item: FridgeItem)

    suspend fun addCategory(category: String)

    suspend fun delete(item: FridgeItem)

    suspend fun getInFridge(): List<FridgeItem>

    suspend fun getInFridgeItemByName(name: String): List<FridgeItem>

    suspend fun getInFridgeItemByNameAndUnit(name: String, unit: String): FridgeItem?

    suspend fun getItemByNameAndUnit(name: String, unit: String): FridgeItem?

    suspend fun getAllItemsCategories(): List<String>

}