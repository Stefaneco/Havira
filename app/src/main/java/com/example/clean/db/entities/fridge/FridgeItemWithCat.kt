package com.example.clean.db.entities.fridge

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.core.entites.FridgeItem
import java.util.*

data class FridgeItemWithCat(
    @Embedded val item: FridgeItemEntity,
    @Relation(
        parentColumn = "name",
        entityColumn = "categoryName",
        associateBy = Junction(FridgeItemCatCrossRef::class)

    )
    val categories: List<FridgeItemCategoryEntity>
) {
    companion object {
        fun fromFridgeItem(fridgeItem: FridgeItem) = FridgeItemWithCat(
            FridgeItemEntity(fridgeItem.name,fridgeItem.amount,fridgeItem.unit,fridgeItem.insertDate),
            categories = fridgeItem.categories.map { FridgeItemCategoryEntity(it) }
        )
    }

    fun toFridgeItem() = FridgeItem(item.name,item.amount,item.unit,item.insertDate,
        categories.map { it.categoryName })
}