package com.nikhil.androidbottomnavtemplate.data.idlingresource

import androidx.test.espresso.IdlingResource

interface ProjectIdlingResource {
    fun getResource(): IdlingResource?
    fun increment()
    fun decrement()
}