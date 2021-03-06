package com.example.mysky.Api

import com.example.mysky.data.Movie
import retrofit2.Response
import retrofit2.http.GET


interface MovieAPIService {
    @GET("/api/Movies")
    suspend fun popularMovies(): Response<List<Movie>>

}