package com.example.core.usecase.shopping

import com.example.core.repository.ShoppingRepository

class ChangeShoppingItemCheckedState(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke(itemName: String){
        for(shoppingItem in shoppingRepository.getShoppingItemByName(itemName)){
            shoppingItem.isChecked = !shoppingItem.isChecked
            shoppingRepository.updateShoppingItem(shoppingItem)
        }
    }

}