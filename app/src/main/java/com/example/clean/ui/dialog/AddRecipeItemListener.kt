package com.example.clean.ui.dialog

import com.example.core.entites.RecipeItem

interface AddRecipeItemListener {
    fun onAddButtonClicked(name: String, amount: Float ,unit: String)
}