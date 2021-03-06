package com.example.core.usecase.recipe

import com.example.core.repository.RecipeRepository

class DeleteRecipeCategory(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(recipeName:String,category:String) =
        recipeRepository.deleteRecipeCategory(recipeName, category)
}