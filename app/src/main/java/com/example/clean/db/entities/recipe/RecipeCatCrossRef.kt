package com.example.clean.db.entities.recipe

import androidx.room.Entity

@Entity(primaryKeys = ["name", "categoryName"])
data class RecipeCarCrossRef(
    val name: String,
    val categoryName: String
)