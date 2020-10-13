package com.example.clean.ui.dialog

import com.example.core.entites.RecipeSortBy

interface RecipeSortByListener {
    fun sortByOptionClicked(option: RecipeSortBy)
}