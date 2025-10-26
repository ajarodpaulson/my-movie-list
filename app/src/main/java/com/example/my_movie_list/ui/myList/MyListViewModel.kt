package com.example.my_movie_list.ui.myList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_movie_list.data.model.Movie
import com.example.my_movie_list.data.model.Watchlist
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyListViewModel : ViewModel() {

    private val watchlist = Watchlist()

    private val _watchlistMovies = MutableStateFlow<List<Movie>>(emptyList())
    val watchlistMovies: StateFlow<List<Movie>> = _watchlistMovies.asStateFlow()

    init {
        // Initialize the flow with current watchlist
        Log.d("MyListViewModel", "view model being created")
        _watchlistMovies.value = watchlist.getWatchlistMovies()
    }

    fun addMovieToWatchlist(movie: Movie) {
        viewModelScope.launch {
            watchlist.addMovieToWatchlist(movie)
            _watchlistMovies.value = watchlist.getWatchlistMovies()
        }
    }

    fun removeMovieFromWatchlist(movieId: Int) {
        viewModelScope.launch {
            watchlist.removeMovieFromWatchlist(movieId)
            _watchlistMovies.value = watchlist.getWatchlistMovies()
        }
    }

    fun isMovieInWatchlist(movieId: Int): Boolean {
        return watchlist.isMovieInWatchlist(movieId)
    }
}