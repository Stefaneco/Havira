package com.example.clean.db.datasource

import android.content.Context
import android.util.Log
import com.example.clean.db.AppDatabase
import com.example.clean.db.entities.fridge.FridgeItemCatCrossRef
import com.example.clean.db.entities.fridge.FridgeItemEntity
import com.example.clean.db.entities.fridge.FridgeItemWithCat
import com.example.clean.db.entities.recipe.RecipeItemEntity
import com.example.core.entites.FridgeItem
import com.example.core.repository.IItemRepository
import javax.inject.Inject

class FridgeDataSource (context: Context): IItemRepository {
    private val fridgeDao = AppDatabase.getInstance(context).fridgeDao()

    override suspend fun add(item: FridgeItem) {
        fridgeDao.add(FridgeItemEntity.fromFridgeItem(item))
        FridgeItemCatCrossRef.fromFridgeItem(item).let { fridgeDao.addFridgeItemCatCrossRef(it) }
    }

    override suspend fun delete(item: FridgeItem) {
        fridgeDao.delete(FridgeItemEntity.fromFridgeItem(item))
        FridgeItemCatCrossRef.fromFridgeItem(item).let { fridgeDao.deleteFridgeItemCatCrossRef(it) }
    }

    override suspend fun getInFridge(): List<FridgeItem> =
        fridgeDao.getInFridge().map { it.toFridgeItem() }

    override suspend fun getInFridgeItemByName(name: String): List<FridgeItem>? =
        fridgeDao.getInFridgeItemByName(name)?.map { it.toFridgeItem() }


    override suspend fun getInFridgeItemByNameAndUnit(name: String, unit: String): FridgeItem? =
        fridgeDao.getInFridgeItemByNameAndUnit(name, unit)?.toFridgeItem()


}