package com.example.core.repository

import com.example.core.entites.Recipe
import com.example.core.entites.RecipeItem

interface IRecipeRepository{
    suspend fun addRecipe(recipe: Recipe)

    suspend fun addRecipeCategory(category: String)

    suspend fun upsertRecipeItems(items: List<RecipeItem>)

    suspend fun delete(recipe: Recipe)

    suspend fun deleteRecipeCategory(recipeName: String, category: String)

    suspend fun getAll(): List<Recipe>

    suspend fun getByName(name: String): Recipe?

    suspend fun getRecipeItemsByName(name: String): List<RecipeItem>

    suspend fun getAllCategories(): List<String>

    suspend fun isRecipeNameInDatabase(name: String): Boolean

}