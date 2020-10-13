package com.example.core.entites

data class ShoppingItem(
    val name: String,
    val recipeName: String? = null,
    var amount: Float,
    val unit: String,
    var isChecked: Boolean = false
)