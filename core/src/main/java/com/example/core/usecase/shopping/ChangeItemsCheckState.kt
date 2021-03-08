package com.example.core.usecase.shopping

import com.example.core.repository.ShoppingRepository

class ChangeItemsCheckState(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke(name: String, unit: String, isChecked: Boolean){
        shoppingRepository.getShoppingItemsByNameUnitCheck(name, unit, isChecked).let {
            for (item in it){
                shoppingRepository.deleteShoppingItem(item)
                shoppingRepository.getShoppingItemsByNameUnitCheck(name,unit, !isChecked).forEach { similarItem ->
                    if(similarItem.recipeName == item.recipeName){
                        item.amount += similarItem.amount
                    }
                }
                item.isChecked = !isChecked
                shoppingRepository.addShoppingItem(item)
            }
        }
    }
}