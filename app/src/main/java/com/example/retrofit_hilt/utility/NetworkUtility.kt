package com.example.retrofit_hilt.utility
// https://api.github.com/repositories
//https://api.github.com/search/repositories?q=tetris+language:assembly&page=1&per_page=10
object NetworkUtility {
    const val BASE_URL = "https://api.github.com/"
//    const val URL_REPOSITORIES = "search/repositories?q=tetris+language:assembly"
    const val URL_REPOSITORIES = "repositories"
}
