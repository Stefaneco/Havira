package com.example.core.usecase.shopping

import com.example.core.repository.ShoppingRepository

class ChangeItemsCheckState(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke(name: String, unit: String, isChecked: Boolean){
        shoppingRepository.getShoppingItemsByNameUnitCheck(name, unit, isChecked).let {
            for (item in it){
                shoppingRepository.deleteShoppingItem(item)
                item.isChecked = !isChecked
                shoppingRepository.addShoppingItem(item)
            }
        }
    }
}