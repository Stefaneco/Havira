package com.example.core.usecase

import com.example.core.repository.ItemRepository

class AddItemCategory(private val itemRepository: ItemRepository) {
    suspend operator fun invoke(category: String) = itemRepository.addCategory(category)
}