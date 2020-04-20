package com.example.mysky.Api

import com.example.mysky.data.Movie
import retrofit2.Call
import retrofit2.http.GET


interface MovieAPIService {
    @get:GET("/api/Movies")
    val popularMovies: Call<List<Movie>>

}