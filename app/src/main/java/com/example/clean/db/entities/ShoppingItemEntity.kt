package com.example.clean.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.entites.ShoppingItem

@Entity(primaryKeys = ["name"])
data class ShoppingItemEntity (
    val name: String,
    val recipeName: String? = null,
    val amount: Float,
    val unit: String,
    val isChecked: Boolean = false
){
    companion object {
        fun fromShoppingItem(item: ShoppingItem) = ShoppingItemEntity(item.name,item.recipeName, item.amount,item.unit,item.isChecked)
    }

    fun toShoppingItem() = ShoppingItem(name, recipeName, amount, unit, isChecked)
}