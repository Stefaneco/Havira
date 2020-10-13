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

    override suspend fun deleteShoppingItems(items: List<ShoppingItem>) {
        shoppingDao.deleteShoppingItems(items.map { ShoppingItemEntity.fromShoppingItem(it) }) }

    override suspend fun getAllShoppingItems(): List<ShoppingItem> =
        shoppingDao.getAllShoppingItems().map { it.toShoppingItem() }

    override suspend fun getAllCheckedItems(): List<ShoppingItem> =
        shoppingDao.getAllCheckedShoppingItems().map { it.toShoppingItem() }

    override suspend fun getShoppingItemByNameUnitAndRecipeName(
        name: String, unit: String, recipeName: String?): ShoppingItem? =
        shoppingDao.getShoppingItemByNameUnitAndRecipeName(name,unit,recipeName)?.toShoppingItem()



    override suspend fun getShoppingItemByName(name: String): List<ShoppingItem> =
        shoppingDao.getShoppingItemByName(name)?.map { it.toShoppingItem()}
}