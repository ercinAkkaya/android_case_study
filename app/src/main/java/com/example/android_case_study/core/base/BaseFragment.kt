package com.example.android_case_study.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<DB : ViewBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: DB? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: VM

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): DB

    open fun provideViewModel(): VM {
        return ViewModelProvider(this).get(getViewModelClass())
    }

    abstract fun getViewModelClass(): Class<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = provideViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Fragment context ile etkileşimde bulunmak için burada işlemler yapılabilir
    }
}
