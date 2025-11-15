package com.example.my_movie_list.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val adult: Boolean = true,
    val backdrop_path: String? = null,
    val genre_ids: List<Int>? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double = 0.0,
    val poster_path: String? = null,
    val release_date: String? = null,
    val title: String? = null,
    val video: Boolean = true,
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)