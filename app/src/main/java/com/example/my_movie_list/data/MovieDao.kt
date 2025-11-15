package com.example.my_movie_list.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.my_movie_list.data.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

    @Query("SELECT * from movies WHERE id = :id")
    fun getMovie(id: Int): Flow<Movie>

    @Query("SELECT * from movies ORDER BY title ASC")
    fun getAllMovies(): Flow<List<Movie>>
}