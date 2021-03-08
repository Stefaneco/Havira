package com.example.clean.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.example.clean.R
import com.example.core.entites.FridgeItem
import kotlinx.android.synthetic.main.dialog_add_in_fridge_item.*

class AddInFridgeItemDialog(context: Context, private val dialogListener: AddInFridgeItemListener):
    AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_in_fridge_item)

        tv_addInFridgeItem_add.setOnClickListener {
            if(et_addInFridgeItem_name.text.any() && et_addInFridgeItem_amount.text.any()) {
                dialogListener.onAddButtonClicked(FridgeItem(et_addInFridgeItem_name.text.toString(),
                    et_addInFridgeItem_amount.text.toString().toFloat(), et_addInFridgeItem_unit.text.toString(),categories = listOf()))
                dismiss()
            }
        }

        tv_addInFridgeItem_cancel.setOnClickListener {
            dismiss()
        }
    }
}