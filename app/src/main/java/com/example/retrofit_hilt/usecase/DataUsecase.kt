package com.example.retrofit_hilt.usecase

import com.example.retrofit_hilt.model.GitHubDataModel
import com.example.retrofit_hilt.model.ResultData
import com.example.retrofit_hilt.repositories.DataRepository
import javax.inject.Inject

class DataUseCase @Inject constructor(private val dataRepository: DataRepository) {
    suspend fun getRepositoriesList(page: Int): ResultData<GitHubDataModel> {
        val publicRepositories = dataRepository.getPublicRepositories(page = page)

        val resultData = if (!publicRepositories.isNullOrEmpty()) {
            ResultData.Success(publicRepositories) // we will use Sealed class here..for success
        } else {
            ResultData.Failed("Something went wrong, Please try again later!")
        }
        return resultData
    }
}
