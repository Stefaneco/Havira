package com.example.clean.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.clean.R
import com.example.clean.ui.viewmodel.FridgeItemDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fridge_item_detail.*

@AndroidEntryPoint
class FridgeItemDetailFragment : Fragment() {

    private val viewModel by viewModels<FridgeItemDetailViewModel>()
    private val args: FridgeItemDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fridge_item_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadItem(args.itemName,args.itemUnit)
        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.fridgeItem.observe(viewLifecycleOwner, Observer {
            et_fridgeDetail_amount.setText(it.amount.toString())
            et_fridgeDetail_name.setText(it.name)
            et_fridgeDetail_unit.setText(it.unit)
        })
    }

}