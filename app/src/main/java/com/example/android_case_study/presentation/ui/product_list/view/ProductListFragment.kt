package com.example.android_case_study.presentation.ui.product_list.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android_case_study.adapter.HomeRecyclerAdapter
import com.example.android_case_study.core.base.BaseFragment
import com.example.android_case_study.databinding.FragmentProductListBinding
import com.example.android_case_study.domain.model.Product
import com.example.android_case_study.presentation.ui.product_list.viewmodel.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

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

        binding.baseTopBar.setTitle("Product List")
        binding.baseTopBar.isHasIcon(true)




    }

    private fun setupRecyclerView( array: ArrayList<Product>) {
        adapter = HomeRecyclerAdapter(array)
        binding.productListRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.productListRecyclerView.adapter = adapter
    }
}