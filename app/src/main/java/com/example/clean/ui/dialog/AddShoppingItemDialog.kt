package com.example.clean.ui.dialog

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.example.clean.R
import com.example.core.entites.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_in_fridge_item.*

class AddShoppingItemDialog(context: Context, private val dialogListener: AddShoppingItemListener):
AppCompatDialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_in_fridge_item)

        tv_add.setOnClickListener {
            if(et_amount.text.any() && et_name.text.any()){
                dialogListener.addShoppingItem(ShoppingItem(
                    name = et_name.text.toString(),
                    recipeName = "",
                    amount = et_amount.text.toString().toFloat(),
                    unit = et_unit.text.toString()
                ))
                dismiss()
            }
        }
        tv_cancel.setOnClickListener {
            dismiss()
        }
    }
}