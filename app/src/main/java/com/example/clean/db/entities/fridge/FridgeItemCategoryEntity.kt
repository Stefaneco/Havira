package com.example.clean.db.entities.fridge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.entites.FridgeItem

@Entity
data class FridgeItemCategoryEntity(
    @PrimaryKey
    val categoryName: String
)