package com.nikhil.androidbottomnavtemplate.data

import com.nikhil.androidbottomnavtemplate.data.api.ApiContract
import com.nikhil.androidbottomnavtemplate.data.preferences.PreferencesContract

class ProjectDataRepository (
    private val api: ApiContract,
    private val prefs: PreferencesContract
) : ApiContract by api,
    PreferencesContract by prefs,
    DataRepositoryContract {

}