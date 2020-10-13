package com.example.core.entites

data class RecipeItem(
    val name: String,
    val unit: String,
    var recipeName: String,
    val amount: Float,
    var missing: Float = amount,
    var inOtherUnit: Boolean = false
)