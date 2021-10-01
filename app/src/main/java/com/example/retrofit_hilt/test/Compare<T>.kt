package com.example.retrofit_hilt.test

interface Compare<T> {
    fun compare(item1: T, item2: T) :Int
}