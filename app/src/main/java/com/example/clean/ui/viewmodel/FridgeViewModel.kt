package com.example.clean.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.clean.di.UseCases
import com.example.core.entites.FridgeItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FridgeViewModel @ViewModelInject constructor(
    private val useCases: UseCases
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val fridgeItems = MutableLiveData<List<FridgeItem>>()

    fun getFridgeItems() {
        coroutineScope.launch {
            fridgeItems.postValue(useCases.getInFridgeItems())
        }
    }

    fun addFridgeItem(item: FridgeItem) {
        coroutineScope.launch {
            useCases.addItem(item)
            getFridgeItems()
        }
    }
}