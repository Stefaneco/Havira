package com.example.clean.db.entities.recipe

import androidx.room.Embedded
import androidx.room.Relation

data class RecipeWithItems(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "name",
        entityColumn = "recipeName"
    )
    var items: List<RecipeItemEntity>
)