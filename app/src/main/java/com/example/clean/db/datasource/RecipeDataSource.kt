package com.example.clean.db.datasource

import android.content.Context
import android.util.Log
import com.example.clean.db.AppDatabase
import com.example.clean.db.entities.recipe.RecipeCatCrossRef
import com.example.clean.db.entities.recipe.RecipeCategoriesEntity
import com.example.clean.db.entities.recipe.RecipeEntity
import com.example.clean.db.entities.recipe.RecipeItemEntity
import com.example.core.entites.Recipe
import com.example.core.entites.RecipeItem
import com.example.core.repository.IRecipeRepository

class RecipeDataSource(context: Context): IRecipeRepository {
     private val recipeDao = AppDatabase.getInstance(context).recipeDao()

    override suspend fun addRecipe(recipe: Recipe) {
        recipeDao.addRecipeEntity(RecipeEntity.fromRecipe(recipe))
        Log.e("RecipeDataSource", recipe.missingItems.toString())
        recipeDao.upsertRecipeItems(RecipeItemEntity.fromRecipe(recipe))
        RecipeCatCrossRef.fromRecipe(recipe).let { recipeDao.addRecipeCatCrossRef(it) }
    }

    override suspend fun addRecipeCategory(category: String) {
        recipeDao.addRecipeCategory(RecipeCategoriesEntity(category))
    }

    override suspend fun upsertRecipeItems(items: List<RecipeItem>) =
        recipeDao.upsertRecipeItems(items.map { RecipeItemEntity.fromRecipeItem(it) })

    override suspend fun delete(recipe: Recipe) {
        recipeDao.deleteRecipeEntity(RecipeEntity.fromRecipe(recipe))
        RecipeCatCrossRef.fromRecipe(recipe).let { recipeDao.deleteRecipeCatCrossRef(it) }
        recipeDao.deleteRecipeItems(RecipeItemEntity.fromRecipe(recipe))
    }

    override suspend fun getAll() =
        recipeDao.getAll().map { it.toRecipe() }

    override suspend fun getByName(name: String): Recipe? =
        recipeDao.getByName(name)?.toRecipe()

    override suspend fun getRecipeItemsByName(name: String): List<RecipeItem> =
        recipeDao.getRecipeItemsByName(name).map { it.toRecipeItem() }

    override suspend fun getAllCategories(): List<String> =
        recipeDao.getAllRecipeCategories()

}