package com.nikhil.androidbottomnavtemplate.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikhil.androidbottomnavtemplate.common.SingleLiveEvent
import com.nikhil.androidbottomnavtemplate.data.DataRepositoryContract
import com.nikhil.androidbottomnavtemplate.data.idlingresource.ProjectIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel: ViewModel(), KoinComponent {
    private val dataRepository: DataRepositoryContract by inject()
    private val idlingResource: ProjectIdlingResource by inject()


    protected fun CoroutineScope.launchIdling(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        idlingResource.increment()
        val job = this.launch(context, start, block)
        job.invokeOnCompletion { idlingResource.decrement() }
        return job
    }
}