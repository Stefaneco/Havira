package com.example.clean.di

import com.example.core.usecase.fridge.*
import com.example.core.usecase.recipe.*
import com.example.core.usecase.shopping.*
import javax.inject.Inject

data class UseCases @Inject constructor(
    val addItem: AddItem,
    val addItemCategory: AddItemCategory,
    val addRecipe: AddRecipe,
    val addRecipeCategory: AddRecipeCategory,
    val deleteItem: DeleteItem,
    val deleteRecipe: DeleteRecipe,
    val deleteRecipeCategory: DeleteRecipeCategory,
    val getAllRecipeCategories: GetAllRecipeCategories,
    val getAllItemsCategories: GetAllItemsCategories,
    val getFridgeItemByNameAndUnit: GetFridgeItemByNameAndUnit,
    val getInFridgeItems: GetInFridgeItems,
    val getInFridgeItemsWithGivenCategories: GetInFridgeItemsWithGivenCategories,
    val getRecipeByName: GetRecipeByName,
    val getRecipesWithGivenCategories: GetRecipesWithGivenCategories,
    val isRecipeNameInDatabase: IsRecipeNameInDatabase,
    val makeRecipe: MakeRecipe,

    val addMissingItemsForRecipe: AddMissingItemsForRecipe,
    val addShoppingItem: AddShoppingItem,
    val changeItemsCheckState: ChangeItemsCheckState,
    val getShoppingItemsMerged: GetShoppingItemsMerged,
    val moveCheckedShoppingItemsToFridge: MoveCheckedShoppingItemsToFridge

)
