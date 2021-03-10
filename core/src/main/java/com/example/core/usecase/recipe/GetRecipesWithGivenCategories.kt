package com.example.core.usecase.recipe

import com.example.core.entites.Recipe
import com.example.core.repository.RecipeRepository

class GetRecipesWithGivenCategories(private val recipeRepository: RecipeRepository) {
    suspend operator fun invoke(categories: List<String>): List<Recipe> {
        return recipeRepository.getAll().filter { it.categories.containsAll(categories) }
    }
}