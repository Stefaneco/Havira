package com.example.clean.ui.dialog

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.example.clean.R
import com.example.clean.databinding.DialogAddInFridgeItemBinding
import com.example.core.entites.FridgeItem
import com.example.core.entites.RecipeItem
import kotlinx.android.synthetic.main.dialog_add_in_fridge_item.*

class AddRecipeItemDialog(context: Context, private val dialogListener: AddRecipeItemListener):
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_in_fridge_item)

        tv_addInFridgeItem_add.setOnClickListener {
            if(et_addInFridgeItem_name.text.any() && et_addInFridgeItem_amount.text.any()){
                dialogListener.onAddButtonClicked(
                    name = et_addInFridgeItem_name.text.toString(),
                    amount = et_addInFridgeItem_amount.text.toString().toFloat(),
                    unit = et_addInFridgeItem_unit.text.toString()
                )
                dismiss()
            }
        }

        tv_addInFridgeItem_cancel.setOnClickListener {
            dismiss()
        }
        
    }
}