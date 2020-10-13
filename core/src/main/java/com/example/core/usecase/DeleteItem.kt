package com.example.core.usecase

import com.example.core.entites.FridgeItem
import com.example.core.repository.IItemRepository
import com.example.core.repository.IRecipeRepository
import com.example.core.repository.ItemRepository

class DeleteItem(private val ItemRepository: ItemRepository) {
    suspend operator fun invoke(item: FridgeItem) = ItemRepository.delete(item)
}