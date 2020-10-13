package com.example.core.entites

import java.util.*

data class Recipe(
    val name: String,
    val description: String,
    val items: List<RecipeItem>,
    val cookTime: Int,
    val servings: Int,
    val rating: Int,
    var lastMade: String? = null,
    val categories: List<String>,
    var missingItems: Int = 0
)