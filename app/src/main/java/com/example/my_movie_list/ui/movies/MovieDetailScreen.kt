import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.my_movie_list.data.model.Movie
import com.example.my_movie_list.ui.movies.FindMoviesViewModel
import com.example.my_movie_list.ui.myList.MyListViewModel

@Composable
fun MovieDetailScreen(
    movieId: Int?,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    modifier: Modifier = Modifier,
    myListViewModel: MyListViewModel,
) {
    val parentEntry = remember(navBackStackEntry) {
        navController.getBackStackEntry("find movies")
    }
    val findMoviesViewModel: FindMoviesViewModel = viewModel(parentEntry)
    val movie = movieId?.let { findMoviesViewModel.getMovieById(it) }

    MovieDetailContent(
        movie = movie,
        myListViewModel = myListViewModel,
        modifier = modifier
    )
}

@Composable
fun MovieDetailContent(
    movie: Movie?,
    myListViewModel: MyListViewModel,
    modifier: Modifier = Modifier
) {
    if (movie == null) {
        Text("Movie not found", modifier = Modifier.padding(16.dp))
    } else {
        val watchlist by myListViewModel.watchlistMovies.collectAsState()
        val isInWatchlist = watchlist.any { it.id == movie.id }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
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
                Button(
                    onClick = {
                        if (isInWatchlist) {
                            myListViewModel.removeMovieFromWatchlist(movie.id)
                        } else {
                            myListViewModel.addMovieToWatchlist(movie)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(if (isInWatchlist) "Remove from Watchlist" else "Add to Watchlist")
                }
            }
        }
    }
}

@Preview
@Composable
fun MovieDetailContentPreview() {
    MovieDetailContent(
        movie = Movie(
            id = 1,
            overview = "This is a sample movie overview",
            poster_path = "/sample.jpg",
            release_date = "2024-01-01",
            title = "Sample Movie",
            vote_average = 8.5
        ),
        myListViewModel = viewModel(),
    )
}
