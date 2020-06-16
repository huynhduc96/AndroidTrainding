package com.example.moviedb.data.repository

import androidx.lifecycle.LiveData
import com.example.moviedb.data.model.GenreResponse
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse

interface UserRepository {

    suspend fun getGenreList(): GenreResponse
    suspend fun getMovieList(page: Int, genre: Int): MovieResponse
    suspend fun getMovieDetail(movieId: Int, type: String): Movie

    /**
     * local movie db functions
     */
    fun getMovieListLocal(): LiveData<List<Movie>>

    suspend fun getMovieLocal(id: Int): Movie?

    suspend fun insertMovieLocal(movie: Movie)

    suspend fun updateLocal(movie: Movie)

    suspend fun deleteMovieLocal(movie: Movie)

    suspend fun deleteMovieLocal(id: Int)
}
