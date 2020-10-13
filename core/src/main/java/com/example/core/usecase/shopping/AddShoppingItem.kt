package com.example.core.usecase.shopping

import com.example.core.entites.ShoppingItem
import com.example.core.repository.ShoppingRepository

class AddShoppingItem(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke(item: ShoppingItem){

        shoppingRepository.getShoppingItemByNameUnitAndRecipeName(item.name,item.unit,item.recipeName)?.let {
            item.amount += it.amount
        }

        shoppingRepository.addShoppingItem(item)
    }
}