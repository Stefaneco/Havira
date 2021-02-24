package com.example.clean.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.clean.R
import com.example.clean.ui.adapter.*
import com.example.clean.ui.adapter.decoration.MarginItemDecorationHeight
import com.example.clean.ui.viewmodel.RecipeViewModel
import com.example.core.entites.RecipeSortBy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipe.*

@AndroidEntryPoint
class RecipeFragment : Fragment(), ItemDetailAction, RecipeCategoryAction {

    private val viewModel by viewModels<RecipeViewModel>()
    private val recipeAdapter = RecipeAdapter(listOf(), this)
    private val categoriesAdapter = RecipeCategoryAdapter(this)
    private val selectedCategoriesAdapter = RecipeSelectedCategoryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe, container, false)
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
        viewModel.loadCategoriesAndRecipes()
        observeViewModel()
    }

    override fun onItemClick(name: String) {
        val action = RecipeFragmentDirections.actionRecipeFragmentToRecipeDetailFragment(name)
        findNavController().navigate(action)
    }

    override fun checkCategory(category: String) {
        viewModel.checkCategory(category)
    }

    override fun uncheckCategory(category: String) {
        viewModel.uncheckCategory(category)
    }

    private fun observeViewModel(){
        viewModel.filteredRecipes.observe(viewLifecycleOwner, Observer { it ->
            recipeAdapter.updateItems(it)
        })
        viewModel.selectedCategories.observe(viewLifecycleOwner, Observer {
            selectedCategoriesAdapter.updateCategories(it)
        })
        viewModel.notSelectedCategoriesFiltered.observe(viewLifecycleOwner, Observer {
            categoriesAdapter.updateCategories(it)
        })
    }

    private fun setupRecyclerViews(){
        rv_recipes.layoutManager = LinearLayoutManager(context)
        rv_recipes.adapter = recipeAdapter
        rv_recipes.setHasFixedSize(true)
        rv_recipes.addItemDecoration(MarginItemDecorationHeight(5))

        rv_categoriesRecipe.apply {
            adapter = categoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }
        rv_selectedCategoriesRecipe.apply {
            adapter = selectedCategoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }

    }

    private fun setupButtonsOnClicks(){
        b_addRecipe.setOnClickListener {
            val action = RecipeFragmentDirections.actionRecipeFragmentToRecipeAddFragment(null)
            findNavController().navigate(action)
        }
        b_categories.setOnClickListener {
            if(et_searchCategoryRecipe.visibility == View.GONE){
                rv_categoriesRecipe.visibility = View.VISIBLE
                rv_selectedCategoriesRecipe.visibility = View.VISIBLE
                et_searchCategoryRecipe.visibility = View.VISIBLE
            }
            else{
                rv_categoriesRecipe.visibility = View.GONE
                rv_selectedCategoriesRecipe.visibility = View.GONE
                et_searchCategoryRecipe.visibility = View.GONE
            }
        }
        b_sortByRecipe.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), it)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.menu_b_sort_name ->{
                        viewModel.sortRecipes(RecipeSortBy.NAME)
                        true
                    }
                    R.id.menu_b_sort_missing ->{
                        viewModel.sortRecipes(RecipeSortBy.MISSING_ITEMS_REVERSED)
                        true
                    }
                    R.id.menu_b_sort_rating ->{
                        viewModel.sortRecipes(RecipeSortBy.RATING)
                        true
                    }
                    R.id.menu_b_sort_time ->{
                        viewModel.sortRecipes(RecipeSortBy.COOK_TIME)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.sort_recipies_menu)
            popupMenu.show()
        }
    }

    private fun setupCategorySearch(){
        et_searchCategoryRecipe.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.setCategoryFilter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

}