package com.example.retrofit_hilt.di

import com.example.retrofit_hilt.network.ApiService
import com.example.retrofit_hilt.repositories.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

// we will be using repositories in Vm, so we should make this module live as long as application is alive ...don't use "SingletonComponent"
@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {
    @Provides
    fun providesDataRepository(apiService: ApiService): DataRepository {
        return DataRepository(apiService)
    }
}