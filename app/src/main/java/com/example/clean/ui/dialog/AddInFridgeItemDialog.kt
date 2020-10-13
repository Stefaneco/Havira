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

        tv_add.setOnClickListener {
            dialogListener.onAddButtonClicked(FridgeItem(et_name.text.toString(),
                et_amount.text.toString().toFloat(), et_unit.text.toString(),categories = listOf()))
            dismiss()
        }

        tv_cancel.setOnClickListener {
            dismiss()
        }


    }
}