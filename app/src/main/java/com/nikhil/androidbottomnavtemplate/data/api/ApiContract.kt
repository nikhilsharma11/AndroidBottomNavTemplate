package com.nikhil.androidbottomnavtemplate.data.api

import com.nikhil.androidbottomnavtemplate.common.models.UniItem

interface ApiContract {
    suspend fun getUniList(keyword: String): List<UniItem>
}