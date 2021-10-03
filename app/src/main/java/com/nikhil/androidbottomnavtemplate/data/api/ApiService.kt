package com.nikhil.androidbottomnavtemplate.data.api

import com.nikhil.androidbottomnavtemplate.common.models.UniItem
import com.nikhil.androidbottomnavtemplate.data.api.ProjectEndpoints.PATH_NAME
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET(ProjectEndpoints.getUniList)
    suspend fun getUniList(
        @Query(PATH_NAME) keyword: String
    ): List<UniItem>
}