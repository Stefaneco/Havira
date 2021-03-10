package com.example.core.usecase.fridge

import com.example.core.repository.ItemRepository

class GetInFridgeItems(private val itemRepository: ItemRepository) {
    suspend operator fun invoke() = itemRepository.getInFridge()
}