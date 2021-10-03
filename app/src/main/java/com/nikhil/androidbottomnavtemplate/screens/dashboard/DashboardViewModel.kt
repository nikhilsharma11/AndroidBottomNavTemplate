package com.nikhil.androidbottomnavtemplate.screens.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikhil.androidbottomnavtemplate.base.BaseViewModel

class DashboardViewModel() : BaseViewModel<DashboardEvent>() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}