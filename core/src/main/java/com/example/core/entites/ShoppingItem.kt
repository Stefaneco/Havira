package com.example.core.entites

data class ShoppingItem(
    val name: String,
    val recipeName: String,
    var amount: Float,
    val unit: String,
    var isChecked: Boolean = false
)