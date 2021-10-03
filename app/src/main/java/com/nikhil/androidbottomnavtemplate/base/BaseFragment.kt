package com.nikhil.androidbottomnavtemplate.base

import androidx.fragment.app.Fragment

abstract class BaseFragment<E: BaseEvent, VM : BaseViewModel<E>> : Fragment() {
    fun hideKeyboard() {
        (activity as? BaseActivity<*, *>)?.hideKeyboard(requireView())
    }
}