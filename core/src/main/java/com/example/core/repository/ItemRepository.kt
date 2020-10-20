package com.example.core.repository

import com.example.core.entites.FridgeItem

class ItemRepository(private val iItemRepository: IItemRepository) {
    suspend fun add(item: FridgeItem) = iItemRepository.add(item)

    suspend fun delete(item: FridgeItem) = iItemRepository.delete(item)

    suspend fun getInFridge() = iItemRepository.getInFridge()

    suspend fun getInFridgeItemByName(name: String) = iItemRepository.getInFridgeItemByName(name)

    suspend fun getInFridgeItemByNameAndUnit(name: String, unit: String) =
        iItemRepository.getInFridgeItemByNameAndUnit(name, unit)

    suspend fun getItemByNameAndUnit(name: String, unit:String) =
        iItemRepository.getItemByNameAndUnit(name,unit)

    suspend fun getAllItemsCategories() =
        iItemRepository.getAllItemsCategories()

}