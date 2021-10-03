package com.nikhil.androidbottomnavtemplate.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikhil.androidbottomnavtemplate.base.BaseFragment
import com.nikhil.androidbottomnavtemplate.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment<HomeEvent, HomeViewModel>() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding!!

    private lateinit var adapter: UniItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding.lifecycleOwner = viewLifecycleOwner
        _binding.viewModel = viewModel

        adapter = UniItemsAdapter()
        _binding.uniRv.adapter = adapter

        addObservers()

        return binding.root
    }

    private fun addObservers() {
        viewModel.uniList.observe(viewLifecycleOwner, {
            adapter.setUniList(it)
            hideKeyboard()
            _binding.uniRv.requestFocus()
        })

        viewModel.events.observe(viewLifecycleOwner, {
            when(it) {
                is HomeEvent.FindClicked -> {
                    if(_binding.etKeyword.text.toString().isNotBlank())
                        viewModel.getUniList(_binding.etKeyword.text.toString())
                    else
                        view?.snack("Please enter a keyword")
                }
            }
        })
    }
}