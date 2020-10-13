package com.example.clean.db.entities.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.entites.FridgeItem
import com.example.core.entites.Recipe
import java.util.*

@Entity
data class RecipeEntity (
    @PrimaryKey
    val name: String,
    val description: String,
    val cookTime: Int,
    val servings: Int,
    val rating: Int,
    val lastMade: String? = null,
    val missingItems: Int
) {
    companion object {
        fun fromRecipe(recipe: Recipe) = RecipeEntity(recipe.name,recipe.description,recipe.cookTime,
        recipe.servings,recipe.rating,recipe.lastMade, recipe.missingItems)
    }

}