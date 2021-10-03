package com.nikhil.androidbottomnavtemplate.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nikhil.androidbottomnavtemplate.R
import com.nikhil.androidbottomnavtemplate.base.BaseFragment
import com.nikhil.androidbottomnavtemplate.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeEvent, HomeViewModel>() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    private lateinit var adapter: UniItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        init()

        return binding.root
    }

    private fun init() {
        adapter = UniItemsAdapter()
        binding.uniRv.adapter = adapter

        addObservers()
    }

    private fun addObservers() {
        viewModel.uniList.observe(viewLifecycleOwner, {
            adapter.setUniList(it)
            hideKeyboard()
            binding.uniRv.requestFocus()
        })

        viewModel.events.observe(viewLifecycleOwner, {
            when(it) {
                is HomeEvent.FindClicked -> {
                    if(binding.etKeyword.text.toString().isNotBlank())
                        viewModel.getUniList(binding.etKeyword.text.toString())
                    else
                        view?.snack(resources.getString(R.string.enter_keyword_error))
                }
            }
        })
    }
}