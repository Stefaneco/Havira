package com.example.core.usecase

import com.example.core.repository.RecipeRepository

class GetAllRecipeCategories(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke() = recipeRepository.getAllCategories()
}