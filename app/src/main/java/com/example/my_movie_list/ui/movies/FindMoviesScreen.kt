package com.example.my_movie_list.ui.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.my_movie_list.data.model.Movie

@Composable
fun FindMoviesScreen(navController: NavController, modifier: Modifier = Modifier) {
    val viewModel: FindMoviesViewModel = viewModel()
    val movies by viewModel.movies.collectAsState()
    LazyColumn(modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp)) {
        items(movies) { movie ->
            MovieCard(
                movie = movie,
                modifier = Modifier.padding(vertical = 4.dp),
                onClick = { navController.navigate("movieDetail/${movie.id}") }
            )
        }
    }
}


@Composable
fun MovieCard(movie: Movie, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w185${movie.poster_path}",
                contentDescription = "Movie poster for ${movie.title}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = movie.original_title ?: "No title found",
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = movie.overview ?: "No overview found",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
