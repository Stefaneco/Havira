package com.example.clean.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.clean.db.dao.FridgeDao
import com.example.clean.db.dao.RecipeDao
import com.example.clean.db.dao.ShoppingDao
import com.example.clean.db.entities.fridge.FridgeItemEntity
import com.example.clean.db.entities.recipe.RecipeEntity
import com.example.clean.db.entities.ShoppingItemEntity
import com.example.clean.db.entities.fridge.FridgeItemCatCrossRef
import com.example.clean.db.entities.fridge.FridgeItemCategoryEntity
import com.example.clean.db.entities.recipe.RecipeCatCrossRef
import com.example.clean.db.entities.recipe.RecipeCategoriesEntity
import com.example.clean.db.entities.recipe.RecipeItemEntity

@Database(entities = [FridgeItemEntity::class, FridgeItemCatCrossRef::class, FridgeItemCategoryEntity::class,
    RecipeEntity::class, RecipeItemEntity::class, RecipeCatCrossRef::class, RecipeCategoriesEntity::class,
    ShoppingItemEntity::class], version = 9)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "appdb.db"

        private var instance: AppDatabase? = null

        private fun create(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration().build()

        fun getInstance(context: Context): AppDatabase =
            (instance ?: create(context)).also { instance = it }
        }

    abstract fun fridgeDao(): FridgeDao
    abstract fun recipeDao(): RecipeDao
    abstract fun shoppingDao(): ShoppingDao
    }
