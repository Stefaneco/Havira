package com.example.clean.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.clean.R
import com.example.clean.ui.adapter.RecipeDetailCategoryAdapter
import com.example.clean.ui.adapter.RecipeItemAdapter
import com.example.clean.ui.viewmodel.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipe_add.*
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import kotlinx.android.synthetic.main.fragment_recipe_detail.rv_items

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private val viewModel by viewModels<RecipeDetailViewModel>()
    private val categoriesAdapter = RecipeDetailCategoryAdapter()
    private val itemsAdapter = RecipeItemAdapter(listOf())
    private val args: RecipeDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadRecipe(args.recipeName)

        rv_items.apply {
            adapter = itemsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        rv_selectedCategoriesRecipe.apply {
            adapter = categoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }
        b_edit.setOnClickListener {
            val action = RecipeDetailFragmentDirections.actionRecipeDetailFragmentToRecipeAddFragment(tv_name.text.toString())
            findNavController().navigate(action)
        }
        b_makeRecipeDetail.setOnClickListener {
            viewModel.makeRecipe()
        }
        b_recipeDetail_delete.setOnClickListener {
            viewModel.deleteRecipe()
            findNavController().popBackStack()
        }
        b_recipeDetail_toCart.setOnClickListener {
            viewModel.addMissingItemsToShoppingList()
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.recipe.observe(viewLifecycleOwner, Observer {
            tv_name.text = it.name
            tv_rating.text = it.rating.toString()
            tv_time.text = it.cookTime.toString()
            tv_servings.text = it.servings.toString()
            tv_instructions.text = it.description
            itemsAdapter.updateItems(it.items)
            it.categories.let { it1 -> categoriesAdapter.updateCategories(it1) }
        })
    }



}