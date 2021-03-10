package com.example.core.usecase.recipe

import com.example.core.repository.IRecipeRepository
import com.example.core.repository.RecipeRepository

class GetRecipeByName(private val RecipeRepository: RecipeRepository) {
    suspend operator fun invoke(name: String) = RecipeRepository.getRecipeByName(name)
}