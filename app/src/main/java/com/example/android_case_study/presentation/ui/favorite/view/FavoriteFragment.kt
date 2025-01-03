package com.example.android_case_study.presentation.ui.favorite.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_case_study.R
import com.example.android_case_study.adapter.FavoriteAdapter
import com.example.android_case_study.core.base.BaseFragment
import com.example.android_case_study.databinding.FavoriteListItemBinding
import com.example.android_case_study.databinding.FragmentFavoriteBinding
import com.example.android_case_study.presentation.ui.favorite.viewmodel.FavoriteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    private lateinit var adapter: FavoriteAdapter


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, container, false)
    }

    override fun getViewModelClass(): Class<FavoriteViewModel> {
        return FavoriteViewModel::class.java
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

        setupUI()
        setupRecyclerView()
        observeState()

    }

    private fun setupUI() {
        binding.apply {
            baseTopBar.setTitle("E-Market")
            baseTopBar.isHasIcon(false)
        }
    }

    private fun setupRecyclerView() {
        adapter = FavoriteAdapter(arrayListOf()) { action ->
                viewModel.handleAction(action)
        }
        binding.favoriteRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@FavoriteFragment.adapter
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    binding.apply {
                        when {
                            state.isLoading -> {
                                favoriteRecyclerView.visibility = View.GONE
                                progressBar.visibility = View.VISIBLE
                            }
                            state.favoriteItems.isNotEmpty() -> {
                                favoriteRecyclerView.visibility = View.VISIBLE
                                progressBar.visibility = View.GONE
                                adapter.updateFavoriteItems(state.favoriteItems)
                            }
                            state.error.isNotBlank() -> {
                                favoriteRecyclerView.visibility = View.GONE
                                progressBar.visibility = View.GONE
                            }
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