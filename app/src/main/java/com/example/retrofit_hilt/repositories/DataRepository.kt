package com.example.retrofit_hilt.repositories

import com.example.retrofit_hilt.model.GitHubDataModel
import com.example.retrofit_hilt.network.ApiService
import javax.inject.Inject

class DataRepository @Inject constructor(private val apiService: ApiService) { // repo will used to get data ..from Api response we get
    suspend fun getPublicRepositories(): GitHubDataModel { // calling Interface ni method
        return apiService.getPublicRepositories()
    }
}

// we should use Coroutine in viewModel only
