package com.example.android_case_study.presentation.ui.detail.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.android_case_study.core.base.BaseFragment
import com.example.android_case_study.core.util.extensions.loadImage
import com.example.android_case_study.core.util.extensions.placeHolder
import com.example.android_case_study.databinding.FragmentDetailBinding
import com.example.android_case_study.presentation.ui.detail.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun getViewModelClass(): Class<DetailViewModel> {
        return DetailViewModel::class.java
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()

        arguments?.let { bundle ->
            val args = DetailFragmentArgs.fromBundle(bundle)
            viewModel.setProductDetail(args.DetailModel)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.apply {
                        state.isLoading.let {
                            if (it) {
                                //TODO
                            }
                            state.productDetailModel?.let { product ->
                                productImage.loadImage(product.imageUrl, placeHolder(requireContext()))
                                productName.text = product.name
                                productPrice.text = product.price.toString()
                                productDescription.text = product.description
                            }
                        }
                    }
                }
            }
        }
    }
}