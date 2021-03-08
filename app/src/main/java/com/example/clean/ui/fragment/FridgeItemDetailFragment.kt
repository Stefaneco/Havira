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
import com.example.clean.databinding.FragmentFridgeBinding
import com.example.clean.databinding.FragmentFridgeItemDetailBinding
import com.example.clean.ui.adapter.RecipeCategoryAction
import com.example.clean.ui.adapter.RecipeCategoryAdapter
import com.example.clean.ui.adapter.RecipeSelectedCategoryAdapter
import com.example.clean.ui.viewmodel.FridgeItemDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fridge_item_detail.*
import kotlinx.android.synthetic.main.fragment_recipe_add.*

@AndroidEntryPoint
class FridgeItemDetailFragment : Fragment(), RecipeCategoryAction {
    private var _binding: FragmentFridgeItemDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<FridgeItemDetailViewModel>()
    private val args: FridgeItemDetailFragmentArgs by navArgs()
    private val categoriesAdapter = RecipeCategoryAdapter(this)
    private val selectedCategoriesAdapter = RecipeSelectedCategoryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFridgeItemDetailBinding.inflate(inflater,container,false)
        return binding.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel(){
        viewModel.selectedCategories.observe(viewLifecycleOwner, Observer {
            selectedCategoriesAdapter.updateCategories(it)
        })
        viewModel.notSelectedCategoriesFiltered.observe(viewLifecycleOwner, Observer {
            categoriesAdapter.updateCategories(it)
        })
        viewModel.fridgeItem.observe(viewLifecycleOwner, Observer {
            binding.etFridgeDetailAmount.setText(it.amount.toString())
            binding.etFridgeDetailName.setText(it.name)
            binding.etFridgeDetailUnit.setText(it.unit)
        })
        viewModel.isUpdateFinished.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().popBackStack()
            }
        })
    }

    override fun checkCategory(category: String) {
        viewModel.checkCategory(category)
    }

    override fun uncheckCategory(category: String) {
        viewModel.uncheckCategory(category)
    }

    private fun setupRecyclerViews(){
        binding.rvFridgeDetailCategories.apply {
            adapter = categoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }
        binding.rvFridgeDetailSelectedCategories.apply {
            adapter = selectedCategoriesAdapter
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    private fun setupButtonsOnClicks(){
        binding.bFridgeDetailAddCategory.setOnClickListener {
            viewModel.checkCategory(et_fridgeDetail_searchCategory.text.toString())
        }
        binding.bFridgeDetailSubbmit.setOnClickListener {
            viewModel.updateItem(et_fridgeDetail_name.text.toString(),
            et_fridgeDetail_amount.text.toString().toFloat(),
            et_fridgeDetail_unit.text.toString())
            //findNavController().popBackStack()
        }
    }

    private fun setupCategorySearch(){
        binding.etFridgeDetailSearchCategory.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                viewModel.setCategoryFilter(s.toString())
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

}