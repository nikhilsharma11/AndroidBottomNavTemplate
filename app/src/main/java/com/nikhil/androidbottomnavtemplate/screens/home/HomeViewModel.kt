package com.nikhil.androidbottomnavtemplate.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.androidbottomnavtemplate.base.BaseViewModel
import com.nikhil.androidbottomnavtemplate.common.models.UniItem
import com.nikhil.androidbottomnavtemplate.data.DataRepositoryContract
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import timber.log.Timber
import java.lang.Exception

class HomeViewModel(private val dataRepository: DataRepositoryContract) : BaseViewModel<HomeEvent>(), KoinComponent {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _uniList = MutableLiveData<List<UniItem>>()
    val uniList: LiveData<List<UniItem>> = _uniList

    fun getUniList(keyword: String) {
        viewModelScope.launchIdling {
            try {
                with(dataRepository) {
                    val result = getUniList(keyword)
                    _uniList.value = result
                }
            } catch (e: Exception) {
               Timber.d("RESULT_ERR:: ${e.message}")
            }
        }
    }

    fun findClicked() {
        postEvent(HomeEvent.FindClicked)
    }
}