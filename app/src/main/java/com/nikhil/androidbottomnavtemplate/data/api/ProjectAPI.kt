package com.nikhil.androidbottomnavtemplate.data.api

import com.nikhil.androidbottomnavtemplate.common.models.UniItem

class ProjectAPI(private val apiService: ApiService) : ApiContract {

    override suspend fun getUniList(keyword: String): List<UniItem> {
        return apiService.getUniList(keyword)
    }
}