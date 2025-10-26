package com.example.my_movie_list.ui.navigation

import MovieDetailScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.my_movie_list.ui.movies.FindMoviesScreen
import com.example.my_movie_list.ui.myList.MyListScreen
import com.example.my_movie_list.ui.myList.MyListViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,
    myListViewModel: MyListViewModel = viewModel()
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
                    Destination.MY_LIST -> MyListScreen(
                        navController,
                        myListViewModel = myListViewModel,
                    )
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
                myListViewModel = myListViewModel,
                navController = navController,
                navBackStackEntry = backStackEntry,
                modifier = modifier,
            )
        }
    }
}
