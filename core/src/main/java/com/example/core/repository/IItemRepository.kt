package com.example.core.repository

import com.example.core.entites.FridgeItem

interface IItemRepository {
    suspend fun add(item: FridgeItem)

    suspend fun delete(item: FridgeItem)

    suspend fun getInFridge(): List<FridgeItem>?

    suspend fun getInFridgeItemByName(name: String): List<FridgeItem>?

    suspend fun getInFridgeItemByNameAndUnit(name: String, unit: String): FridgeItem?
}