package com.example.retrofit_hilt.network

import com.example.retrofit_hilt.model.GitHubDataModel
import com.example.retrofit_hilt.utility.NetworkUtility
import retrofit2.http.GET

interface ApiService {  //aa interface ne provide karavo padshe..in Module
    @GET(NetworkUtility.URL_REPOSITORIES)
    suspend fun getPublicRepositories(): GitHubDataModel    //Suspend bc we will be using Coroutines..to call our Api.
}