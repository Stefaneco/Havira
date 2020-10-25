package com.example.clean.db.datasource

import android.content.Context
import com.example.clean.db.AppDatabase
import com.example.clean.db.entities.ShoppingItemEntity
import com.example.core.entites.ShoppingItem
import com.example.core.repository.IShoppingRepository

class ShoppingDataSource(context: Context): IShoppingRepository {

    private val shoppingDao = AppDatabase.getInstance(context).shoppingDao()

    override suspend fun addShoppingItem(item: ShoppingItem) {
        shoppingDao.addShoppingItem(ShoppingItemEntity.fromShoppingItem(item)) }

    override suspend fun updateShoppingItem(item: ShoppingItem) {
        shoppingDao.updateShoppingItem(ShoppingItemEntity.fromShoppingItem(item))
    }

    override suspend fun deleteShoppingItem(item: ShoppingItem) =
        shoppingDao.deleteShoppingItem(ShoppingItemEntity.fromShoppingItem(item))

    override suspend fun getShoppingItem(name: String, recipeName: String, unit: String, isChecked: Boolean): ShoppingItem? =
        shoppingDao.getShoppingItem(name, recipeName, unit, isChecked)?.toShoppingItem()

    override suspend fun getShoppingItemsByNameUnitCheck(name: String, unit: String, isChecked: Boolean): List<ShoppingItem> =
        shoppingDao.getShoppingItemsByNameUnitCheck(name, unit, isChecked).map { it.toShoppingItem() }

    override suspend fun getCheckedShoppingItems(): List<ShoppingItem> =
        shoppingDao.getCheckedShoppingItems().map { it.toShoppingItem() }

    override suspend fun getAllShoppingItems(): List<ShoppingItem> =
        shoppingDao.getAllShoppingItems().map { it.toShoppingItem() }

    override suspend fun getShoppingItemsByRecipe(recipeName: String): List<ShoppingItem> =
        shoppingDao.getShoppingItemsByRecipe(recipeName).map { it.toShoppingItem() }

}