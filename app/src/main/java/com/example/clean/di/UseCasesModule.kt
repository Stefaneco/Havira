package com.example.clean.di

import android.app.Application
import com.example.clean.db.datasource.FridgeDataSource
import com.example.clean.db.datasource.RecipeDataSource
import com.example.clean.db.datasource.ShoppingDataSource
import com.example.core.repository.IShoppingRepository
import com.example.core.repository.ItemRepository
import com.example.core.repository.RecipeRepository
import com.example.core.repository.ShoppingRepository
import com.example.core.usecase.*
import com.example.core.usecase.shopping.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
 class UseCasesModule {
    @Provides
    fun getUseCases(recipeRepository: RecipeRepository, itemRepository: ItemRepository, shoppingRepository: ShoppingRepository) =
        UseCases(
            AddItem(itemRepository, recipeRepository),
            AddItemCategory(itemRepository),
            AddRecipe(recipeRepository, itemRepository),
            AddRecipeCategory(recipeRepository),
            DeleteItem(itemRepository, recipeRepository),
            DeleteRecipe(recipeRepository),
            DeleteRecipeCategory(recipeRepository),
            GetAllRecipeCategories(recipeRepository),
            GetAllItemsCategories(itemRepository),
            GetFridgeItemByNameAndUnit(itemRepository),
            GetInFridgeItems(itemRepository),
            GetInFridgeItemsWithGivenCategories(itemRepository),
            GetRecipeByName(recipeRepository),
            GetRecipesWithGivenCategories(recipeRepository),
            IsRecipeNameInDatabase(recipeRepository),
            MakeRecipe(recipeRepository, itemRepository),

            AddMissingItemsForRecipe(shoppingRepository),
            AddShoppingItem(shoppingRepository),
            ChangeItemsCheckState(shoppingRepository),
            GetShoppingItemsMerged(shoppingRepository),
            MoveCheckedShoppingItemsToFridge(shoppingRepository,itemRepository,recipeRepository)

        )

    @Provides
    fun getRecipeDataSource(app: Application) = RecipeDataSource(app)

    @Provides
    fun getFridgeDataSource(app: Application) = FridgeDataSource(app)

    @Provides
    fun getShoppingDataSource(app: Application) = ShoppingDataSource(app)

    @Provides
    fun getRecipeRepository(iRecipeRepository: RecipeDataSource) = RecipeRepository(iRecipeRepository)

    @Provides
    fun getItemRepository(iItemRepository: FridgeDataSource) = ItemRepository(iItemRepository)

    @Provides
    fun getShoppingRepository(iShoppingRepository: IShoppingRepository) = ShoppingRepository(iShoppingRepository)


}