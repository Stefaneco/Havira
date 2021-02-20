package com.example.core.usecase.shopping
import com.example.core.entites.ShoppingItem
import com.example.core.repository.ShoppingRepository

class AddShoppingItem(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke(shoppingItem: ShoppingItem){
        shoppingRepository.getShoppingItem(shoppingItem.name,shoppingItem.recipeName,shoppingItem.unit,false)?.let {
            shoppingItem.amount += it.amount
        }
        shoppingRepository.addShoppingItem(shoppingItem)
    }
}