package com.example.moviedb.data.repository.impl

import com.example.moviedb.data.model.GenreResponse
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse
import com.example.moviedb.data.repository.UserRepository
import com.example.moviedb.data.service.ApiService

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {

    override suspend fun getGenreList(): GenreResponse =
        apiService.getGenreAsync()


    override suspend fun getMovieList(page: Int, genre: Int): MovieResponse =
        apiService.getPopularMovie(page, genre)

    override suspend fun getMovieDetail(movieId: Int, type: String): Movie =
        apiService.getMovieDetail(movieId, type)
}
