package com.example.moviedb.data.repository

import com.example.moviedb.data.model.GenreResponse
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse

interface UserRepository {

    suspend fun getGenreList(): GenreResponse
    suspend fun getMovieList(page: Int, genre: Int): MovieResponse
    suspend fun getMovieDetail(movieId: Int, type: String): Movie
}
