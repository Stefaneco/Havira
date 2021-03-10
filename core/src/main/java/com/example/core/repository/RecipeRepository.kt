package com.example.core.repository

import com.example.core.entites.Recipe
import com.example.core.entites.RecipeBase
import com.example.core.entites.RecipeItem

class RecipeRepository(private val iRecipeRepository: IRecipeRepository) {
    suspend fun addRecipe(recipe: Recipe) = iRecipeRepository.addRecipe(recipe)

    suspend fun addRecipeBase(recipe: RecipeBase) = iRecipeRepository.addRecipeBase(recipe)

    suspend fun addRecipeCategory(category: String) = iRecipeRepository.addRecipeCategory(category)

    suspend fun upsertRecipeItems(items: List<RecipeItem>) = iRecipeRepository.upsertRecipeItems(items)

    suspend fun deleteRecipe(recipe: Recipe) = iRecipeRepository.delete(recipe)

    suspend fun deleteRecipeCategory(recipeName: String, category: String) =
        iRecipeRepository.deleteRecipeCategory(recipeName,category)

    suspend fun getAll() = iRecipeRepository.getAll()

    suspend fun getRecipeByName(name: String) = iRecipeRepository.getByName(name)

    suspend fun getRecipeItemsByName(name: String) = iRecipeRepository.getRecipeItemsByName(name)

    suspend fun getAllCategories() = iRecipeRepository.getAllCategories()

    suspend fun isRecipeNameInDatabase(name: String) = iRecipeRepository.isRecipeNameInDatabase(name)



}