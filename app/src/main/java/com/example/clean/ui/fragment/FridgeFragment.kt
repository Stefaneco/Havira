package com.example.clean.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.example.clean.R
import com.example.clean.ui.adapter.*
import com.example.clean.ui.dialog.AddInFridgeItemDialog
import com.example.clean.ui.dialog.AddInFridgeItemListener
import com.example.clean.ui.viewmodel.FridgeViewModel
import com.example.core.entites.FridgeItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fridge.*

@AndroidEntryPoint
class FridgeFragment : Fragment(), FridgeItemDetailAction, RecipeCategoryAction {

    private val viewModel by viewModels<FridgeViewModel>()
    private val itemAdapter = FridgeItemAdapter(listOf(), this)
    private val categoriesAdapter = RecipeCategoryAdapter(this)
    private val selectedCategoriesAdapter = RecipeSelectedCategoryAdapter(this)

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
        setupCategorySearch()

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCategoriesAndItems()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.filteredItems.observe(viewLifecycleOwner, Observer {
            itemAdapter.updateItems(it)
        })
        viewModel.selectedCategories.observe(viewLifecycleOwner, Observer {
            selectedCategoriesAdapter.updateCategories(it)
        })
        viewModel.notSelectedCategoriesFiltered.observe(viewLifecycleOwner, Observer {
            categoriesAdapter.updateCategories(it)
        })
    }

    private fun setupRecyclerViews(){
        rv_fridgeItems.layoutManager = LinearLayoutManager(context)
        rv_fridgeItems.adapter = itemAdapter
        rv_fridgeItems.setHasFixedSize(true)
        rv_categoriesItems.apply {
            adapter = categoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }
        rv_selectedCategoriesItems.apply {
            adapter = selectedCategoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }
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

    override fun onItemLongClick(name: String, unit: String) {
        val action = FridgeFragmentDirections.actionFridgeFragmentToFridgeItemDetailFragment(name,unit)
        findNavController().navigate(action)
    }

    override fun checkCategory(category: String) {
        viewModel.checkCategory(category)
    }

    override fun uncheckCategory(category: String) {
        viewModel.uncheckCategory(category)
    }

    private fun setupCategorySearch(){
        et_categoriesSearchItems.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.setCategoryFilter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

}
