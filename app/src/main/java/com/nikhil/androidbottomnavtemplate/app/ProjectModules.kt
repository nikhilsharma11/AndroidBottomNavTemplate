package com.nikhil.androidbottomnavtemplate.app

import com.nikhil.androidbottomnavtemplate.BuildConfig
import com.nikhil.androidbottomnavtemplate.common.ConnectivityLiveData
import com.nikhil.androidbottomnavtemplate.data.DataRepositoryContract
import com.nikhil.androidbottomnavtemplate.data.ProjectDataRepository
import com.nikhil.androidbottomnavtemplate.data.api.ApiContract
import com.nikhil.androidbottomnavtemplate.data.api.ApiService
import com.nikhil.androidbottomnavtemplate.data.api.ProjectAPI
import com.nikhil.androidbottomnavtemplate.data.idlingresource.ProjectIdlingResource
import com.nikhil.androidbottomnavtemplate.data.idlingresource.SimpleProjectIdlingResource
import com.nikhil.androidbottomnavtemplate.data.preferences.PreferencesContract
import com.nikhil.androidbottomnavtemplate.data.preferences.ProjectPreferences
import com.nikhil.androidbottomnavtemplate.screens.dashboard.DashboardViewModel
import com.nikhil.androidbottomnavtemplate.screens.home.HomeViewModel
import com.nikhil.androidbottomnavtemplate.screens.notifications.NotificationsViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val allModules
    get() = listOf(
        appModule,
        dataModule,
        homeModule,
        dashboardModule,
        notificationsModule,
    )

val appModule = module {
    single { ConnectivityLiveData(androidContext()) }
}

val dataModule = module {
    single<ProjectIdlingResource> { SimpleProjectIdlingResource() }

    single<Converter.Factory> { MoshiConverterFactory.create() }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(get())
            .client(get<OkHttpClient>())
            .baseUrl(BuildConfig.API_URL)
            .build()
    }

    single {
        Moshi.Builder().build()
    }

    single<ApiContract> {
        val retrofitApiService = get<Retrofit>().create(ApiService::class.java)
        ProjectAPI(retrofitApiService)
    }

    single<PreferencesContract> { ProjectPreferences(androidContext()) }

    single<DataRepositoryContract> { ProjectDataRepository(get(), get()) }
}

val dashboardModule = module {
    viewModel {
        DashboardViewModel()
    }
}

val homeModule = module {
    viewModel {
        HomeViewModel(get())
    }
}

val notificationsModule = module {
    viewModel {
        NotificationsViewModel()
    }
}