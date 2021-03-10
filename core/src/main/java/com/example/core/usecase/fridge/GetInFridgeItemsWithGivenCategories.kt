package com.example.core.usecase.fridge

import com.example.core.entites.FridgeItem
import com.example.core.repository.ItemRepository

class GetInFridgeItemsWithGivenCategories(private val itemRepository: ItemRepository) {
    suspend operator fun invoke(categories: List<String>): List<FridgeItem> {
        return itemRepository.getInFridge().filter { it.categories.containsAll(categories) }
    }
}