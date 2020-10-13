package com.example.core.usecase.shopping

import com.example.core.entites.ShoppingItem
import com.example.core.repository.ItemRepository
import com.example.core.repository.ShoppingRepository
import com.example.core.usecase.AddItem

class DeleteCheckedShoppingItems(private val shoppingRepository: ShoppingRepository) {

    suspend operator fun invoke(){
        shoppingRepository.deleteShoppingItems(shoppingRepository.getAllCheckedItems())
    }

}