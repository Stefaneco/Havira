package com.example.core.usecase.shopping

import com.example.core.entites.ShoppingItem
import com.example.core.repository.ShoppingRepository

class GetShoppingItemsMerged(private val shoppingRepository: ShoppingRepository) {
    suspend operator fun invoke(): List<ShoppingItem>{
        val newList = mutableListOf<ShoppingItem>()
        shoppingRepository.getAllShoppingItems().toMutableList().let { all ->
            while (all.any()){
                all.filter { it.isChecked == all[0].isChecked && it.name == all[0].name && it.unit == all[0].unit }.let {
                    val newItem = ShoppingItem(all[0].name, "", 0.0f,all[0].unit,all[0].isChecked)
                    for (item in it){
                        newItem.amount += item.amount
                        all.remove(item)
                    }
                    newList.add(newItem)
                }
            }
        }
        return newList.sortedBy { it.name }
    }
}