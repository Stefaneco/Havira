package com.example.clean.db.entities

import androidx.room.Entity

@Entity
data class FridgeItemCategoryEntity(
    val categoryId: Int,
    val name: String
)