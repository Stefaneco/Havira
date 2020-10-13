package com.example.clean.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean.di.UseCases
import com.example.core.entites.Recipe
import com.example.core.entites.RecipeSortBy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class RecipeViewModel @ViewModelInject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val filteredRecipes = MutableLiveData<List<Recipe>>()
    val selectedCategories = MutableLiveData<List<String>>()
    val notSelectedCategoriesFiltered = MutableLiveData<List<String>>()
    private val allCategories = TreeMap<String, Boolean>()
    private var categoryFilter = ""

    fun loadCategoriesAndRecipes(){
        coroutineScope.launch {
            useCases.getAllRecipeCategories().map { allCategories[it] = false }
            selectedCategories.postValue(allCategories.filter { it.value }.map { it.key})
            notSelectedCategoriesFiltered.postValue(allCategories.filter { !it.value && it.key.contains(categoryFilter) }.map { it.key })
            filteredRecipes.postValue(useCases.getRecipesWithGivenCategories(listOf()).sortedBy { it.name })
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

    private fun feedLiveData(){
        coroutineScope.launch {
            selectedCategories.postValue(allCategories.filter { it.value }.map { it.key})
            notSelectedCategoriesFiltered.postValue(allCategories.filter { !it.value && it.key.contains(categoryFilter) }.map { it.key })
            filteredRecipes.postValue(useCases.getRecipesWithGivenCategories(allCategories.filter { it.value }.map { it.key}))
        }
    }

    fun setCategoryFilter(search: String){
        categoryFilter = search
        feedLiveData()
    }

    fun sortRecipes(option: RecipeSortBy){
        when(option){
            RecipeSortBy.RATING -> filteredRecipes.postValue(filteredRecipes.value?.sortedBy { it.rating })
            RecipeSortBy.NAME -> filteredRecipes.postValue(filteredRecipes.value?.sortedBy { it.name })
            RecipeSortBy.COOK_TIME -> filteredRecipes.postValue(filteredRecipes.value?.sortedBy { it.cookTime })
            RecipeSortBy.MISSING_ITEMS_REVERSED -> filteredRecipes.postValue(
                filteredRecipes.value?.sortedBy {
                    it.items.filter { recipeItem -> recipeItem.missing > 0f  }.size
                }?.reversed()
            )
        }
    }

}