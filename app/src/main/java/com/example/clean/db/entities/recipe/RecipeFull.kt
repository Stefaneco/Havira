package com.example.clean.db.entities.recipe

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.core.entites.Recipe
import com.example.core.entites.RecipeItem

data class RecipeFull(
    @Embedded val recipe: RecipeWithItems,
    @Relation (
        parentColumn = "name",
        entityColumn = "categoryName",
        associateBy = Junction(RecipeCatCrossRef::class)
    )
    val categories: List<RecipeCategoriesEntity>
) {
    companion object {
        fun fromRecipe(recipe: Recipe) = RecipeFull(
            RecipeWithItems(RecipeEntity(recipe.name,recipe.description,recipe.cookTime,
                recipe.servings, recipe.rating,recipe.lastMade, recipe.missingItems),

            recipe.items.map { RecipeItemEntity(it.recipeName,it.name,
                it.unit,it.amount,it.missing, it.inOtherUnit) }),

            recipe.categories.map { RecipeCategoriesEntity(it) }
        )
    }

    fun toRecipe() = Recipe(recipe.recipe.name,recipe.recipe.description,
        recipe.items.map { RecipeItem(it.name,it.unit, it.recipeName,it.amount,it.missing) } ,
        recipe.recipe.cookTime, recipe.recipe.servings,recipe.recipe.rating, recipe.recipe.lastMade,
    categories.map { it.categoryName }, recipe.recipe.missingItems)
}