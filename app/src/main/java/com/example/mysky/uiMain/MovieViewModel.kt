package com.example.mysky.uiMain

import androidx.lifecycle.*
import com.example.mysky.utils.NetworkHelper
import com.example.mysky.utils.Resource
import com.example.mysky.data.Movie
import com.example.mysky.repository.MoviesRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val mainRepository: MoviesRepository,
                     private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _movies = MutableLiveData<Resource<List<Movie>>>()
    val movies: LiveData<Resource<List<Movie>>>
        get() = _movies

    init {
        fetchMovie()
    }

    private fun fetchMovie() {
        viewModelScope.launch {
            _movies.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getMovies().let {
                    if (it.isSuccessful) {
                        _movies.postValue(Resource.success(it.body()))
                    } else _movies.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _movies.postValue(Resource.error("Sem Conexão com Internet", null))
        }
    }

}