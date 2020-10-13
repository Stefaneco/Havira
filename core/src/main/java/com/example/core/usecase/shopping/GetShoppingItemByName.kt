package com.example.core.usecase.shopping

import com.example.core.repository.ShoppingRepository

class GetShoppingItemByName(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke(name: String) = shoppingRepository.getShoppingItemByName(name)
}