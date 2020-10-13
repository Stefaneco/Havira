package com.example.clean.db.dao

import androidx.room.*
import com.example.clean.db.entities.ShoppingItemEntity
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ShoppingDao {
    @Insert(onConflict = REPLACE)
    suspend fun addShoppingItem(item: ShoppingItemEntity)

    @Update
    suspend fun updateShoppingItem(item: ShoppingItemEntity)

    @Delete
    suspend fun deleteShoppingItems(items: List<ShoppingItemEntity>)

    @Query("SELECT * FROM ShoppingItemEntity")
    fun getAllShoppingItems(): List<ShoppingItemEntity>

    @Query("SELECT * FROM ShoppingItemEntity WHERE isChecked")
    fun getAllCheckedShoppingItems(): List<ShoppingItemEntity>

    @Query("SELECT * FROM ShoppingItemEntity WHERE name==:name AND unit==:unit AND recipeName==:recipeName")
    fun getShoppingItemByNameUnitAndRecipeName(name:String, unit: String, recipeName: String?): ShoppingItemEntity?

    @Query("SELECT * FROM ShoppingItemEntity WHERE name==:name")
    fun getShoppingItemByName(name:String): List<ShoppingItemEntity>
}