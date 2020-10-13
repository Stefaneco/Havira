package com.example.clean.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.clean.R
import com.example.clean.ui.adapter.FridgeItemAdapter
import com.example.clean.ui.dialog.AddInFridgeItemDialog
import com.example.clean.ui.dialog.AddInFridgeItemListener
import com.example.clean.ui.viewmodel.FridgeViewModel
import com.example.core.entites.FridgeItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fridge.*

@AndroidEntryPoint
class FridgeFragment : Fragment() {

    private val viewModel by viewModels<FridgeViewModel>()
    private val adapter = FridgeItemAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fridge, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setupRecyclerViews()
        setupButtonsOnClicks()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getFridgeItems()
    }

    private fun observeViewModel() {
        viewModel.fridgeItems.observe(viewLifecycleOwner, Observer {
            adapter.updateItems(it)
        })
    }

    private fun setupRecyclerViews(){
        rv_fridgeItems.layoutManager = LinearLayoutManager(context)
        rv_fridgeItems.adapter = adapter
        rv_fridgeItems.setHasFixedSize(true)
    }

    private fun setupButtonsOnClicks(){
        b_addItem.setOnClickListener {
            AddInFridgeItemDialog(requireContext(), object : AddInFridgeItemListener{
                override fun onAddButtonClicked(item: FridgeItem) {
                    viewModel.addFridgeItem(item)
                }
            }).show()
        }
        b_categoriesItems.setOnClickListener {
            if(rv_categoriesItems.visibility == View.VISIBLE){
                rv_categoriesItems.visibility = View.GONE
                rv_selectedCategoriesItems.visibility = View.GONE
                et_categoriesSearchItems.visibility = View.GONE
            }
            else{
                rv_categoriesItems.visibility = View.VISIBLE
                rv_selectedCategoriesItems.visibility = View.VISIBLE
                et_categoriesSearchItems.visibility = View.VISIBLE
            }
        }
    }

}
