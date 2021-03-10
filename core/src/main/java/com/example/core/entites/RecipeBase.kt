package com.example.core.entites

data class RecipeBase (
    val name: String,
    val items: List<RecipeItem>,
    var lastMade: String? = null,
    val categories: List<String>,
    var missingItems: Int = 0
)