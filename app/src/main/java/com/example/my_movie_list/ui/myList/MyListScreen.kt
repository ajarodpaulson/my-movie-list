package com.example.my_movie_list.ui.myList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.my_movie_list.ui.movies.MovieCard

@Composable
fun MyListScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    myListViewModel: MyListViewModel,
) {
    val watchlistMovies by myListViewModel.watchlistMovies.collectAsState()

    if (watchlistMovies.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Your watchlist is empty.")
        }
    } else {
        LazyColumn(modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
            items(watchlistMovies) { movie ->
                MovieCard(
                    movie = movie,
                    modifier = Modifier.padding(vertical = 4.dp),
                    onClick = { navController.navigate("movieDetail/${movie.id}") }
                )
            }
        }
    }
}
