package com.example.my_movie_list.network

import MovieResponse
import com.example.my_movie_list.data.model.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://api.themoviedb.org/3/"

private const val API_KEY = "d5ed70c06b7e62983104a98369e7246c"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface TmdbApiService {
    @GET("movie/popular")
    suspend fun getMostPopularMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): MovieResponse
}

object TmdbApi {
    val retrofitService: TmdbApiService by lazy {
        retrofit.create(TmdbApiService::class.java)
    }
}