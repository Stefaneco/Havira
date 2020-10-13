package com.example.clean

import com.example.core.usecase.*
import javax.inject.Inject

data class UseCases @Inject constructor(
    val addItem: AddItem,
    val addRecipe: AddRecipe,
    val addRecipeCategory: AddRecipeCategory,
    val deleteItem: DeleteItem,
    val deleteRecipe: DeleteRecipe,
    val getAllRecipeCategories: GetAllRecipeCategories,
    val getInFridgeItems: GetInFridgeItems,
    val getRecipeByName: GetRecipeByName,
    val getRecipesWithGivenCategories: GetRecipesWithGivenCategories,
    val makeRecipe: MakeRecipe
)
