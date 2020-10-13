package com.example.clean.ui.dialog

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.example.clean.R
import com.example.core.entites.RecipeSortBy
import kotlinx.android.synthetic.main.dialog_recipe_sort_by.*

class RecipeSortByDialog(context: Context, private val dialogListener: RecipeSortByListener):
AppCompatDialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_recipe_sort_by)

        tv_sortByRecipeName.setOnClickListener{
            dialogListener.sortByOptionClicked(RecipeSortBy.NAME)
            dismiss()
        }

        tv_sortByRecipeRating.setOnClickListener {
            dialogListener.sortByOptionClicked(RecipeSortBy.RATING)
            dismiss()
        }

        tv_sortByRecipeCookTime.setOnClickListener {
            dialogListener.sortByOptionClicked(RecipeSortBy.COOK_TIME)
            dismiss()
        }

        tv_sortByRecipeMissingReversed.setOnClickListener {
            dialogListener.sortByOptionClicked(RecipeSortBy.MISSING_ITEMS_REVERSED)
            dismiss()
        }
    }


}