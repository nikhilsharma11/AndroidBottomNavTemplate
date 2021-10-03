package com.nikhil.androidbottomnavtemplate.base

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment<E: BaseEvent, VM : BaseViewModel<E>> : Fragment() {

    fun hideKeyboard() {
        (activity as? BaseActivity<*, *>)?.hideKeyboard(requireView())
    }

    fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }
}