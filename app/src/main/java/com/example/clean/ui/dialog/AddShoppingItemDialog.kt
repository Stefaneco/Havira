package com.example.clean.ui.dialog

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.example.clean.R
import com.example.clean.databinding.DialogAddInFridgeItemBinding
import com.example.core.entites.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_in_fridge_item.*

class AddShoppingItemDialog(context: Context, private val dialogListener: AddShoppingItemListener):
AppCompatDialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_in_fridge_item)

        tv_addInFridgeItem_add.setOnClickListener {
            if(et_addInFridgeItem_amount.text.any() && et_addInFridgeItem_name.text.any()){
                dialogListener.addShoppingItem(ShoppingItem(
                    name = et_addInFridgeItem_name.text.toString(),
                    recipeName = "",
                    amount = et_addInFridgeItem_amount.text.toString().toFloat(),
                    unit = et_addInFridgeItem_unit.text.toString()
                ))
                dismiss()
            }
        }
        tv_addInFridgeItem_cancel.setOnClickListener {
            dismiss()
        }
    }
}