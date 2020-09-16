package com.example.mysky.Api

import com.example.mysky.data.Movie
import retrofit2.Response

interface ApiHelper {
    suspend fun popularMovies(): Response<List<Movie>>

}