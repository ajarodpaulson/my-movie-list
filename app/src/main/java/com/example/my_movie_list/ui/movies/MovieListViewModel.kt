package com.example.my_movie_list.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_movie_list.data.model.Movie
import com.example.my_movie_list.network.TmdbApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    init {
        fetchMovies()
    }


    private fun fetchMovies() {
        viewModelScope.launch {
            try {
                println("made it here!!!")
                val response = TmdbApi.retrofitService.getMostPopularMovies()
                println("API response: $response")
                _movies.value = response.results
            } catch (e: Exception) {
                // Handle error (log, show message, etc.)
                _movies.value = emptyList()
            }
        }
    }
}
