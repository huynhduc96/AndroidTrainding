package com.example.moviedb.data.repository.impl

import com.example.moviedb.data.model.GenreResponse
import com.example.moviedb.data.repository.UserRepository
import com.example.moviedb.data.service.ApiService

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {

    override suspend fun getGenreList(): GenreResponse {
        return apiService.getGenreAsync()
    }
}
