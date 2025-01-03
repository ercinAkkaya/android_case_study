package com.example.android_case_study.presentation.ui.cart.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_case_study.adapter.CartRecyclerAdapter
import com.example.android_case_study.core.base.BaseFragment
import com.example.android_case_study.databinding.FragmentCartBinding
import com.example.android_case_study.presentation.ui.cart.CartFragmentAction
import com.example.android_case_study.presentation.ui.cart.CartFragmentEffect
import com.example.android_case_study.presentation.ui.cart.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>() {

    private lateinit var adapter: CartRecyclerAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCartBinding {
        return FragmentCartBinding.inflate(inflater, container, false)
    }

    override fun getViewModelClass(): Class<CartViewModel> {
        return CartViewModel::class.java
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupRecyclerView()
        observeState()
        observeEffects()
    }

    private fun setupUI() {
        binding.apply {
            baseTopBar.setTitle("E-Market")
            baseTopBar.isHasIcon(false)
        }
        binding.btnComplete.setOnClickListener {
            viewModel.handleAction(CartFragmentAction.CompleteShopping)
        }
    }

    private fun setupRecyclerView() {
        adapter = CartRecyclerAdapter(arrayListOf()) { action ->
            viewModel.handleAction(action)
        }
        binding.cartRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@CartFragment.adapter
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.apply {
                        when {
                            state.isLoading -> {
                                cartRecyclerView.visibility = View.GONE
                                progressBar.visibility = View.VISIBLE
                            }
                            state.cartItems.isNotEmpty() -> {
                                cartRecyclerView.visibility = View.VISIBLE
                                progressBar.visibility = View.GONE
                                adapter.updateCartItems(state.cartItems)
                                productPrice.text = state.totalPrice.toString()
                            }
                            state.error.isNotBlank() -> {
                                cartRecyclerView.visibility = View.GONE
                                progressBar.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }
    }

    private fun observeEffects() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEffect.collect { effect ->
                    when (effect) {
                        is CartFragmentEffect.ShowToast -> {
                            Toast.makeText(requireContext(), effect.message, Toast.LENGTH_SHORT).show()
                        }
                        null -> {
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
