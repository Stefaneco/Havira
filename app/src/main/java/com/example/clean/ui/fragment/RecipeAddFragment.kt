package com.example.clean.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.clean.R
import com.example.clean.ui.adapter.*
import com.example.clean.ui.adapter.decoration.MarginItemDecoration
import com.example.clean.ui.dialog.AddRecipeItemDialog
import com.example.clean.ui.dialog.AddRecipeItemListener
import com.example.clean.ui.viewmodel.RecipeAddViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipe_add.*

@AndroidEntryPoint
class RecipeAddFragment : Fragment(), RecipeCategoryAction {


    private val viewModel by viewModels<RecipeAddViewModel>()
    private val itemsAdapter = RecipeItemAdapter(listOf())
    private val categoriesAdapter = RecipeCategoryAdapter(this)
    private val selectedCategoriesAdapter = RecipeSelectedCategoryAdapter(this)
    private val args: RecipeAddFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setupRecyclerViews()
        setupButtonsOnClicks()
        setupCategorySearch()
        setupNameObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadCategories()
        args.recipeName?.let {
            viewModel.loadRecipe(it)

        }

    }

    override fun checkCategory(category: String) {
        viewModel.checkCategory(category)
    }

    override fun uncheckCategory(category: String) {
        viewModel.uncheckCategory(category)
    }

    private fun observeViewModel(){
        viewModel.items.observe(viewLifecycleOwner, Observer {
            itemsAdapter.updateItems(it) })
        viewModel.selectedCategories.observe(viewLifecycleOwner, Observer {
            selectedCategoriesAdapter.updateCategories(it) })
        viewModel.notSelectedCategoriesFiltered.observe(viewLifecycleOwner, Observer {
            categoriesAdapter.updateCategories(it) })
        viewModel.recipe.observe(viewLifecycleOwner, Observer {
            et_name.setText(it.name)
            et_instructions.setText(it.description)
            et_rating.setText(it.rating.toString())
            et_servings.setText(it.servings.toString())
            et_time.setText(it.cookTime.toString())
        })
        viewModel.isUpdateFinished.observe(viewLifecycleOwner, Observer {
            if(it){
                val action = RecipeAddFragmentDirections.actionRecipeAddFragmentToRecipeFragment()
                findNavController().navigate(action)
            }
        })
    }

    private fun setupRecyclerViews(){
        rv_items.apply{
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }
        rv_categoriesRecipeAd.apply {
            adapter = categoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(MarginItemDecoration(15))
        }
        rv_selectedCategoriesRecipeAd.apply {
            adapter = selectedCategoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(MarginItemDecoration(15))
        }
    }

    private fun setupButtonsOnClicks(){
        b_addItem.setOnClickListener {
            AddRecipeItemDialog(requireContext(), object : AddRecipeItemListener {
                override fun onAddButtonClicked(name: String, amount: Float, unit: String) {
                    viewModel.addItem(name,amount,unit)
                }
            }).show()
        }

        b_addCategory.setOnClickListener {
            viewModel.checkCategory(et_searchCategory.text.toString())
        }

        b_submit.setOnClickListener {
            if (!viewModel.isNameFree){
                Toast.makeText(requireContext(), "Name of recipe already in database!", Toast.LENGTH_LONG).show()
            }
            else
                viewModel.addRecipe(et_name.text.toString(), et_instructions.text.toString(), et_time.text.toString().toInt(),
                    et_servings.text.toString().toInt(),et_rating.text.toString().toInt())
            }
        }


    private fun setupCategorySearch(){
        et_searchCategory.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                viewModel.setCategoryFilter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupNameObserver(){
        et_name.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                viewModel.isNameFreeSetter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }



}