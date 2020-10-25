package com.example.clean.db.dao

import androidx.room.*
import com.example.clean.db.entities.ShoppingItemEntity
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ShoppingDao {
    @Insert(onConflict = REPLACE)
    suspend fun addShoppingItem(item: ShoppingItemEntity)

    @Update
    suspend fun updateShoppingItem(item:ShoppingItemEntity)

    @Delete
    suspend fun deleteShoppingItem(item: ShoppingItemEntity)

    @Query("SELECT * FROM ShoppingItemEntity WHERE name == :name AND recipeName == :recipeName AND unit == :unit AND isChecked == :isChecked")
    suspend fun getShoppingItem(name: String, recipeName: String, unit: String, isChecked: Boolean): ShoppingItemEntity?

    @Query("SELECT * FROM ShoppingItemEntity WHERE name == :name AND unit == :unit AND isChecked == :isChecked")
    suspend fun getShoppingItemsByNameUnitCheck(name: String,unit: String,isChecked: Boolean): List<ShoppingItemEntity>

    @Query("SELECT * FROM ShoppingItemEntity WHERE isChecked")
    suspend fun  getCheckedShoppingItems(): List<ShoppingItemEntity>

    @Query("SELECT * FROM ShoppingItemEntity")
    suspend fun getAllShoppingItems(): List<ShoppingItemEntity>

    @Query("SELECT * FROM ShoppingItemEntity WHERE recipeName == :recipeName")
    suspend fun getShoppingItemsByRecipe(recipeName: String): List<ShoppingItemEntity>
}