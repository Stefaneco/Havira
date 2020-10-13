package com.example.clean.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RecipeCategoriesEntity (
    @PrimaryKey(autoGenerate = true)
    val CategoryId: Int,
    val name: String
)