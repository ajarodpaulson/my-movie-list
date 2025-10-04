package com.example.my_movie_list.ui.navigation

import com.example.my_movie_list.R


enum class Destination(
    val route: String,
    val label: String,
    val iconId: Int,
    val contentDescription: String
) {
    MY_LIST(
        "my list",
        "My List",
        R.drawable.outline_list_24,
        "My List",
    ),
    FIND_MOVIES(
        "find movies",
        "Find Movies",
        R.drawable.outline_search_24,
        "Find Movies"
    ),
}