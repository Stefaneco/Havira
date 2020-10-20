package com.example.core.usecase

import com.example.core.repository.RecipeRepository

class IsRecipeNameInDatabase(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(name: String) = recipeRepository.isRecipeNameInDatabase(name)
}