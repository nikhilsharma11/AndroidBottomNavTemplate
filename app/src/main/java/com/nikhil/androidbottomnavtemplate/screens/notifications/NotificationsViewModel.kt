package com.nikhil.androidbottomnavtemplate.screens.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikhil.androidbottomnavtemplate.base.BaseViewModel
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class NotificationsViewModel() : BaseViewModel<NotificationsEvent>() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}