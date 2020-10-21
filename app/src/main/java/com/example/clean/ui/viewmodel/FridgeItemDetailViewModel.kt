package com.example.clean.ui.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean.di.UseCases
import com.example.core.entites.FridgeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class FridgeItemDetailViewModel @ViewModelInject constructor(
    private val useCases: UseCases
):ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val fridgeItem = MutableLiveData<FridgeItem>()
    val selectedCategories = MutableLiveData<List<String>>()
    val notSelectedCategoriesFiltered = MutableLiveData<List<String>>()
    private val allCategories = TreeMap<String, Boolean>()
    private var categoryFilter = ""
    val isUpdateFinished = MutableLiveData<Boolean>()

    fun loadItem(name: String, unit: String){
        coroutineScope.launch {
            useCases.getFridgeItemByNameAndUnit(name,unit)?.let { loadedItem ->
                fridgeItem.postValue(loadedItem)
                loadedItem.categories.map { category -> allCategories[category] = true }
                selectedCategories.postValue(allCategories.filter { it.value }.map { it.key})
                notSelectedCategoriesFiltered.postValue(allCategories.filter { !it.value && it.key.contains(categoryFilter) }.map { it.key })
                isUpdateFinished.postValue(false)
            }
        }
    }

    fun updateItem(name: String, amount: Float, unit: String){
        val categories = allCategories.filter { it.value }.map { it.key }
        val newItem = FridgeItem(name,amount,unit, categories = categories)
        coroutineScope.launch {
            fridgeItem.value?.let {
                useCases.deleteItem(it)
            }
            useCases.addItem(newItem)
            isUpdateFinished.postValue(true)
        }
    }

    fun loadCategories(){
        coroutineScope.launch {
            useCases.getAllItemsCategories().map { allCategories[it] = false }
            notSelectedCategoriesFiltered.postValue(allCategories.filter { !it.value && it.key.contains(categoryFilter) }.map { it.key })
        }
    }

    fun checkCategory(name: String) {
        if (!allCategories.containsKey(name) && name.any())
            coroutineScope.launch {
                useCases.addItemCategory(name)
            }
        allCategories[name] = true
        feedLiveData()
    }

    fun uncheckCategory(name: String){
        allCategories[name] = false
        feedLiveData()
    }

    private fun feedLiveData(){
        coroutineScope.launch {
            selectedCategories.postValue(allCategories.filter { it.value }.map { it.key})
            notSelectedCategoriesFiltered.postValue(allCategories.filter { !it.value && it.key.contains(categoryFilter) }.map { it.key })
        }
    }

    fun setCategoryFilter(search: String){
        categoryFilter = search
        feedLiveData()
    }

}
