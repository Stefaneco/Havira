package com.example.clean.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.entites.FridgeItem

@Entity
data class FridgeItemEntity(
    @PrimaryKey
    val name: String,
    val amount: Float,
    val unit: String,
    val categories: List<String>,
    val insertDate: String = ""
) {
    companion object{
        fun fromFridgeItem(item: FridgeItem) = FridgeItemEntity(item.name,item.amount,
            item.unit,item.categories, item.insertDate)
    }
    fun toFridgeItem() = FridgeItem(name, amount, unit, insertDate, categories)
}