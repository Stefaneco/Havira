package com.example.clean.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean.di.UseCases
import com.example.core.entites.FridgeItem
import com.example.core.entites.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class FridgeViewModel @ViewModelInject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)


    val filteredItems = MutableLiveData<List<FridgeItem>>()
    val selectedCategories = MutableLiveData<List<String>>()
    val notSelectedCategoriesFiltered = MutableLiveData<List<String>>()
    private val allCategories = TreeMap<String, Boolean>()
    private var categoryFilter = ""


    fun loadCategoriesAndItems(){
        coroutineScope.launch {
            useCases.getAllItemsCategories().map { allCategories[it] = false }
            selectedCategories.postValue(allCategories.filter { it.value }.map { it.key})
            notSelectedCategoriesFiltered.postValue(allCategories.filter { !it.value && it.key.contains(categoryFilter) }.map { it.key })
            filteredItems.postValue(useCases.getInFridgeItemsWithGivenCategories(listOf()).sortedBy { it.name })
        }
    }

    fun addFridgeItem(item: FridgeItem) {
        coroutineScope.launch {
            useCases.addItem(item)
            filteredItems.postValue(useCases.getInFridgeItemsWithGivenCategories(allCategories.filter { it.value }.map { it.key}))
        }
    }

    fun checkCategory(name: String) {
        allCategories[name] = true
        feedLiveData()
    }

    fun uncheckCategory(name: String){
        allCategories[name] = false
        feedLiveData()
    }

    fun setCategoryFilter(search: String){
        categoryFilter = search
        feedLiveData()
    }

    private fun feedLiveData(){
        coroutineScope.launch {
            selectedCategories.postValue(allCategories.filter { it.value }.map { it.key})
            notSelectedCategoriesFiltered.postValue(allCategories.filter { !it.value && it.key.contains(categoryFilter) }.map { it.key })
            filteredItems.postValue(useCases.getInFridgeItemsWithGivenCategories(allCategories.filter { it.value }.map { it.key}))
        }
    }
}