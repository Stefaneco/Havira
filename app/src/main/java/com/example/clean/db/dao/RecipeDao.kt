package com.example.clean.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.clean.db.entities.recipe.*

@Dao
interface RecipeDao {
    @Insert(onConflict = REPLACE)
    suspend fun addRecipeEntity(recipe: RecipeEntity)

    @Insert(onConflict = REPLACE)
    suspend fun addRecipeCatCrossRef(recipeCategories: List<RecipeCatCrossRef>)

    @Insert(onConflict = REPLACE)
    suspend fun upsertRecipeItems(items: List<RecipeItemEntity>)

    @Insert
    suspend fun addRecipeCategory(category: RecipeCategoriesEntity)

    @Delete
    suspend fun deleteRecipeEntity(recipe: RecipeEntity)

    @Delete
    suspend fun deleteRecipeItems(recipeItems: List<RecipeItemEntity>)

    @Delete
    suspend fun deleteRecipeCatCrossRef(recipeCategories: List<RecipeCatCrossRef>)


    @Transaction
    @Query("SELECT * FROM RecipeEntity ORDER BY name")
    fun getAll(): List<RecipeFull>

    @Transaction
    @Query("SELECT * FROM RecipeEntity WHERE name == :name ")
    fun getByName(name: String): RecipeFull?

    @Transaction
    @Query("SELECT * FROM RecipeItemEntity WHERE name == :name")
    fun getRecipeItemsByName(name: String): List<RecipeItemEntity>

    @Query("SELECT categoryName FROM RecipeCategoriesEntity")
    fun getAllRecipeCategories(): List<String>
}