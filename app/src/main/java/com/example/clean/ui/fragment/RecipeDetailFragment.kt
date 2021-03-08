package com.example.clean.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.clean.R
import com.example.clean.databinding.FragmentFridgeBinding
import com.example.clean.databinding.FragmentRecipeDetailBinding
import com.example.clean.ui.adapter.decoration.MarginItemDecoration
import com.example.clean.ui.adapter.RecipeDetailCategoryAdapter
import com.example.clean.ui.adapter.RecipeItemAdapter
import com.example.clean.ui.viewmodel.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_recipe_detail.*
import kotlinx.android.synthetic.main.fragment_recipe_detail.rv_items

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {
    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RecipeDetailViewModel>()
    private val categoriesAdapter = RecipeDetailCategoryAdapter()
    private val itemsAdapter = RecipeItemAdapter(listOf())
    private val args: RecipeDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadRecipe(args.recipeName)

        rv_items.apply {
            adapter = itemsAdapter
            layoutManager = LinearLayoutManager(context)
        }
        binding.rvRecipeDetailSelectedCategories.apply {
            adapter = categoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(MarginItemDecoration(15))
        }
        binding.bRecipeDetailEdit.setOnClickListener {
            val popupMenu = PopupMenu(requireContext(), it)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId){
                    R.id.menu_b_edit -> {
                        val action = RecipeDetailFragmentDirections.actionRecipeDetailFragmentToRecipeAddFragment(binding.tvRecipeDetailName.text.toString())
                        findNavController().navigate(action)
                        true
                    }
                    R.id.menu_b_delete -> {
                        viewModel.deleteRecipe()
                        findNavController().popBackStack()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.edit_delete_menu)
            popupMenu.show()
        }
        binding.bRecipeDetailMake.setOnClickListener {
            viewModel.makeRecipe()
        }
        b_recipeDetail_toCart.setOnClickListener {
            viewModel.addMissingItemsToShoppingList()
        }

        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel(){
        viewModel.recipe.observe(viewLifecycleOwner, Observer {
            binding.tvRecipeDetailName.text = it.name
            binding.tvRecipeDetailRating.text = it.rating.toString()
            binding.tvRecipeDetailTime.text = it.cookTime.toString()
            binding.tvRecipeDetailServings.text = it.servings.toString()
            binding.tvRecipeDetailInstructions.text = it.description
            itemsAdapter.updateItems(it.items)
            it.categories.let { it1 -> categoriesAdapter.updateCategories(it1) }
        })
    }

}