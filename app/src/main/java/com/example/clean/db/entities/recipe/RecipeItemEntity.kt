package com.example.clean.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeItemEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val amount: Float,
    var missing: Float
)