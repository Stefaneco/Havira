package com.example.core.usecase

import com.example.core.entites.ShoppingItem
import com.example.core.repository.ShoppingRepository

class AddShoppingItem(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke(item: ShoppingItem){

        shoppingRepository.getShoppingItemByNameAndUnit(item.name,item.unit)?.let {
            item.amount += it.amount
        }

        shoppingRepository.addShoppingItem(item)
    }
}