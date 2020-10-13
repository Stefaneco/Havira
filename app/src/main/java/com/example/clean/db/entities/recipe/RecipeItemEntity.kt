package com.example.clean.db.entities.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.core.entites.Recipe
import com.example.core.entites.RecipeItem

@Entity(primaryKeys = ["recipeName", "name"])
data class RecipeItemEntity (
    val recipeName: String,
    val name: String,
    val unit: String,
    val amount: Float,
    var missing: Float,
    // if item in fridge with different unit
    var inOtherUnit: Boolean = false
) {
    companion object {
        fun fromRecipe(recipe: Recipe) = recipe.items.map { RecipeItemEntity(
            it.recipeName,it.name, it.unit,it.amount,it.missing, it.inOtherUnit) }

        fun fromRecipeItem(item: RecipeItem) = RecipeItemEntity(item.recipeName,item.name,
        item.unit,item.amount,item.missing, item.inOtherUnit)

    }

    fun toRecipeItem() = RecipeItem(name,unit, recipeName, amount, missing, inOtherUnit)


}