package com.nikhil.androidbottomnavtemplate.screens.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikhil.androidbottomnavtemplate.base.BaseViewModel

class NotificationsViewModel() : BaseViewModel<NotificationsEvent>() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}