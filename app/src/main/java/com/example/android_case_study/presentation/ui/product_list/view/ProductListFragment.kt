package com.example.android_case_study.presentation.ui.product_list.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_case_study.adapter.HomeRecyclerAdapter
import com.example.android_case_study.core.base.BaseFragment
import com.example.android_case_study.databinding.FragmentProductListBinding
import com.example.android_case_study.presentation.ui.product_list.viewmodel.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductListFragment : BaseFragment<FragmentProductListBinding, ProductListViewModel>() {

    private lateinit var adapter: HomeRecyclerAdapter

    override fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProductListBinding {
        return FragmentProductListBinding.inflate(inflater, container, false)
    }

    override fun getViewModelClass(): Class<ProductListViewModel> {
        return ProductListViewModel::class.java
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        setupRecyclerView()
        observeState()
    }

    private fun setupUI() {
        binding.apply {
            baseTopBar.setTitle("Product List")
            baseTopBar.isHasIcon(true)
        }
    }

    private fun setupRecyclerView() {
        adapter = HomeRecyclerAdapter(arrayListOf())
        binding.productListRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = this@ProductListFragment.adapter
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.apply {
                        when {
                            state.isLoading -> {
                                loadingProgressBar.visibility = View.VISIBLE
                                productListRecyclerView.visibility = View.GONE
                            }
                            state.error.isNotEmpty() -> {
                                loadingProgressBar.visibility = View.GONE
                                productListRecyclerView.visibility = View.GONE
                                // Hata mesajını gösterin
                                Log.e("ProductListFragment", "Error: ${state.error}")
                            }
                            state.productList.isNotEmpty() -> {
                                loadingProgressBar.visibility = View.GONE
                                productListRecyclerView.visibility = View.VISIBLE
                                adapter.updateProducts(state.productList)
                            }
                        }
                    }
                }
            }
        }
    }

}