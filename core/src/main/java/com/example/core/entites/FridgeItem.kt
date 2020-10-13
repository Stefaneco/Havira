package com.example.core.entites

data class FridgeItem(
    val name: String,
    var amount: Float,
    val unit: String,
    val insertDate: String? = null,
    var categories: List<String>
)