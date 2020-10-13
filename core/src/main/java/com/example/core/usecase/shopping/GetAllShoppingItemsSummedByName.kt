package com.example.core.usecase.shopping

import com.example.core.entites.ShoppingItem
import com.example.core.repository.ShoppingRepository

class GetAllShoppingItemsSummedByName(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke(): List<ShoppingItem> {
        val allItems = shoppingRepository.getAllShoppingItems().toMutableList()
        val summedItems = mutableListOf<ShoppingItem>()
        while (allItems.any()){
            val nextItem = allItems[0]
            summedItems.add(ShoppingItem(
                name = nextItem.name,
                unit = nextItem.unit,
                amount = allItems.filter { it.name == nextItem.name }.map { it.amount }.sum(),
                isChecked = nextItem.isChecked
            ))
            allItems.removeAll(allItems.filter { nextItem.name == it.name })
        }
        return summedItems.toList()
    }
}