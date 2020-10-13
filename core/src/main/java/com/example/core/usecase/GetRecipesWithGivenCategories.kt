package com.example.core.usecase

import com.example.core.entites.Recipe
import com.example.core.repository.IRecipeRepository
import com.example.core.repository.RecipeRepository

class GetAllRecipes(private val RecipeRepository: RecipeRepository) {
    suspend operator fun invoke() = RecipeRepository.getAll()
}