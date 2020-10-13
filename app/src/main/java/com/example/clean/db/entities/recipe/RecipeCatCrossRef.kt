package com.example.clean.db.entities.recipe

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.example.core.entites.Recipe

@Entity(primaryKeys = ["name", "categoryName"])
data class RecipeCatCrossRef(
    @ColumnInfo(index = true)
    val name: String,
    @ColumnInfo(index = true)
    val categoryName: String
) {
    companion object {
        fun fromRecipe(recipe: Recipe) = recipe.categories.let { list ->
            list.map { RecipeCatCrossRef(recipe.name,it) }
        }
    }
}