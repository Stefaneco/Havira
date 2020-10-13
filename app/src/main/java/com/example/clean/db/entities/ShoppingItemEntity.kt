package com.example.clean.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShoppingItem (
    @PrimaryKey
    val name: String,
    val amount: Float,
    val isChecked: Boolean = false
)