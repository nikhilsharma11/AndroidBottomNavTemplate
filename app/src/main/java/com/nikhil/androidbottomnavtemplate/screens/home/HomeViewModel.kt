package com.nikhil.androidbottomnavtemplate.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.androidbottomnavtemplate.base.BaseViewModel
import com.nikhil.androidbottomnavtemplate.data.DataRepositoryContract
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import timber.log.Timber
import java.lang.Exception

class HomeViewModel(private val dataRepository: DataRepositoryContract) : BaseViewModel(), KoinComponent {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getUniList() {
        viewModelScope.launchIdling {
            try {
                with(dataRepository) {
                    Timber.d("getting Result.............")
                    val result = getUniList("technology")
                    Timber.d("Result:: $result")
                }
            } catch (e: Exception) {
               Timber.d("RESULT_ERR:: ${e.message}")
            }
        }
    }
}