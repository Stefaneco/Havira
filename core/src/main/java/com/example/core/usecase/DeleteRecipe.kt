package com.example.core.usecase

import com.example.core.entites.Recipe
import com.example.core.repository.IRecipeRepository
import com.example.core.repository.RecipeRepository

class DeleteRecipe(private val RecipeRepository: RecipeRepository) {
    suspend operator fun invoke(recipe: Recipe) = RecipeRepository.deleteRecipe(recipe)
}