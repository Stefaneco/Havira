package com.example.clean.ui.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean.di.UseCases
import com.example.core.entites.FridgeItem
import com.example.core.entites.ShoppingItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val useCases: UseCases
): ViewModel(){

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val shoppingItems = MutableLiveData<List<ShoppingItem>>()

    fun addShoppingItem(item: ShoppingItem){
        coroutineScope.launch {
            useCases.addShoppingItem(item)
            shoppingItems.postValue(useCases.getShoppingItemsMerged())
        }
    }

    fun loadItems(){
        coroutineScope.launch {
            shoppingItems.postValue(useCases.getShoppingItemsMerged())
        }
    }

    fun moveItemsToFridge(){
        coroutineScope.launch {
            shoppingItems.value?.filter { it.isChecked }?.let {
                Log.e("ShoppingViewModel", it.size.toString())
                for (checkedItem in it){
                    useCases.addItem(FridgeItem(checkedItem.name,checkedItem.amount,
                        checkedItem.unit,categories = listOf()))
                }
            }
            useCases.moveCheckedShoppingItemsToFridge()
            shoppingItems.postValue(useCases.getShoppingItemsMerged())
        }
    }

    fun changeCheckStateOfItem(itemName: String, unit: String, isChecked: Boolean){
        coroutineScope.launch {
            useCases.changeItemsCheckState(itemName,unit,isChecked)
        }
    }
}