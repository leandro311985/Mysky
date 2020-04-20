package com.example.mysky.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mysky.data.Movie

class MovieViewModel(private val repository: Repository) : ViewModel() {

    val moviesLiveData: MutableLiveData<List<Movie>> = repository.getUser()

    class MainViewModelFactory(
        private val repository: Repository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MovieViewModel(repository) as T
        }
    }

}