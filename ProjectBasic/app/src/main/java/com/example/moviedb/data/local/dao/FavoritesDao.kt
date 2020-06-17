package com.example.moviedb.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviedb.data.model.Movie

@Dao
interface FavoritesDao {

    @Query("SELECT * FROM movie")
    fun getMovieList(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE movie.id = :id")
    suspend fun getMovie(id: Int): Movie?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movie WHERE id = :id")
    suspend fun deleteMovie(id: Int)
}
