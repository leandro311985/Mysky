package com.example.mysky.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.mysky.Api.MovieAPI
import com.example.mysky.data.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    fun getUser(): MutableLiveData<List<Movie>> {
        val data = MutableLiveData<List<Movie>>()
        val repos = MovieAPI
        repos.build()?.popularMovies?.enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                data.value = response.body()
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return data
    }
}