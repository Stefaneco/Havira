package com.example.clean.db.entities.fridge

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.core.entites.FridgeItem

@Entity(primaryKeys = ["categoryName", "name"])
data class FridgeItemCatCrossRef(
    @ColumnInfo(index = true)
    val name: String,
    @ColumnInfo(index = true)
    val categoryName: String
) {
    companion object {
        fun fromFridgeItem(item: FridgeItem) = item.categories.let { list ->
            list.map{FridgeItemCatCrossRef(item.name, it)}
        }
    }
}