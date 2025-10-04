package com.example.my_movie_list.ui.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun MovieDetailScreen(
    movieId: Int?,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    modifier: Modifier = Modifier,
) {
    val parentEntry = remember(navBackStackEntry) {
        navController.getBackStackEntry("find movies")
    }
    val viewModel: FindMoviesViewModel = viewModel(parentEntry)
    val movie = movieId?.let { viewModel.getMovieById(it) }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
        if (movie == null) {
            Text("Movie not found", modifier = Modifier.padding(16.dp))
        } else {
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                Column {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w500${movie.poster_path}",
                        contentDescription = "Movie poster for ${movie.title}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp),
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = movie.title ?: "No title found",
                            style = MaterialTheme.typography.titleLarge
                        )
                        Text(
                            text = movie.release_date ?: "No release date",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = "Rating: ${movie.vote_average}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        Text(
                            text = movie.overview ?: "No overview found",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}