package com.example.moviedb.data.repository

import com.example.moviedb.data.model.GenreResponse

interface UserRepository {

    suspend fun getGenreList(): GenreResponse
}
