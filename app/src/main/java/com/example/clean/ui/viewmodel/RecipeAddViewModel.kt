package com.example.clean.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean.di.UseCases
import com.example.core.entites.Recipe
import com.example.core.entites.RecipeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class RecipeAddViewModel @ViewModelInject constructor(
    private val useCases: UseCases
): ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val items = MutableLiveData<List<RecipeItem>>()
    private val viewModelItems = mutableListOf<RecipeItem>()
    val selectedCategories = MutableLiveData<List<String>>()
    val notSelectedCategoriesFiltered = MutableLiveData<List<String>>()
    private val allCategories = TreeMap<String, Boolean>()
    private var categoryFilter = ""
    val recipe = MutableLiveData<Recipe>()

    fun addRecipe(name: String, description: String, cookTime: Int, servings: Int, rating: Int): Boolean{
        if (viewModelItems.any()) {
            val categories = allCategories.filter { it.value }.map { it.key }
            val recipe = Recipe(name, description, viewModelItems.map { RecipeItem(it.name,it.unit,name,it.amount) },
                cookTime, servings, rating, categories = categories)
            coroutineScope.launch {
                useCases.addRecipe(recipe)
            }
            return true
        }
        return false
    }

    fun loadCategories(){
        coroutineScope.launch {
            useCases.getAllRecipeCategories().map { allCategories[it] = false }
            notSelectedCategoriesFiltered.postValue(allCategories.filter { !it.value && it.key.contains(categoryFilter) }.map { it.key })
        }
    }

    fun loadRecipe(name: String){
        coroutineScope.launch {
            useCases.getRecipeByName(name)?.let { loadedRecipe ->
                recipe.postValue(loadedRecipe)
                loadedRecipe.categories.map { category -> allCategories[category] = true }
                viewModelItems.addAll(loadedRecipe.items)
                items.postValue(viewModelItems)
                selectedCategories.postValue(allCategories.filter { it.value }.map { it.key})
                notSelectedCategoriesFiltered.postValue(allCategories.filter { !it.value && it.key.contains(categoryFilter) }.map { it.key })
            }
        }
    }

    fun addItem(name: String, amount: Float, unit: String){
        viewModelItems.add(RecipeItem(name,unit,"", amount))
        coroutineScope.launch {
            items.postValue(viewModelItems)
        }
    }

    fun checkCategory(name: String) {
        if (!allCategories.containsKey(name) && name.any())
            coroutineScope.launch {
                useCases.addRecipeCategory(name)
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

