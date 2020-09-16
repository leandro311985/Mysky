package com.example.mysky.data

import com.example.mysky.Api.ApiHelper
import com.example.mysky.Api.MovieAPIService
import com.example.mysky.data.Movie
import retrofit2.Response

class ApiHelperImpl(private val apiService: MovieAPIService) : ApiHelper {

    override suspend fun popularMovies(): Response<List<Movie>> = apiService.popularMovies()

}