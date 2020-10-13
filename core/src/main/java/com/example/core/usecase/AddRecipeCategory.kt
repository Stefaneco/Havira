package com.example.core.usecase

import com.example.core.repository.RecipeRepository

class AddRecipeCategory(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(category: String) = recipeRepository.addRecipeCategory(category)
}