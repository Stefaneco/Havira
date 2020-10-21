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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.clean.R
import com.example.clean.ui.adapter.RecipeCategoryAction
import com.example.clean.ui.adapter.RecipeCategoryAdapter
import com.example.clean.ui.adapter.RecipeSelectedCategoryAdapter
import com.example.clean.ui.viewmodel.FridgeItemDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fridge_item_detail.*
import kotlinx.android.synthetic.main.fragment_recipe_add.*

@AndroidEntryPoint
class FridgeItemDetailFragment : Fragment(), RecipeCategoryAction {

    private val viewModel by viewModels<FridgeItemDetailViewModel>()
    private val args: FridgeItemDetailFragmentArgs by navArgs()
    private val categoriesAdapter = RecipeCategoryAdapter(this)
    private val selectedCategoriesAdapter = RecipeSelectedCategoryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fridge_item_detail, container, false)
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
        viewModel.loadCategories()
        viewModel.loadItem(args.itemName,args.itemUnit)
    }

    private fun observeViewModel(){
        viewModel.selectedCategories.observe(viewLifecycleOwner, Observer {
            selectedCategoriesAdapter.updateCategories(it)
        })
        viewModel.notSelectedCategoriesFiltered.observe(viewLifecycleOwner, Observer {
            categoriesAdapter.updateCategories(it)
        })
        viewModel.fridgeItem.observe(viewLifecycleOwner, Observer {
            et_fridgeDetail_amount.setText(it.amount.toString())
            et_fridgeDetail_name.setText(it.name)
            et_fridgeDetail_unit.setText(it.unit)
        })
    }

    override fun checkCategory(category: String) {
        viewModel.checkCategory(category)
    }

    override fun uncheckCategory(category: String) {
        viewModel.uncheckCategory(category)
    }

    private fun setupRecyclerViews(){
        rv_fridgeDetail_categories.apply {
            adapter = categoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }
        rv_fridgeDetail_selectedCategories.apply {
            adapter = selectedCategoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    private fun setupButtonsOnClicks(){
        b_fridgeDetail_addCategory.setOnClickListener {
            viewModel.checkCategory(et_fridgeDetail_searchCategory.text.toString())
        }
        b_fridgeDetail_subbmit.setOnClickListener {
            viewModel.updateItem(et_fridgeDetail_name.text.toString(),
            et_fridgeDetail_amount.text.toString().toFloat(),
            et_fridgeDetail_unit.text.toString())
            findNavController().popBackStack()
        }
    }

    private fun setupCategorySearch(){
        et_fridgeDetail_searchCategory.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                viewModel.setCategoryFilter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

}