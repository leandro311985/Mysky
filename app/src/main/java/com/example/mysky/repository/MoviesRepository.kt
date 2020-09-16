package com.example.mysky.repository

import com.example.mysky.Api.ApiHelper

class MoviesRepository(private val apiHelper: ApiHelper) {

    suspend fun getMovies() = apiHelper.popularMovies()

}