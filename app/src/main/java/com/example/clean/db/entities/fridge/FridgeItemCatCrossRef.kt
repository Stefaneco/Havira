package com.example.clean.db.entities.fridge

import androidx.room.Entity

@Entity(primaryKeys = ["categoryName", "name"])
data class FridgeItemWithCat(
    val name: String,
    val categoryName: String
)