package com.example.clean.ui.adapter

interface ShoppingCheckAction {
    fun onItemClicked(itemName: String, unit: String, isChecked: Boolean)
}