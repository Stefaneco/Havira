package com.example.core.usecase.shopping

import com.example.core.repository.ShoppingRepository

class GetAllShoppingItems(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke() = shoppingRepository.getAllShoppingItems()
}