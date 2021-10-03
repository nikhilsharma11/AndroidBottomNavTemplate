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
        _binding.uniRvAdapter.adapter = adapter

        addObservers()
        viewModel.getUniList("technology")

        return binding.root
    }

    private fun addObservers() {
        viewModel.uniList.observe(viewLifecycleOwner, {
            adapter.setUniList(it)
            hideKeyboard()
        })

        viewModel.events.observe(viewLifecycleOwner, {
            when(it) {
                is HomeEvent.FindClicked -> Timber.d("NIKHIL:: Hello world!")
            }
        })
    }
}