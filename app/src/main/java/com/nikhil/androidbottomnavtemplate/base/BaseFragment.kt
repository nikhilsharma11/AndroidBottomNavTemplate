package com.nikhil.androidbottomnavtemplate.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    fun hideKeyboard() {
        (activity as? BaseActivity<*>)?.hideKeyboard(requireView())
    }
}