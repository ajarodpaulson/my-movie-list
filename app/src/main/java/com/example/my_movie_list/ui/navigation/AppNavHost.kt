package com.example.my_movie_list.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.my_movie_list.ui.movies.FindMoviesScreen
import com.example.my_movie_list.ui.movies.MovieDetailScreen
import com.example.my_movie_list.ui.myList.MyListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier
    )
    {
        Destination.values().forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.MY_LIST -> MyListScreen()
                    Destination.FIND_MOVIES -> FindMoviesScreen(navController)
                }
            }
        }
        composable(
            route = "movieDetail/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            MovieDetailScreen(
                movieId,
                navController = navController,
                navBackStackEntry = backStackEntry,
            )
        }
    }
}
