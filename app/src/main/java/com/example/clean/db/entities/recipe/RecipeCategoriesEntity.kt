package com.example.clean.db.entities.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeCategoriesEntity (
    @PrimaryKey
    val categoryName: String
)