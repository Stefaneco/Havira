package com.example.clean.di

import com.example.clean.db.datasource.FridgeDataSource
import com.example.clean.db.datasource.RecipeDataSource
import com.example.clean.db.datasource.ShoppingDataSource
import com.example.core.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class UseCasesInterfaceModule {
    @Binds
    abstract fun getRecipeRepositoryInterface(recipeDataSource: RecipeDataSource): IRecipeRepository
    @Binds
    abstract fun getItemRepositoryInterface(itemDataSource: FridgeDataSource): IItemRepository
    @Binds
    abstract fun getShoppingRepositoryInterface(shoppingDataSource: ShoppingDataSource): IShoppingRepository
}