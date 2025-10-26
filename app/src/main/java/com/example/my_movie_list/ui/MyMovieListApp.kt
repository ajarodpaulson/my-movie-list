package com.example.my_movie_list.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.my_movie_list.R
import com.example.my_movie_list.ui.navigation.AppNavHost
import com.example.my_movie_list.ui.navigation.Destination

@Composable
fun MyMovieListApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Destination.entries.firstOrNull { screen ->
        screen.route == navBackStackEntry?.destination?.route
    } ?: Destination.MY_LIST

    Scaffold(
        topBar = {
            MovieListAppBar(
                currentScreen = currentScreen,
                canNavigateBack = !Destination.entries.any {
                    navController.currentDestination?.route == it.route
                },
                navigateUp = { navController.navigateUp() })
        },
        bottomBar = {
            NavigationBar {
                Destination.values().forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painterResource(screen.iconId),
                                contentDescription = screen.contentDescription
                            )
                        },
                        label = { Text(screen.label) },
                        selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    inclusive = true
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            startDestination = Destination.MY_LIST,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListAppBar(
    currentScreen: Destination,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen.label) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        painterResource(R.drawable.outline_arrow_back_24),
                        contentDescription = "back button",
                    )
                }
            }
        }
    )
}