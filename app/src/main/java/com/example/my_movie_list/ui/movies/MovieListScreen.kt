package com.example.my_movie_list.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.my_movie_list.data.model.Movie

@Composable
fun MovieListScreen(viewModel: MovieListViewModel) {
    val movies by viewModel.movies.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(movies) { movie ->
            MovieItem(movie)
        }
    }
}

@Composable
fun MovieItem(movie: Movie) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            val posterUrl = movie.poster_path?.let {
                "https://image.tmdb.org/t/p/w185$it"
            }
            if (posterUrl != null) {
                Image(
                    painter = ColorPainter(Color.Transparent),
                    contentDescription = movie.title,
                    modifier = Modifier.size(80.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = movie.title ?: "No Title",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = movie.overview ?: "No overview available",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3
                )
            }
        }
    }
}

