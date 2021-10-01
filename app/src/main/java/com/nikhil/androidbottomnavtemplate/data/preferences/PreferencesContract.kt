package com.nikhil.androidbottomnavtemplate.data.preferences

interface PreferencesContract {
    fun saveName(name: String)
    fun getName(): String

    fun clearPreferences()
}