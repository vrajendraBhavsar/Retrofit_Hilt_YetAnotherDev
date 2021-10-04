package com.example.retrofit_hilt.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.retrofit_hilt.model.GitHubDataModel
import com.example.retrofit_hilt.model.ResultData
import com.example.retrofit_hilt.usecase.DataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@DelicateCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(private val dataUseCase: DataUseCase) : ViewModel() {
    // when calling suspend fun ....as useCase nu suspend fun call karyu chhe
    // option - 1. make fun suspend
    // option - 2. use it inside coroutine.
//    fun getRepositoriesList(): LiveData<ResultData<GitHubDataModel>> {
//        // we used extension property of kotlin ..so coroutine banavanu easy thai gayu..
//        return liveData<ResultData<GitHubDataModel>> { // pass useCase na fun wali return type in <>
//            // we are telling liveData to emit something
//            emit(ResultData.Loading()) // first time it will return loading state ..useCase ni method call thaya pahela.
//            emit(dataUseCase.getRepositoriesList())
//        }
//    }

    private val _githubDataModelLiveData = MutableLiveData<ResultData<GitHubDataModel>>()
    internal val githubDataModelLiveData: LiveData<ResultData<GitHubDataModel>> = _githubDataModelLiveData

    fun getRepositoriesList(page: Int) {
        val job: Job = GlobalScope.launch(Dispatchers.IO) {
            _githubDataModelLiveData.postValue(dataUseCase.getRepositoriesList(page = page))
        }
    }
}
// 1. MainFragment mathi vm ni getRepositoriesList() call krta
// 1. it'll create coroutine ..jeno return type liveData na <> walo hashe
// 2. telling it to emit ..result data with the type LOADING.
// 3. telling it to emit ..anything je repository ni method return kare chhe
