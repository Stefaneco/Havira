package com.example.clean.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity (
    @PrimaryKey(autoGenerate = true)
    val recipeId: Int,
    val name: String,
    val description: String,
    val cookTime: Int,
    val servings: Int,
    val rating: Int,
    val lastMade: String
)