package com.example.clean.db.entities.fridge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.entites.FridgeItem

@Entity(primaryKeys = ["name", "unit"])
data class FridgeItemEntity(
    val name: String,
    val amount: Float,
    val unit: String,
    val insertDate: String? = null
) {
    companion object {
        fun fromFridgeItem(fridgeItem: FridgeItem) = FridgeItemEntity(
            fridgeItem.name,fridgeItem.amount,fridgeItem.unit,fridgeItem.insertDate)
    }
}