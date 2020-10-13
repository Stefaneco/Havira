package com.example.clean.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean.di.UseCases
import com.example.core.entites.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*

class RecipeDetailViewModel @ViewModelInject constructor(
    private val useCases: UseCases
): ViewModel() {

    val recipe = MutableLiveData<Recipe>()

    fun loadRecipe(name: String){
        CoroutineScope(Dispatchers.IO).launch {
            recipe.postValue(useCases.getRecipeByName(name))
        }
    }
    fun makeRecipe(){
        val currentTime = Calendar.getInstance().time
        val formattedTime = DateFormat.getDateInstance(DateFormat.SHORT).format(currentTime)
        CoroutineScope(Dispatchers.IO).launch {
            useCases.makeRecipe(
                recipe.value!!.name,
                formattedTime
            )
        }
    }


}