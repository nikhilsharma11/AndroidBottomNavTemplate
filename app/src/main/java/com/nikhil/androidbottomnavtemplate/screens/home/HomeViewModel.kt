package com.nikhil.androidbottomnavtemplate.screens.home

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.nikhil.androidbottomnavtemplate.base.BaseViewModel
import com.nikhil.androidbottomnavtemplate.common.models.UniItem
import com.nikhil.androidbottomnavtemplate.data.DataRepositoryContract
import kotlinx.coroutines.delay
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class HomeViewModel(private val dataRepository: DataRepositoryContract) : BaseViewModel<HomeEvent>() {

    private val INITIAL_MESSAGE = "Find universities by entering a keyword"
    private val NO_RESULTS = "No results found.."
    private val SEARCHING = "Looking for universities..."

    private val _text = MutableLiveData<String>().apply {
        value = INITIAL_MESSAGE
    }
    val text: LiveData<String> = _text

    private val _uniList = MutableLiveData<List<UniItem>>()
    val uniList: LiveData<List<UniItem>> = _uniList

    val noResultsTextVisibility: LiveData<Int> = _uniList.map {
        if(it.isEmpty()) View.VISIBLE else View.GONE
    }

    private val _progressVisibility = MutableLiveData<Boolean>().apply { false }
    val progressVisibility: LiveData<Boolean> = _progressVisibility

    fun getUniList(keyword: String) {
        viewModelScope.launchIdling {
            try {
                _progressVisibility.value = true
                clearUniListForSearch()
                delay(1000)
                with(dataRepository) {
                    val result = getUniList(keyword)
                    _uniList.value = result
                    if(result.isEmpty()) _text.value = NO_RESULTS
                }
            } catch (e: Exception) {
               _text.value = NO_RESULTS
            } finally {
                _progressVisibility.value = false
            }
        }
    }

    private fun clearUniListForSearch() {
        _uniList.value = emptyList()
        _text.value = SEARCHING
    }

    fun findClicked() {
        postEvent(HomeEvent.FindClicked)
    }
}