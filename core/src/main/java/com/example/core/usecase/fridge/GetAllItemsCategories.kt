package com.example.core.usecase.fridge

import com.example.core.repository.ItemRepository

class GetAllItemsCategories(private val itemRepository: ItemRepository) {
    suspend operator fun invoke() = itemRepository.getAllItemsCategories()
}