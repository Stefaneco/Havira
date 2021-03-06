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
import com.example.clean.databinding.FragmentRecipeDetailBinding
import com.example.clean.databinding.FragmentShoppingBinding
import com.example.clean.ui.adapter.ShoppingAdapter
import com.example.clean.ui.adapter.ShoppingCheckAction
import com.example.clean.ui.dialog.AddShoppingItemDialog
import com.example.clean.ui.dialog.AddShoppingItemListener
import com.example.clean.ui.viewmodel.FridgeViewModel
import com.example.clean.ui.viewmodel.ShoppingViewModel
import com.example.core.entites.ShoppingItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_shopping.*

@AndroidEntryPoint
class ShoppingFragment : Fragment(), ShoppingCheckAction {
    private var _binding: FragmentShoppingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ShoppingViewModel>()
    private val shoppingAdapter = ShoppingAdapter(listOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShoppingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupRecyclerViews()
        setupButtonsOnClicks()
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadItems()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeViewModel(){
        viewModel.loadItems()
        viewModel.shoppingItems.observe(viewLifecycleOwner, Observer {
            shoppingAdapter.updateItems(it)
        })
    }

    private fun setupButtonsOnClicks(){
        binding.bShoppingAddItem.setOnClickListener {
            AddShoppingItemDialog(requireContext(), object : AddShoppingItemListener{
                override fun addShoppingItem(item: ShoppingItem) {
                    viewModel.addShoppingItem(item)
                }
            }).show()
        }
        binding.bShoppingFinish.setOnClickListener {
            viewModel.moveItemsToFridge()
        }
    }

    private fun setupRecyclerViews(){
        binding.rvShoppingList.apply {
            setHasFixedSize(true)
            adapter = shoppingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onItemClicked(itemName: String, unit: String, isChecked: Boolean) {
        viewModel.changeCheckStateOfItem(itemName,unit,isChecked)
    }


}