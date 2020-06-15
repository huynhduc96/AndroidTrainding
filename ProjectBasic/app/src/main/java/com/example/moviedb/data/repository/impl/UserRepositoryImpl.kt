package com.example.moviedb.data.repository.impl

import com.example.moviedb.data.local.dao.FavoritesDao
import com.example.moviedb.data.model.GenreResponse
import com.example.moviedb.data.model.Movie
import com.example.moviedb.data.model.MovieResponse
import com.example.moviedb.data.repository.UserRepository
import com.example.moviedb.data.service.ApiService

class UserRepositoryImpl(
    private val apiService: ApiService,
    private val favoritesDao: FavoritesDao
) : UserRepository {

    override suspend fun getGenreList() = apiService.getGenreAsync()

    override suspend fun getMovieList(page: Int, genre: Int) =
        apiService.getPopularMovie(page, genre)

    override suspend fun getMovieDetail(movieId: Int, type: String) =
        apiService.getMovieDetail(movieId, type)

    override fun getMovieListLocal() = favoritesDao.getMovieList()

    override suspend fun getMovieLocal(id: Int) = favoritesDao.getMovie(id)

    override suspend fun insertLocal(movie: Movie) = favoritesDao.insert(movie)

    override suspend fun updateLocal(movie: Movie) = favoritesDao.update(movie)

    override suspend fun deleteMovieLocal(movie: Movie) = favoritesDao.deleteMovie(movie)

    override suspend fun deleteMovieLocal(id: Int) = favoritesDao.deleteMovie(id)
}
