package com.example.clean.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean.di.UseCases
import com.example.core.entites.FridgeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FridgeItemDetailViewModel @ViewModelInject constructor(
    private val useCases: UseCases
):ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val fridgeItem = MutableLiveData<FridgeItem>()
    val selectedCategories = MutableLiveData<List<String>>()
    val notSelectedCategoriesFiltered = MutableLiveData<List<String>>()

    fun loadItem(name: String, unit: String){
        coroutineScope.launch {
            useCases.getFridgeItemByNameAndUnit(name,unit)?.let {
                fridgeItem.postValue(it)
            }
        }
    }

    fun updateItem(){
        coroutineScope.launch {
            useCases.addItem(fridgeItem.value!!)
        }
    }

}
