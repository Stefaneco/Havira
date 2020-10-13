package com.example.clean.di

import com.example.core.usecase.*
import com.example.core.usecase.shopping.*
import javax.inject.Inject

data class UseCases @Inject constructor(
    val addItem: AddItem,
    val addRecipe: AddRecipe,
    val addRecipeCategory: AddRecipeCategory,
    val deleteItem: DeleteItem,
    val deleteRecipe: DeleteRecipe,
    val getAllRecipeCategories: GetAllRecipeCategories,
    val getInFridgeItems: GetInFridgeItems,
    val getRecipeByName: GetRecipeByName,
    val getRecipesWithGivenCategories: GetRecipesWithGivenCategories,
    val makeRecipe: MakeRecipe,

    val addShoppingItem: AddShoppingItem,
    val changeShoppingItemCheckedState: ChangeShoppingItemCheckedState,
    val deleteCheckedShoppingItems: DeleteCheckedShoppingItems,
    val getAllShoppingItemsSummedByName: GetAllShoppingItemsSummedByName,
    val getShoppingItemByName: GetShoppingItemByName
)
