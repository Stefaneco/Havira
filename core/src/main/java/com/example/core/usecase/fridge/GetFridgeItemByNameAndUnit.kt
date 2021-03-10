package com.example.core.usecase.fridge

import com.example.core.repository.ItemRepository

class GetFridgeItemByNameAndUnit(private val itemRepository: ItemRepository) {
    suspend operator fun invoke(name: String, unit: String) = itemRepository.getItemByNameAndUnit(name, unit)
}