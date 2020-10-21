package com.example.clean.di

import com.example.core.usecase.*
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

    val addShoppingItem: AddShoppingItem,
    val changeShoppingItemCheckedState: ChangeShoppingItemCheckedState,
    val deleteCheckedShoppingItems: DeleteCheckedShoppingItems,
    val getAllShoppingItemsSummedByName: GetAllShoppingItemsSummedByName,
    val getShoppingItemByName: GetShoppingItemByName
)
