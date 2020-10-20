package com.example.clean.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.clean.db.entities.fridge.FridgeItemCatCrossRef
import com.example.clean.db.entities.fridge.FridgeItemEntity
import com.example.clean.db.entities.fridge.FridgeItemWithCat

@Dao
interface FridgeDao {
    @Insert(onConflict = REPLACE)
    suspend fun add(item: FridgeItemEntity)

    @Insert(onConflict = REPLACE)
    suspend fun addFridgeItemCatCrossRef(crossRef: List<FridgeItemCatCrossRef>)

    @Delete
    suspend fun delete(item: FridgeItemEntity)

    @Delete
    suspend fun deleteFridgeItemCatCrossRef(crossRef: List<FridgeItemCatCrossRef>)

    @Transaction
    @Query("SELECT * FROM FridgeItemEntity WHERE amount > 0")
    fun getInFridge(): List<FridgeItemWithCat>

    @Transaction
    @Query("SELECT * FROM FridgeItemEntity WHERE name == :name AND amount > 0")
    fun getInFridgeItemByName(name: String): List<FridgeItemWithCat>?

    @Transaction
    @Query("SELECT * FROM FridgeItemEntity WHERE name == :name AND amount > 0 AND unit == :unit")
    fun getInFridgeItemByNameAndUnit(name: String, unit: String): FridgeItemWithCat?

    @Transaction
    @Query("SELECT * FROM FridgeItemEntity WHERE name == :name AND unit == :unit")
    fun getItemByNameAndUnit(name:String, unit: String): FridgeItemWithCat?

    @Query("SELECT categoryName FROM FridgeItemCategoryEntity")
    fun getAllItemsCategories(): List<String>
}