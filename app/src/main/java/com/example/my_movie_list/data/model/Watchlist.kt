package com.example.my_movie_list.data.model

class Watchlist {
    private val watchlist = HashMap<Int, Movie>()

    fun addMovieToWatchlist(movie: Movie) {
        watchlist[movie.id] = movie
    }

    fun removeMovieFromWatchlist(movieId: Int) {
        watchlist.remove(movieId)
    }

    fun getWatchlistMovies(): List<Movie> {
        return watchlist.values.toList()
    }

    fun isMovieInWatchlist(movieId: Int): Boolean {
        return watchlist.containsKey(movieId)
    }
}