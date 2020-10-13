package com.example.clean.ui.dialog

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.example.clean.R
import com.example.core.entites.FridgeItem
import com.example.core.entites.RecipeItem
import kotlinx.android.synthetic.main.dialog_add_in_fridge_item.*

class AddRecipeItemDialog(context: Context, private val dialogListener: AddRecipeItemListener):
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_in_fridge_item)

        tv_add.setOnClickListener {
            dialogListener.onAddButtonClicked(
                name = et_name.text.toString(),
                amount = et_amount.text.toString().toFloat(),
                unit = et_unit.text.toString()
            )
            dismiss()
        }

        tv_cancel.setOnClickListener {
            dismiss()
        }
        
    }
}